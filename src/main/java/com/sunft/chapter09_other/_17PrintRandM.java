package com.sunft.chapter09_other;

/**
 * 从N个数中等概率打印M个数
 * <p>
 * 【题目】
 * 给定一个长度为N且没有重复元素的数组arr和一个整数n， 实现函数等概率随机打印arr中的M个数。
 * <p>
 * 【要求】
 * 1．相同的数不要重复打印。
 * 2．时间复杂度为O(M)， 额外空间复杂度为O(1)。
 * 3．可以改变arr数组。
 */
public class _17PrintRandM {

    public void printRandM(int[] arr, int m) {
        if (arr == null || arr.length == 0 || m < 0) {
            return;
        }
        m = Math.min(arr.length, m);
        int count = 0;
        int i = 0;
        while (count < m) {
            i = (int) (Math.random() * (arr.length - count));
            System.out.println(arr[i]);
            swap(arr, arr.length - count++ - 1, i);
        }
    }

    private void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

}
