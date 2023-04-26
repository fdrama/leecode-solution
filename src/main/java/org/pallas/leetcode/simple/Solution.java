package org.pallas.leetcode.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fdrama
 * date 2023年04月26日 15:32
 */
public class Solution {

    /**
     * @title 两数之和
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hash = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (hash.get(temp) != null) {
                return new int[]{hash.get(temp), i};
            }
            hash.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 回文数字
     */
    public static boolean isPalindrome(int x) {

        if (x < 0 || (x % 10 == 0 & x != 0)) {
            return false;
        }
        int reversNumber = 0;
        while (x > reversNumber) {
            reversNumber = reversNumber * 10 + x % 10;
            x /= 10;
        }
        return reversNumber == x || x == reversNumber / 10;
    }


    /**
     * 移除指定元素
     */
    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != val) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
        }
        return left;
    }

    /**
     * 移除重复元素
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            if (nums[right] != nums[left]) {
                if (right - left > 1) {
                    nums[++left] = nums[right];
                }
            }
        }
        return ++left;
    }

    /**
     * 罗马转数字
     */
    public static int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = getValue(s.charAt(i));
            if (preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private static int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }


}
