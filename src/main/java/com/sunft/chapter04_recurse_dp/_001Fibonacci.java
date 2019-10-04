package com.sunft.chapter04_recurse_dp;

/**
 * 斐波那契系列问题的递归和动态规划
 *
 * 【题目】
 *  给定整数N，返回斐波那契数列的第N项。
 *
 * 【补充题目1】
 *  给定整数N，代表台阶数，一次可以跨2个或者1个台阶，返回有多少种走法。
 *
 * 【补充题目2】
 *  假设农场中成熟的母牛每年只会生1头小母牛，并且永远不会死。第一年农场有
 *  1只成熟的母牛，从第二年开始生小母牛。每只小母牛3年之后成熟又可以生小
 *  母牛。给定整数N，求出N年后牛的数量。
 *
 *  【要求】
 *   对以上所有的问题，请实现时间复杂度O(logN)的解法。
 */
public class _001Fibonacci {

    /**
     * 斐波那契数列的递归求解法
     * @param n 整数值
     * @return
     */
    public int f1(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    public int f2(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        int res = 1;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i ++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    /**
     * 斐波那契数列矩阵求解法
     * @param n
     * @return
     */
    public int f3(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }

    /**
     * 求矩阵m的p次方
     * @param m 矩阵
     * @param p 次方数
     * @return
     */
    public int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        //先把res设为单位矩阵，相当于整数中的1
        for (int i = 0; i < res.length; i ++) {
            res[i][i] = 1;
        }

        int[][] tmp = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = multiMatrix(res, tmp);
            }
            tmp = multiMatrix(res, tmp);
        }
        return res;
    }

    /**
     * 两个矩阵相乘的具体实现
     * @param m1
     * @param m2
     * @return
     */
    private int[][] multiMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m2[0].length; i ++) {
            for (int j = 0; j < m1.length; j ++) {
                for (int k = 0; k < m2.length; k ++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    /**
     * 补充问题1
     * @param n
     * @return
     */
    public int s1(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return n;
        }

        return s1(n - 1) + s1(n - 2);
    }

    public int s2(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return n;
        }

        int res = 2;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i ++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    public int s3(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return n;
        }

        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];

    }

    /**
     * 补充问题2
     * @param n
     * @return
     */
    public int c1(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2 || n == 3) {
            return n;
        }

        return c1(n - 1) + c1(n - 3);
    }

    public int c2(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2 || n == 3) {
            return n;
        }

        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 4; i <= n; i ++) {
            tmp1 = res;
            tmp2 = pre;
            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }

    public int c3(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2 || n == 3) {
            return n;
        }

        int[][] base = {{1, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        int[][] res = matrixPower(base, n - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }

}
