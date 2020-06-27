package com.sunft.chapter09_other;

/**
 * 邮局选址问题
 * <p>
 * 【题目】
 * 一条直线上有居民点， 邮局只能建在居民点上。 给定一个有序整
 * 型数组arr， 每个值表示居民点的一维坐标， 再给定一个正数num， 表
 * 示邮局数量。 选择num个居民点建立num个邮局， 使所有的居民点到
 * 邮局的总距离最短， 返回最短的总距离。
 * <p>
 * 【举例】
 * arr=[1， 2， 3， 4， 5， 1000]， num=2。
 * 第一个邮局建立在3位置， 第二个邮局建立在1000位置。 那么1位
 * 置到邮局距离为2， 2位置到邮局距离为1， 3位置到邮局距离为0， 4位
 * 置到邮局距离为1， 5位置到邮局距离为2， 1000位置到邮局距离为0。
 * 所以这种方案下的总距离为6， 其他任何方案的总距离都不会比该方
 * 案的总距离更短， 所以返回6。
 */
public class _34MinDistances {

    public int minDistances1(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }
        int[][] w = new int[arr.length + 1][arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                w[i][j] = w[i][j - 1] + arr[j] - arr[(i + j) / 2];
            }
        }
        int[][] dp = new int[num][arr.length];
        for (int j = 0; j != arr.length; j++) {
            dp[0][j] = w[0][j];
        }
        for (int i = 1; i < num; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k <= j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + w[k + 1][j]);
                }
            }
        }
        return dp[num - 1][arr.length - 1];
    }

    public int minDistances2(int[] arr, int num) {
        if (arr == null || num < 1 || arr.length < num) {
            return 0;
        }
        int[][] w = new int[arr.length + 1][arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                w[i][j] = w[i][j - 1] + arr[j] - arr[(i+j)/2];
            }
        }
        int[][] dp = new int[num][arr.length];
        int[][] s = new int[num][arr.length];
        for (int j = 0; j != arr.length; j ++) {
            dp[0][j] = w[0][j];
            s[0][j] = 0;
        }
        int minK = 0;
        int maxK = 0;
        int cur = 0;
        for (int i = 1; i < num; i++) {
            for (int j = arr.length - 1; j > i; j --) {
                minK = s[i - 1][j];
                maxK = j == arr.length - 1 ? arr.length - 1 : s[i][j+1];
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = minK; k <= maxK; k++) {
                    cur = dp[i - 1][k] + w[k + 1][j];
                    if (cur <= dp[i][j]) {
                        dp[i][j] = cur;
                        s[i][j] = k;
                    }
                }
            }
        }
        return dp[num - 1][arr.length - 1];
    }

}
