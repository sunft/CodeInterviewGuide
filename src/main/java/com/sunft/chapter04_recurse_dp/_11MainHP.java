package com.sunft.chapter04_recurse_dp;

/**
 * 龙与地下城游戏问题
 * 【题目】
 *  给定一个二维数组map，含义是一张地图，例如，如下矩阵：
 *  -2  -3  3
 *  -5  -10 1
 *  0   30  -5
 *  游戏的规则如下：
 *  1) 骑士从左上角出发，每次只能向右或向下走，最后到达右下角见到公主。
 *  2) 地图中每个位置的值代表骑士要遭遇的事情。如果是负数，说明此处有
 *     怪兽，要让骑士损失血量。如果是非负数，代表此处有血瓶，能让骑士回血。
 *  3) 骑士从左上角到右下角的过程中，走到任何一个位置，血量都不能少于1。
 *
 *  为了保证骑士能见到公主，初始血量至少是多少？根据map，返回初始血量。
 */
public class _11MainHP {

    public int minHP1(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 1;
        }

        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row--][col--];
        dp[row][col] = m[row][col] > 0 ? 1 : -m[row][col] + 1;
        for (int j = col - 1; j >= 0; j --) {
            dp[row][j] = Math.max(dp[row][j + 1] - m[row][j], 1);
        }
        int right = 0;
        int down = 0;
        for (int i = row - 1; i >= 0; i --) {
            dp[i][col] = Math.max(dp[i + 1][col] - m[i][col], 1);
            for (int j = col - 1; j >= 0; j --) {
                right = Math.max(dp[i][j + 1] - m[i][j], 1);
                down = Math.max(dp[i + 1][j] - m[i][j], 1);
                dp[i][j] = Math.min(right, down);
            }
        }
        return dp[0][0];
    }

    public static int minHP2(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 1;
        }

        int more = Math.max(m.length, m[0].length);
        int less = Math.min(m.length, m[0].length);
        boolean rowmore = more == m.length;
        int[] dp = new int[less];
        int tmp = m[m.length - 1][m[0].length - 1];
        dp[less - 1] = tmp > 0 ? 1 : -tmp + 1;
        int row = 0;
        int col = 0;
        for (int j = less - 2; j >= 0; j --) {
            row = rowmore ? more - 1 : j;
            col = rowmore ? j : more - 1;
            dp[j] = Math.max(dp[j + 1] - m[row][col], 1);
        }
        int choosen1 = 0;
        int choosen2 = 0;
        for (int i = more - 2; i >= 0; i --) {
            row = rowmore ? i : less - 1;
            col = rowmore ? less - 1 : i;
            dp[less - 1] = Math.max(dp[less - 1] - m[row][col], 1);
            for (int j = less - 2; j >= 0; j --) {
                row = rowmore ? i : j;
                col = rowmore ? j : i;
                choosen1 = Math.max(dp[j] - m[row][col], 1);
                choosen2 = Math.max(dp[j + 1] - m[row][col], 1);
                dp[j] = Math.min(choosen1, choosen2);
            }
        }
        return dp[0];
    }

}
