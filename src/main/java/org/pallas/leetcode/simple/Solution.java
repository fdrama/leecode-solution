package org.pallas.leetcode.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fdrama
 * date 2023年04月26日 15:32
 */
public class Solution {

    /**
     * @param nums 整数数组
     *             1 <= nums.length <= 10^4
     *             -10^9 <= nums[i] <= 10^9
     *             -10^9 <= target <= 10^9
     *             只有一个有效答案
     *             不能使用同一个元素两次
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
     * 1. 负数不是回文数
     * 2. 末尾为0的数字不是回文数
     * 3. 反转一半数字，与原数字比较
     * 4. 反转数字时，反转一半数字，当反转数字大于原数字时，停止反转
     * 5. 当数字长度为奇数时，反转数字的长度会比原数字多一位，此时反转数字除以10，去掉最后一位
     * 6. 当数字长度为偶数时，反转数字与原数字相等
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
     * 1. 双指针，左指针指向下一个非指定元素位置
     * 2. 右指针遍历数组，遇到非指定元素时，与左指针交换
     * 3. 左指针右移
     * 4. 重复上述步骤，直至右指针遍历完数组
     * 5. 返回左指针位置
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
     * 1. 罗马数字规则 I(1) V(5) X(10) L(50) C(100) D(500) M(1000)
     * 2. 从左到右遍历罗马数字，若当前数字小于后一个数字，则减去当前数字，否则加上当前数字
     * 3. 返回结果
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
