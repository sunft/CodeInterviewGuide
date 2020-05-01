package com.sunft.chapter09_other;

/**
 * 最大的leftMax与rightMax之差的绝对值
 * <p>
 * 【题目】
 * 给定一个长度为N（N>1） 的整型数组arr， 可以划分成左右两个
 * 部分， 左部分为arr[0..K]， 右部分为arr[K+1..N-1]， K可以取值的范围
 * 是[0， N-2]。 求这么多划分方案中， 左部分中的最大值减去右部分最
 * 大值的绝对值中， 最大是多少？
 * 例如： [2， 7， 3， 1， 1]， 当左部分为[2， 7]， 右部分为[3， 1， 1]
 * 时， 左部分中的最大值减去右部分最大值的绝对值为4。 当左部分为
 * [2， 7， 3]， 右部分为[1， 1]时， 左部分中的最大值减去右部分最大值
 * 的绝对值为6。 还有很多划分方案， 但最终返回6。
 */
public class _09MaxABS {

    public int maxABS1(int[] arr) {
        int res = Integer.MIN_VALUE;
        int maxLeft = 0;
        int maxRight = 0;
        for (int i = 0; i != arr.length - 1; i++) {
            maxLeft = Integer.MIN_VALUE;
            for (int j = 0; j != i + 1; j++) {
                maxLeft = Math.max(arr[j], maxLeft);
            }
            maxRight = Integer.MIN_VALUE;

            for (int j = i + 1; j != arr.length; j++) {
                maxRight = Math.max(arr[j], maxRight);
            }
            res = Math.max(Math.abs(maxLeft - maxRight), res);
        }
        return res;
    }

    public int maxABS2(int[] arr) {
        int[] lArr = new int[arr.length];
        int[] rArr = new int[arr.length];
        lArr[0] = arr[0];
        rArr[arr.length - 1] = arr[arr.length - 1];
        for (int i = 1; i < arr.length; i++) {
            lArr[i] = Math.max(lArr[i - 1], arr[i]);
        }
        for (int i = arr.length - 2; i > -1; i--) {
            rArr[i] = Math.max(rArr[i + 1], arr[i]);
        }
        int max = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            max = Math.max(max, Math.abs(lArr[i] -rArr[i + 1]));
        }
        return max;
    }

    public int maxABS3(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(arr[i], max);
        }
        return max - Math.min(arr[0], arr[arr.length - 1]);
    }

}
