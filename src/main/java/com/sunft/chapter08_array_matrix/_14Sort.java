package com.sunft.chapter08_array_matrix;

/**
 * 自然数数组的排序
 * <p>
 * 【题目】
 * 给定一个长度为N的整型数组arr， 其中有N个互不相等的自然数1～N， 请实现arr的排序，
 * 但是不要把下标0～N-1位置上的数通过直接赋值的方式替换成1～N。
 * <p>
 * 【要求】
 * 时间复杂度为O(N)， 额外空间复杂度为O(1)。
 */
public class _14Sort {

    public void sort1(int[] arr) {
        int tmp = 0;
        int next = 0;
        for (int i = 0; i != arr.length; i++) {
            tmp = arr[i];
            while (arr[i] != i + 1) {
                next = arr[tmp - 1];
                arr[tmp - 1] = tmp;
                tmp = next;
            }
        }
    }

    public void sort2(int[] arr) {
        int tmp = 0;
        for (int i = 0; i != arr.length; i++) {
            while (arr[i] != i + 1) {
                tmp = arr[arr[i] - 1];
                arr[arr[i] - 1] = arr[i];
                arr[i] = tmp;
            }
        }
    }

}
