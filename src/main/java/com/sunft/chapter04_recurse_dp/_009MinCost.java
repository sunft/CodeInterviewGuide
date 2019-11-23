package com.sunft.chapter04_recurse_dp;

/**
 * 最小编辑代价
 *
 * 【题目】
 *  给定两个字符串str1和str2，再给定三个整数ic、dc和rc，分别代表插入、删除和
 *  替换一个字符的代价，返回将str1编辑成str2的最小代价。
 *
 *  【举例】
 *   str1="abc"，str2="adc"，ic=5， dc=3，rc=2。
 *   从"abc"编辑成"adc"，把'b'替换成'd'是代价最小的，所以返回2。
 *   str1="abc"，str2="adc"，ic=5，dc=3，rc=100。
 *   从"abc"编辑成"adc"，先删除'b'，然后插入'd'是代价最小的，所以返回8。
 *   str="abc"，str2="abc"，ic=5，dc=3，rc=2。
 *   不用编辑了，本来就是一样的字符串，所以返回0。
 */
public class _009MinCost {

    public int minCost1(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str2 == null) {
            return 0;
        }

        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int row = chs1.length + 1;
        int col = chs2.length + 1;
        int[][] dp = new int[row][col];
        for (int i = 1; i < row; i ++) {
            dp[i][0] = dc * i;
        }
        for (int j = 1; j < col; j ++) {
            dp[0][j] = ic * j;
        }
        for (int i = 1; i < row; i ++) {
            for (int j = 1; j < col; j ++) {
                if (chs1[i - 1] == chs2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
            }
        }
        return dp[row - 1][col - 1];
    }

    public int minCost2(String str1, String str2, int ic, int dc, int rc){
        if (str1 == null || str2 == null) {
            return 0;
        }
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
        char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
        if (chs1.length < chs2.length) {//str2较长就交换ic和dc的值
            int tmp = ic;
            ic = dc;
            dc = tmp;
        }
        int[] dp = new int[shorts.length + 1];
        for (int i = 1; i <= shorts.length; i ++) {
            dp[i] = ic * i;
        }
        for (int i = 1; i <= longs.length; i ++) {
            int pre = dp[0];//pre表示左上角的值
            dp[0] = dc * i;
            for (int j = 1; j <= shorts.length; j ++) {
                int tmp = dp[j]; //dp[j]没更更新前先保存下来
                if (longs[i - 1] == shorts[j - 1]) {
                    dp[j] = pre;
                } else {
                    dp[j] = pre + rc;
                }
                dp[j] = Math.min(dp[j], dp[j - 1] + ic);
                dp[j] = Math.min(dp[j], tmp + dc);
                pre = tmp; //pre变成dp[j]没有更新前的值
            }
        }
        return dp[shorts.length];
    }

}