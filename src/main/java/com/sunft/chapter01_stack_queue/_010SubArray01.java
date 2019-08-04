package com.sunft.chapter01_stack_queue;

/**
 * 最大值减去最小值小于或等于num的子数组数量
 * 【题目】
 * 给定数组arr和整数num，共返回有多少个子数组满足如下情况：
 * max(arr[i...j]) - min(arr[i...j]) <= num
 * max(arr[i...j])表示子数组arr[i...j]中的最大值，min(arr[i...j])表示子数组arr[i...j]中的最小值。
 * 【要求】
 * 如果数组长度为N，请实现时间复杂度为O(N)的解法。
 * 解法一：自己的方法
 */
public class _010SubArray01 {

    /**
     * 返回arr的子数组中<=num的子数组的个数
     * @param arr
     * @param num
     * @return
     */
    public static int getSubArrayNum(int[] arr, int num) {
        int count = 0;//默认个数为0
        if (arr == null || arr.length == 0) return count;

        if (arr.length == 1) {
            if (arr[0] <= num) {
                count ++;
                return count;
            }
        }

        //子数组的长度范围为1~arr.length
        for (int len = 1; len <= arr.length; len ++) {
            //i代表子数组的起点，每次循环代表求解一个子数组
            for (int i = 0; i + len <= arr.length; i ++) {
                //声明子数组的最大值和最小值
                int min = arr[i];
                int max = arr[i];
                //该循环用于求出子数组中的最大值和最小值
                for (int j = i; j < i + len; j ++) {
                    //求数组最大值
                    if (arr[j] < min) {
                        min = arr[j];
                    }
                    if (arr[j] > max) {
                        max = arr[j];
                    }
                }
                //判断是否满足要求
                if (max - min <= num) {
                    count ++;
                }
            }
        }

        return count;
    }

    /**
     * 测试类
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(getSubArrayNum(new int[]{1, 1, 1, 1}, 1));
    }

}
