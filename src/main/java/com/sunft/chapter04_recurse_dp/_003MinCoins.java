package com.sunft.chapter04_recurse_dp;

/**
 * 换钱的最少货币数
 *
 * 【题目】
 *  给定数组arr，arr中所有的值都为正数且不重复。每个值代表一种面值的货币，
 *  每种面值的货币可以使用任意张，再给定一个整数aim代表要找的钱数，求组成
 *  aim的最少货币数。
 *  【补充题目】
 *   给定数组arr，arr中所有的值都为正数。每个值仅代表一张钱的面值，再给定
 *   一个整数aim代表要找的钱数，求组成aim的最少货币数。
 *
 */
public class _003MinCoins {

    public int minCoins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }

        int n = arr.length;
        int max = Integer.MAX_VALUE;
        int[][] dp = new int[n][aim + 1];
        for (int j = 1; j <= aim; j ++) {
            dp[0][j] = max;
            if (j - arr[0] >= 0 && dp[0][j -arr[0]] != max) {
                dp[0][j] = dp[0][j - arr[0]] + 1;
            }
        }
        int left = 0;
        for (int i = 1; i < n; i ++) {
            for (int j = 1; j <= aim; j ++) {
                left = max;
                if (j - arr[i] >= 0 && dp[i][j - arr[i]] != max) {
                    left = dp[i][j - arr[i]] + 1;
                }
                dp[i][j] = Math.min(left, dp[i - 1][j]);
            }
        }

        return dp[n - 1][aim] != max ? dp[n - 1][aim] : -1;
    }

    public int minCoins2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }

        int n = arr.length;
        int max = Integer.MAX_VALUE;
        int[] dp = new int[aim + 1];
        for (int j = 1; j <= aim; j ++) {
            dp[j] = max;
            if (j - arr[0] >= 0 && dp[j - arr[0]] != max) {
                dp[j] = dp[j -arr[0]] + 1;
            }
        }

        int left = 0;
        for (int i = 1; i < n; i ++) {
            for (int j = 1; j <= aim; j ++) {
                left = max;
                if (j - arr[i] >= 0 && dp[j - arr[i]] != max) {
                    left = dp[j - arr[i]] + 1;
                }
                dp[j] = Math.min(left, dp[j]);
            }
        }
        return dp[aim] != max ? dp[aim] : -1;

    }

    public int minCoins3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }

        int n = arr.length;
        int max = Integer.MAX_VALUE;
        int[][] dp = new int[n][aim + 1];
        for (int j = 1; j <= aim; j ++) {
            dp[0][j] = max;
        }

        if (arr[0] <= aim) {
            dp[0][arr[0]] = 1;
        }

        //左上角某个位置的值
        int leftup = 0;
        for (int i = 1; i < n; i ++) {
            for (int j = 1; j <= aim; j ++) {
                leftup = max;
                if (j - arr[i] >= 0 && dp[i - 1][j -arr[i]] != max) {
                    leftup = dp[i - 1][j - arr[i]] + 1;
                }
                dp[i][j] = Math.min(leftup, dp[i - 1][j]);
            }
        }
        return dp[n - 1][aim] != max ? dp[n - 1][aim] : -1;
    }

    public int minCoins4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return -1;
        }

        int n = arr.length;
        int max = Integer.MAX_VALUE;
        int[] dp = new int[aim + 1];
        for (int j = 1; j <= aim; j ++) {
            dp[j] = max;
        }

        if (arr[0] <= aim) {
            dp[arr[0]] = 1;
        }

        int leftup = 0;//左上角某个位置的值

        for (int i = 1; i < n; i ++) {
            for (int j = 1; j > 0; j --) {
                leftup = max;
                if (j - arr[i] >= 0 && dp[j - arr[i]] != max) {
                    leftup = dp[j - arr[i]] + 1;
                }
                dp[j] = Math.min(leftup, dp[j]);
            }
        }

        return dp[aim] != max ? dp[aim] : -1;
    }

}
