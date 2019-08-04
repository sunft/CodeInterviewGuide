package com.sunft.chapter01_stack_queue;

import java.util.LinkedList;

/**
 * 课本上的解法：使用双端队列实现
 * 整体的时间复杂度为O(N)
 */
public class _007MaxWindow02 {

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }

        //使用LinkedList保存数据
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i ++) {
            //如果qmax不为空并且最后一个元素比arr[i]小，则移除最后一个元素
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }

            if (i >= w - i) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        int[] a = getMaxWindow(arr, 3);
        for (int ele : a){
            System.out.print(ele + " ");
        }
    }

}
