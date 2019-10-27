package com.sunft.chapter04_recurse_dp;

/**
 * 汉诺塔问题
 * 【题目】
 *  给定一个整数n，代表汉诺塔游戏中从小到大放置的n个圆盘，假设开始时所有的圆盘
 *  都放在左边的柱子上，想按照汉诺塔游戏的要求把所有的圆盘都移到最右边的柱子上。
 *  实现函数打印最优移动轨迹。
 *
 *  【举例】
 *   n=1时，打印：
 *   move from left to right
 *   n=2时，打印：
 *   move from left to mid
 *   move from left to right
 *   move from mid to right
 *
 *   【进阶题目】
 *    给定一个整型数组arr，其中含有1、2和3，代表所有圆盘目前的状态，1代表左柱，
 *    2代表中柱，3代表右柱，arr[i]的值代表地i+1个圆盘的位置。比如，arr=[3, 3, 2, 1]，
 *    代表第1个圆盘在右柱上、第2个圆盘在右柱上、第3个圆盘在中柱上、第4个圆盘在左柱上。
 *    如果arr代表的状态是最优移动轨迹过程中出现的状态，返回arr这种状态是移动轨迹中的
 *    第几个状态。如果arr代表的状态不是最优移动轨迹过程中出现的状态，则返回-1。
 *
 *    【举例】
 *     arr=[1, 1]。两个圆盘目前都在左柱上，也就是初始状态，所以返回0。
 *     arr=[2, 1]。第一个圆盘在中柱上、第二个圆盘在左柱上，这个状态是2个圆盘的汉诺塔
 *     游戏中最优移动轨迹的第1步，所以返回1。
 *     arr=[3, 3]。第一个圆盘在右柱上、第二个圆盘在右柱上，这个状态是2个圆盘的汉诺塔
 *     游戏中最优移动轨迹的第3步，所以返回3。
 *     arr=[2, 2]。第一个圆盘在中柱上、第二个圆盘在中柱上，这个状态是2个圆盘的汉诺塔
 *     游戏中最优移动轨迹中从来不会出现的状态，所以返回-1。
 *
 *     【进阶题目要求】
 *      如果arr长度为N，请实现时间复杂度为O(N)、额外空间复杂度为O(1)的方法。
 *
 */
public class _006Hanoi {

    /**
     * 打印最优解法
     * @param n 圆盘个数
     */
    public static void hanoi1(int n) {
        if (n > 0) {
            func1(n, "left", "mid", "right");
        }
    }

    /**
     * 把上面n-1个盘子看成一个整体，不能把下面n-1个盘子看成一个整体，否则没有递归结束条件
     * @param n
     * @param from
     * @param mid
     * @param to
     */
    private static void func1(int n, String from, String mid, String to) {
        if (n == 1) {
            System.out.println("move from " + from + " to " + to);
        } else {
            func1(n - 1, from, to, mid);
            func1(1, from, mid, to);
            func1(n - 1, mid, from, to);
        }
    }

    /**
     * 进阶递归的方式
     * @param arr
     * @return
     */
    public int step1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        
        return process(arr, arr.length - 1, 1, 2, 3);
    }

    private int process(int[] arr, int i, int from, int mid, int to) {
        if (i == -1) {
            return 0;
        }

        if (arr[i] != from && arr[i] != to) {
            return -1;
        }

        if (arr[i] == from) {
            return process(arr, i - 1, from, to, mid);
        } else {
            int rest = process(arr, i - 1, mid, from, to);
            if (rest == -1) {
                return -1;
            }
            return (1 << i) + rest;
        }
    }

    public int step2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int from = 1;
        int mid = 2;
        int to = 3;
        int i = arr.length - 1;
        int res = 0;
        int tmp = 0;
        while (i >= 0) {
            if (arr[i] != from && arr[i] != to) {
                return -1;
            }
            if (arr[i] == to) {
                res += 1<<i;
                tmp = from;
                from = mid;
            } else {
                tmp = to;
                to = mid;
            }
            mid = tmp;
            i--;
        }
        return res;
    }

}
