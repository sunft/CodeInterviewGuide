package com.sunft.chapter04_recurse_dp;

/**
 * 字符串的交错组成
 * 【题目】
 *  给定三个字符串str1、str2和aim，如果aim包含且仅包含来自str1和str2的所有字符，
 *  而且在aim中属于str1的字符之间保持原来在str1中的顺序，属于str2的字符之间保持
 *  原来在str2中的顺序，那么称aim是str1和str2的交错组成。实现一个函数，判断aim
 *  是否是str1和str2交错组成。
 *  【举例】
 *   str1="AB"，str2="12"。那么"AB12"、"A1B2"、"A12B"、"1A2B"和"1AB2"等都是str1
 *   和str2的交错组成。
 */
public class _010IsCross {

    public boolean isCross1(String str1, String str2, String aim) {
        if (str1 == null || str2 == null || aim == null) {
            return false;
        }

        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        char[] chaim = aim.toCharArray();
        if (chaim.length != ch1.length + ch2.length) {
            return false;
        }
        boolean[][] dp = new boolean[ch1.length + 1][ch2.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= ch1.length; i ++) {
            if (ch1[i - 1] != chaim[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }

        for (int j = 1; j <= ch2.length; j ++) {
            if (ch2[j - 1] != chaim[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }

        for (int i = 1; i <= ch1.length; i ++) {
            for (int j = 1; j <= ch2.length; j ++) {
                if ((ch1[j - 1] == chaim[i + j - 1] && dp[i - 1][j])
                || (ch2[j - 1] == chaim[i + j - 1] && dp[i][j])) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[ch1.length][ch2.length];
    }

    public boolean isCross2(String str1, String str2, String aim) {
        if (str1 == null || str2 == null || aim == null) {
            return false;
        }

        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        char[] chaim = aim.toCharArray();
        if (chaim.length != ch1.length + ch2.length) {
            return false;
        }
        char[] longs = ch1.length >= ch2.length ? ch1 : ch2;
        char[] shorts = ch1.length > ch2.length ? ch1 : ch2;
        boolean[] dp = new boolean[shorts.length + 1];

        dp[0] = true;
        for (int i = 1; i <= shorts.length; i ++) {
            if (shorts[i - 1] != chaim[i - 1]) {
                break;
            }
            dp[i] = true;
        }

        for (int i = 1; i <= longs.length; i ++) {
            dp[0] = dp[0] && longs[i - 1] == chaim[i - 1];
            for (int j = 1; j <= shorts.length; j ++) {
                if ((longs[i - 1] == chaim[i + j - 1] && dp[j])
                || (shorts[j - 1] == chaim[i + j - 1] && dp[j - 1])) {
                    dp[j] = true;
                } else {
                    dp[j] = false;
                }
            }
        }
        return dp[shorts.length];
    }

}
