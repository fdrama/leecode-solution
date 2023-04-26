package org.pallas.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author fdrama
 * date 2023年04月26日 15:34
 */
public class Solution {

    /**
     * 最长字符串前缀
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int endPost = 0;
            int length = Math.min(prefix.length(), strs[i].length());
            while (endPost < length && prefix.charAt(endPost) == strs[i].charAt(endPost)) {
                endPost++;
            }
            prefix = prefix.substring(0, endPost);

        }
        return prefix;
    }

    /**
     * 有效括号
     */
    public static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        char[] chars = s.toCharArray();

        for (char ch : chars) {
            switch (ch) {
                case '{':
                case '[':
                case '(':
                    stack.push(ch);
                    break;
                case '}':
                case ']':
                case ')':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    char open = stack.pop();
                    if (!compatibleClose(open, ch)) {
                        return false;
                    }
                default:
                    break;
            }
        }
        return stack.isEmpty();
    }

    private static boolean compatibleClose(char open, char close) {
        if (open == '{') {
            return close == '}';
        } else if (open == '[') {
            return close == ']';
        }
        return close == ')';
    }
}
