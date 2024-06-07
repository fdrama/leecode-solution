package org.pallas.leetcode.search;

public class Search {

    /**
     * 二分查找
     * 1. 从有序数组中查找目标元素
     * 2. 每次查找时，将目标元素与中间元素比较
     * 3. 如果目标元素等于中间元素，返回中间元素下标
     * 4. 如果目标元素大于中间元素，缩小查找范围至右半部分
     * 5. 如果目标元素小于中间元素，缩小查找范围至左半部分
     * 6. 重复上述步骤，直至找到目标元素或者查找范围为空
     * 7. 返回 -1 结构
     */
    public int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
