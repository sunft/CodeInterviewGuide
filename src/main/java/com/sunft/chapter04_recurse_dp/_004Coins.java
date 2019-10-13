package com.sunft.chapter04_recurse_dp;

/**
 * 换钱的方法数
 * 【题目】
 *  给定数组arr，arr中所有的值都为正数且不重复。每个值代表一种面值的货币，
 *  每种面值的货币可以使用任意张，再给定一个整数aim代表要找的钱数，求换钱
 *  有多少种方法。
 *  【举例】
 *   arr=[5, 10, 25, 1]，aim=0。
 *   组成0元的方法有1种，就是所有面值的货币都不用。所以返回1。
 *   arr=[5, 10, 25, 1]， aim=15。
 *   组成15元的方法有6种，分别为3张5元、1张10元+1张5元、1张10元+5张1元、
 *   10张1元+1张5元、2张5元+5元1元和15张1元。所以返回6。
 *   arr=[3, 5]，aim=2。
 *   任何方法都无法组成2元。所以返回0。
 */
public class _004Coins {

    /**
     * 暴力破解法
     * @param arr
     * @param aim
     * @return
     */
    public static int coins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        
        return process1(arr, 0, aim);
    }

    private static int process1(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i ++) {
                System.out.println("index = " + index + "; i = " + i);
                res += process1(arr, index + 1, aim - arr[index] * i);
            }
        }
        return res;
    }

    /**
     * 方式二：记忆化搜索
     * @param arr
     * @param aim
     * @return
     */
    public static int coin2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        int[][] map = new int[arr.length + 1][aim + 1];
        return process2(arr, 0, aim, map);
    }

    private static int process2(int[] arr, int index, int aim, int[][] map) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            int mapValue = 0;
            for (int i = 0; arr[index] * i <= aim; i ++) {
                mapValue = map[index + 1][aim - arr[index] * i];
                if (mapValue != 0) {
                    res += mapValue == -1 ? 0 : mapValue;
                } else {
                    res += process2(arr, index + 1, aim - arr[index] * i, map);
                }
            }
        }
        map[index][aim] = res == 0 ? -1 : res;
        return res;
    }

    public static int coins3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i ++) {
            dp[i][0] = 0;
        }
        for (int j = 1; arr[0] * j <= aim; j ++) {
            dp[0][arr[0] * j] = 1;
        }
        int num = 0;
        for (int i = 1; i < arr.length; i ++) {
            for (int j = 1; j <= aim; j ++) {
                num = 0;
                for (int k = 0; j - arr[i] * k >= 0; k ++) {
                    num += dp[i-1][j - arr[i] * k];
                }
                dp[i][j] = num;
            }
        }
        return dp[arr.length - 1][aim];
    }

    public static int coins4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i ++) {
            dp[i][0] = 1;
        }
        for (int j = 1; arr[0] * j <= aim; j ++) {
            dp[0][arr[0] * j] = 1;
        }
        for (int i = 1; i < arr.length; i ++) {
            for (int j = 1; j <= aim; j ++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j -arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }
        return dp[arr.length - 1][aim];
    }

    /**
     * 矩阵空间压缩技巧
     * @param arr
     * @param aim
     */
    public static int coins5(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[] dp = new int[aim + 1];
        for (int j = 0; arr[0] * j <= aim; j ++) {
            dp[arr[0] * j] = 1;
        }

        for (int i = 1; i < arr.length; i ++) {
            for (int j = 1; j <= arr.length; j ++) {
                dp[j] += j - arr[i] >= 0 ? dp[j - arr[i]] : 0;
            }
        }
        return dp[aim];
    }

}
