package com.sunft.chapter08_array_matrix;

/**
 * 需要排序的最短子数组长度
 * 【题目】
 * 给定一个无序数组arr， 求出需要排序的最短子数组长度。
 * 例如： arr = [1， 5， 3， 4， 2， 6， 7]返回4， 因为只有[5， 3， 4， 2]需要排序
 *
 * 【解答】
 * 解决这个问题可以做到时间复杂度为O(N)、额外空间复杂度为O(1)
 */
public class _05GetMinLength {

    public int getMinLength(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int min = arr[arr.length - 1];
        int noMinIndex = -1;
        for (int i = arr.length - 2; i != -1; i--) {
            if (arr[i] > min) {
                noMinIndex = i;
            } else {
                min = Math.min(min, arr[i]);
            }
        }
        if (noMinIndex == -1) {
            return 0;
        }
        int max = arr[0];
        int noMaxIndex = -1;
        for (int i = 1; i != arr.length; i++) {
            if (arr[i] < max) {
                noMaxIndex = i;
            } else {
                max = Math.max(max, arr[i]);
            }
        }
        return noMaxIndex - noMinIndex + 1;
    }

}
