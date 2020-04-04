package com.sunft.chapter08_array_matrix;

/**
 * 数组的partition调整
 * <p>
 * 【题目】
 * 给定一个有序数组arr， 调整arr使得这个数组的左半部分没有重复元素且升序， 而不用保证右部分是否有序。
 * 例如， arr=[1， 2， 2， 2， 3， 3， 4， 5， 6， 6， 7， 7， 8， 8， 8， 9]，调整之后arr=[1， 2， 3， 4， 5， 6， 7， 8， 9， …]。
 * <p>
 * 【补充题目】
 * 给定一个数组arr， 其中只可能含有0、 1、 2三个值， 请实现arr的排序。
 * <p>
 * 另一种问法为： 有一个数组， 其中只有红球、 蓝球和黄球， 请实
 * 现红球全放在数组的左边， 蓝球放在中间， 黄球放在右边。
 * 另一种问法为： 有一个数组， 再给定一个值k， 请实现比k小的数
 * 都放在数组的左边， 等于k的数都放在数组的中间， 比k大的数都放在数组的右边。
 * <p>
 * 【要求】
 * 1．所有题目实现的时间复杂度为O(N)。
 * 2．所有题目实现的额外空间复杂度为O(1)。
 */
public class _23ArrayPartition {

    public void leftUnique(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int u = 0;
        int i = 1;
        while (i != arr.length) {
            if (arr[i++] != arr[u]) {
                swap(arr, ++u, i - 1);
            }
        }
    }

    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int left = -1;
        int index = 0;
        int right = arr.length;
        while (index < right) {
            if (arr[index] == 0) {
                swap(arr, ++left, index++);
            } else if (arr[index] == 2) {
                swap(arr, index, --right);
            } else {
                index++;
            }
        }
    }

    private void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}
