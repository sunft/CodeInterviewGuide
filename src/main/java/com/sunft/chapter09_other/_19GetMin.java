package com.sunft.chapter09_other;

/**
 * 在有序旋转数组中找到最小值
 * <p>
 * 【题目】
 * 有序数组arr可能经过一次旋转处理， 也可能没有， 且arr可能存
 * 在重复的数。 例如， 有序数组[1， 2， 3， 4， 5， 6， 7]， 可以旋转处
 * 理成[4， 5， 6， 7， 1， 2， 3]等。 给定一个可能旋转过的有序数组
 * arr， 返回arr中的最小值。
 */
public class _19GetMin {

    public int getMin(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while (low < high) {
            if (low == high - 1) {
                break;
            }
            if (arr[low] < arr[high]) {
                return arr[low];
            }
            mid = (low + high) / 2;
            if (arr[low] > arr[mid]) {
                high = mid;
                continue;
            }
            if (arr[mid] > arr[high]) {
                low = mid;
                continue;
            }
            while (low < mid) {
                if (arr[low] == arr[mid]) {
                    low++;
                } else if (arr[low] < arr[mid]) {
                    return arr[low];
                } else {
                    high = mid;
                    break;
                }
            }
        }
        return Math.min(arr[low], arr[high]);
    }

}
