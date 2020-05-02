package com.sunft.chapter09_other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 正数数组的最小不可组成和
 * <p>
 * 【题目】
 * 给定一个正数数组arr， 其中所有的值都为整数， 以下是最小不可组成和的概念：
 * ● 把arr每个子集内的所有元素加起来会出现很多值， 其中最小的记为min， 最大的记为max。
 * ● 在区间[min， max]上， 如果有数不可以被arr某一个子集相加得到， 那么其中最小的那个数是arr的最小不可组成和。
 * ● 在区间[min， max]上， 如果所有的数都可以被arr的某一个子集相加得到， 那么max+1是arr的最小不可组成和。
 * <p>
 * 请写函数返回正数数组arr的最小不可组成和。
 * <p>
 * 【举例】
 * arr=[3， 2， 5]。 子集{2}相加产生2为min， 子集{3， 2， 5}相加产生10为max。 在区间[2， 10]上， 4、 6和9不能被任何子集相加得到，
 * 其中4是arr的最小不可组成和。
 * <p>
 * arr=[1， 2， 4]。 子集{1}相加产生1为min， 子集{1， 2， 4}相加产生7为max。 在区间[1， 7]上， 任何数都可以被子集相加得到， 所以8
 * 是arr的最小不可组成和。
 * <p>
 * 【 进阶题目】
 * 如果已知正数数组arr中肯定有1这个数， 是否能更快地得到最小不可组成和？
 */
public class _14UnformedSum {

    public int unformedSum1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 1;
        }
        Set<Integer> set = new HashSet<>();
        process(arr, 0, 0, set);//收集所有子集的和
        int min = Integer.MAX_VALUE;
        for (int i = 0; i != arr.length; i++) {
            min = Math.min(min, arr[i]);
        }
        for (int i = min + 1; i != Integer.MIN_VALUE; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return 0;
    }

    private void process(int[] arr, int i, int sum, Set<Integer> set) {
        if (i == arr.length) {
            set.add(sum);
            return;
        }
        process(arr, i + 1, sum, set);//包含当前数arr[i]的情况
        process(arr, i + 1, sum + arr[i], set);//不包含当前数arr[i]的情况
    }

    public int unformedSum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 1;
        }
        int sum = 0;
        int min = Integer.MIN_VALUE;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            min = Math.min(min, arr[i]);
        }
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i != arr.length; i++) {
            for (int j = sum; j >= arr[i]; j--) {
                dp[j] = dp[j - arr[i]] || dp[j];
            }
        }

        for (int i = min; i != dp.length; i++) {
            if (!dp[i]) {
                return i;
            }
        }
        return sum + 1;

    }

    public int unformedSum3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);//把arr排序
        int range = 0;
        for (int i = 0; i != arr.length; i++) {
            if (arr[i] > range + 1) {
                return range + 1;
            } else {
                range += arr[i];
            }
        }
        return range + 1;
    }

}
