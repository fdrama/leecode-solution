package org.pallas.leetcode.datastructure;

public class MinHeap {

    // 堆的存储结构
    private int[] heap;

    // 堆中元素的数量
    private int size;


    public MinHeap(int capacity) {
        heap = new int[capacity + 1];
        size = 0;
    }

    // 建堆

    public MinHeap(int[] arr) {
        int n = arr.length;
        heap = new int[n + 1];
        for (int i = 0; i < n; i++) {
            heap[i] = arr[i];
        }
        size = n;
        for (int i = parent(n - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    // 获取左孩子节点的下标

    private int left(int i) {
        return 2 * i + 1;
    }

    // 获取右孩子节点的下标

    private int right(int i) {
        return 2 * i + 2;
    }

    // 获取父节点的下标

    private int parent(int i) {
        return (i - 1) / 2;
    }

    // 获取堆顶元素

    public int peek() {
        return heap[0];
    }

    // 获取堆中元素的数量

    public int size() {
        return size;
    }

    // 向堆中插入元素
    public void push(int val) {
        heap[size] = val;
        siftUp(size);
        size++;
    }

    private void siftUp(int size) {
        int i = size;
        while (i > 0) {
            int p = parent(i);
            if (heap[i] >= heap[p]) {
                break;
            }
            swap(i, p);
            i = p;
        }
    }

    private void swap(int i, int p) {
        int temp = heap[i];
        heap[i] = heap[p];
        heap[p] = temp;
    }

    // 删除堆顶元素
    public int pop() {
        int val = heap[0];
        heap[0] = heap[size - 1];
        size--;
        siftDown(0);
        return val;
    }

    private void siftDown(int i) {
        while (true) {
            int l = left(i);
            int r = right(i);
            int smallest = i;
            if (l < size && heap[l] < heap[smallest]) {
                smallest = l;
            }
            if (r < size && heap[r] < heap[smallest]) {
                smallest = r;
            }
            if (smallest == i) {
                break;
            }
            swap(i, smallest);
            i = smallest;
        }
    }


}
