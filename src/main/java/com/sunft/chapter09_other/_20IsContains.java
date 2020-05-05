package com.sunft.chapter09_other;

/**
 * 在有序旋转数组中找到一个数
 * <p>
 * 【题目】
 * 有序数组arr可能经过一次旋转处理， 也可能没有， 且arr可能存
 * 在重复的数。 例如， 有序数组[1， 2， 3， 4， 5， 6， 7]， 可以旋转处
 * 理成[4， 5， 6， 7， 1， 2， 3]等。 给定一个可能旋转过的有序数组
 * arr， 再给定一个数num， 返回arr中是否含有num。
 */
public class _20IsContains {

    public boolean isContains(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] == num) {
                return true;
            }
            if (arr[low] == arr[mid] && arr[mid] == arr[high]) {
                while (low != mid && arr[low] == arr[mid]) {
                    low++;
                }
                if (low == mid) {
                    low = mid + 1;
                    continue;
                }
            }
            if (arr[low] != arr[mid]) {
                if (arr[mid] > arr[low]) {

                    if (arr[mid] > arr[low] && num < arr[mid]) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                } else {
                    if (num > arr[mid] && num <= arr[high]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
            } else {
                if (arr[mid] < arr[high]) {
                    if (num > arr[mid] && num <= arr[high]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                } else {
                    if (num >= arr[low] && num < arr[mid]) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
            }
        }
        return false;
    }

}
