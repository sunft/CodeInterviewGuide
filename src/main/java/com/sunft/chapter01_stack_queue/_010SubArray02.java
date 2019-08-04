package com.sunft.chapter01_stack_queue;

import java.util.LinkedList;

/**
 * 最大值减去最小值小于或等于num的子数组数量
 * 【题目】
 * 给定数组arr和整数num，共返回有多少个子数组满足如下情况：
 * max(arr[i...j]) - min(arr[i...j]) <= num
 * max(arr[i...j])表示子数组arr[i...j]中的最大值，min(arr[i...j])表示子数组arr[i...j]中的最小值。
 * 【要求】
 * 如果数组长度为N，请实现时间复杂度为O(N)的解法。
 * 书上的最优解：时间复杂度为O(N)，额外空间复杂度为O(N)
 */
public class _010SubArray02 {

    public static int getSubArrayNum(int[] arr, int num) {
        //判断数组为null或数组长度为0的情况
        if (arr == null || arr.length == 0) {
            return 0;
        }

        //双端队列，用于维护窗口子数组arr[i...j]的最小值更新的结构
        LinkedList<Integer> qmin = new LinkedList<>();
        //双端队列，用于维护窗口子数组arr[i...j]的最大值更新的结构
        LinkedList<Integer> qmax = new LinkedList<>();

        int i = 0;//子数组左边的下标
        int j = 0;//子数组右边的下标
        int res = 0;//表示所有满足条件的子数组数量

        while (i < arr.length) {
            while (j < arr.length) {
                //qmin尾部保存的始终是最小元素的下标
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]) {
                    qmin.pollLast();//删除最后一个元素
                }
                //尾插法插入元素下标
                qmin.addLast(j);
                //qmax尾部保存的始终是最大元素的下标
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]) {
                    qmax.pollLast();//删除最后一个元素
                }
                //尾插法插入元素下标
                qmax.addLast(j);
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;
                }
                j ++;
            }
            if (qmin.peekFirst() == i) {
                qmin.pollFirst();
            }
            if (qmax.peekFirst() == i) {
                qmax.pollFirst();
            }
            res += j - i;
            i ++;
        }
        return res;
    }

    /**
     * 测试类
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(getSubArrayNum(new int[]{1, 1, 1, 1}, 1));
    }

}
