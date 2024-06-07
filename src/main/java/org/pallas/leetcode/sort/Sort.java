package org.pallas.leetcode.sort;

public class Sort {

    /**
     * 选择排序
     * 1. 每次从未排序区间中选择最小元素，将其与未排序区间的首个元素交换
     * 2. 未排序区间缩小，已排序区间扩大
     * 3. 重复上述步骤，直至未排序区间为空
     *
     * @param arr
     */
    void selectSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j; //记录最小值的下标
                }
            }
            // 交换 将该最小元素与未排序区间的首个元素交换
            swap(arr, i, minIndex);
        }
    }

    /**
     * 冒泡排序
     * 1. 每次比较相邻的两个元素，如果它们的顺序错误就交换它们
     * 2. 重复上述步骤，直至没有需要交换的元素
     * 3. 每次循环都将未排序区间的最大元素交换至该区间的最右端
     * 4. 未排序区间缩小，已排序区间扩大
     * 5. 重复上述步骤，直至未排序区间为空
     */
    void bubbleSort(int[] arr) {

        for (int i = arr.length - 1; i > 0; i--) {
            // 内循环：将未排序区间 [0, i] 中的最大元素交换至该区间的最右端
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    void bubbleSortWithFlag(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    /**
     * 插入排序
     * 1. 将未排序区间的第一个元素插入到已排序区间的合适位置
     * 2. 未排序区间缩小，已排序区间扩大
     * 3. 重复上述步骤，直至未排序区间为空
     */
    void insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int base = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > base) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = base;
        }
    }

    /**
     * 快速排序
     * 分治思想
     * 1. 选择一个基准元素，通常选择第一个元素或者最后一个元素
     * 2. 将比基准元素小的元素移动到基准元素的左边，将比基准元素大的元素移动到基准元素的右边
     * 3. 对基准元素左右两边的子序列重复上述步骤
     */
    void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }

    int partition(int[] arr, int left, int right) {
        int i = left, j = right;
        while (i < j) {
            while (i < j && arr[j] >= arr[left]) {
                j--;
            }
            while (i < j && arr[i] <= arr[left]) {
                i++;
            }
            swap(arr, i, j);
        }
        swap(arr, i, left);
        return i;
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 归并排序
     */
    void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) >> 1;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        // 将 temp 中的元素复制到原数组对应区间
        for (int l = 0; l < temp.length; l++) {
            arr[left + l] = temp[l];
        }
    }


}
