package com.sunft.chapter08_array_matrix;

/**
 * 未排序正数数组中累加和为给定值的最长子数组长度
 * 【题目】
 * 给定一个数组arr， 该数组无序， 但每个值均为正数， 再给定一
 * 个正数k。 求arr的所有子数组中所有元素相加和为k的最长子数组长
 * 度。
 * 例如， arr=[1， 2， 1， 1， 1]， k=3。
 * 累加和为3的最长子数组为[1， 1， 1]， 所以结果返回3。
 */
public class _10GetMaxLength {

    public int getMaxLength(int[] arr, int K) {
        if (arr == null || arr.length == 0 || K <= 0) {
            return 0;
        }

        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        while (right < arr.length) {
            if (sum == K) {
                len = Math.max(len, right - left + 1);
                sum -= arr[left++];
            } else if (sum < K) {
                right++;
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
            } else {
                sum -= arr[left++];
            }
        }
        return len;
    }

}
