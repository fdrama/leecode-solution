package org.pallas.leetcode.hard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Calculator {

    private static final Map<Character, Integer> precedence = Map.of('+', 1, '-', 1, '*', 2, '/', 2);


    /**
     * 中缀表达式转逆波兰表达式
     *
     * @param infixExpression
     * @return
     */
    public static String[] infixToRPN(String infixExpression) {
        LinkedList<String> rpn = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        char[] tokens = infixExpression.toCharArray();
        for (int i = 0; i < tokens.length; i++) {
            char token = tokens[i];
            if (Character.isWhitespace(token)) {
                continue;
            }
            if (Character.isDigit(token) || token == '.') {
                StringBuilder number = new StringBuilder();
                while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.')) {
                    number.append(tokens[i]);
                    i++;
                }
                // 回退一个字符以处理下一个字符
                i--;
                // 正确地将数字作为一个整体添加到RPN表达式中
                rpn.addLast(number.toString());
            } else if (token == '-') {
                // 处理负号
                if (i == 0 || tokens[i - 1] == '(') {
                    rpn.addLast("0");
                    stack.push(token);
                } else {
                    while (!stack.isEmpty() && stack.peek() != '(' && precedence.get(token) <= precedence.getOrDefault(stack.peek(), 0)) {
                        rpn.addLast(String.valueOf(stack.pop()));
                    }
                    stack.push(token);
                }
            } else if (token == '(') {
                stack.push(token);
            } else if (token == ')') {
                // 正确处理右括号，将栈中元素弹出直到遇到左括号
                boolean foundLeftParenthesis = false;
                while (!stack.isEmpty() && stack.peek() != '(') {
                    rpn.addLast(String.valueOf(stack.pop()));
                    foundLeftParenthesis = true;
                }
                if (!stack.isEmpty()) {
                    stack.pop(); // Pop the '('
                } else if (!foundLeftParenthesis) {
                    throw new IllegalArgumentException("Mismatched parentheses in expression.");
                }
            } else if (precedence.containsKey(token)) {
                // 正确地根据运算符的优先级来处理栈中的运算符
                while (!stack.isEmpty() && stack.peek() != '(' && precedence.get(token) <= precedence.getOrDefault(stack.peek(), 0)) {
                    rpn.addLast(String.valueOf(stack.pop()));
                }
                // 将当前运算符推入栈
                stack.push(token);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                throw new IllegalArgumentException("Mismatched parentheses in expression.");
            }
            rpn.addLast(String.valueOf(stack.pop()));
        }

        return rpn.toArray(new String[0]);
    }

    public static BigDecimal evalRPN(String[] tokens) {
        Deque<BigDecimal> stack = new LinkedList<>();
        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0))) {
                stack.push(new BigDecimal(token));
            } else {
                BigDecimal num2 = stack.pop();
                BigDecimal num1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(num1.add(num2));
                        break;
                    case "-":
                        stack.push(num1.subtract(num2));
                        break;
                    case "*":
                        stack.push(num1.multiply(num2));
                        break;
                    case "/":
                        stack.push(num1.divide(num2, 6, RoundingMode.HALF_UP));
                        break;
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String infixExpression = "3 + (4 * 5) / 2 - 6";
        String[] rpn = infixToRPN(infixExpression);
        System.out.println("RPN: " + Arrays.toString(rpn));
        BigDecimal result = evalRPN(rpn);
        System.out.println("Result: " + result);
    }
}
