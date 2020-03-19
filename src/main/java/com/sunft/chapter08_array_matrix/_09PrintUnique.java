package com.sunft.chapter08_array_matrix;

/**
 * 不重复打印排序数组中相加和为给定值的所有二元组和三元组
 * <p>
 * 【题目】
 * 给定排序数组arr和整数k， 不重复打印arr中所有相加和为k的不
 * 降序二元组。
 * 例如， arr=[-8， -4， -3， 0， 1， 2， 4， 5， 8， 9]， k=10， 打印结
 * 果为：
 * 1， 9
 * 2， 8
 * 【补充题目】
 * 给定排序数组arr和整数k， 不重复打印arr中所有相加和为k的不
 * 降序三元组。
 * 例如， arr=[-8， -4， -3， 0， 1， 2， 4， 5， 8， 9]， k=10， 打印结
 * 果为：
 * -4， 5， 9
 * -3， 4， 9
 * -3， 5， 8
 * 0， 1， 9
 * 0， 2， 8
 * 1， 4， 5
 */
public class _09PrintUnique {

    public void printUniquePair(int[] arr, int K) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            if (arr[left] + arr[right] < K) {
                left++;
            } else if (arr[left] + arr[right] > K) {
                right--;
            } else {
                if (left == 0 || arr[left - 1] != arr[left]) {
                    System.out.println(arr[left] + "," + arr[right]);
                }
                left++;
                right--;
            }
        }
    }

    public void printUniqueTriad(int[] arr, int K) {
        if (arr == null || arr.length < 3) {
            return;
        }
        for (int i = 0; i < arr.length - 2; i++) {
            if (i == 0 || arr[i] != arr[i - 1]) {
                printRest(arr, i, i + 1, arr.length - 1, K - arr[i]);
            }
        }
    }

    private void printRest(int[] arr, int f, int l, int r, int K) {
        while (l < r) {
            if (arr[l] + arr[r] < K) {
                l++;
            } else if (arr[l] + arr[r] > K) {
                r--;
            } else {
                if (l == f + 1 || arr[l - 1] != arr[l]) {
                    System.out.println(arr[f] + "," + arr[l] + "," + arr[r]);
                }
                l++;
                r--;
            }
        }
    }

}
