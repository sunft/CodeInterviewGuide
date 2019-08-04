package com.sunft.chapter01_stack_queue;

/**
 * 生产窗口最大值数组
 * 【题目】
 * 有一个整形数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置。
 * 如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值。
 * 请实现一个函数：
 * 1.输入: 整型数组arr，窗口大小为w；
 * 2.输出: 一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的最大值。
 *
 * 方式一：使用自己的思路实现，时间复杂度为N*W
 */
public class _007MaxWindow01 {

    public static int[] getMaxWindow(int[] arr, int w) {

        if (arr == null || w <= 0) {
            throw new RuntimeException("arr can't be null pr w can't less than 0");
        }

        if (w > arr.length) {
            throw new RuntimeException("w can't be longer than arr.length");
        }

        int[] window = new int[arr.length-w+1];

        //w的最终边界不能越过数组
        int i = 0;
        while(w <= arr.length) {
            int max = arr[i];
            for (int j = i + 1; j < w; j ++) {
                if (arr[j] > max) {
                    max = arr[j];
                }
            }
            window[i] = max;
            i ++;
            w ++;
        }
        return window;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        for (int ele : getMaxWindow(arr, 8)) {
            System.out.print(ele + " ");
        }
    }

}
