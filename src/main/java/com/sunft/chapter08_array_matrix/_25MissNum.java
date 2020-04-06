package com.sunft.chapter08_array_matrix;

/**
 * 数组中未出现的最小正整数
 * <p>
 * 【题目】
 * 给定一个无序整型数组arr， 找到数组中未出现的最小正整数。
 * <p>
 * 【举例】
 * arr=[-1， 2， 3， 4]。 返回1。
 * arr=[1， 2， 3， 4]。 返回5。
 */
public class _25MissNum {

    public int missNum(int[] arr) {
        int l = 0;
        int r = arr.length;
        while (l < r) {
            if (arr[l] == l + 1) {
                l++;
            } else if (arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
                arr[l] = arr[--r];
            } else {
                swap(arr, l, arr[l] - 1);
            }
        }
        return l + 1;
    }

    private void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

}
