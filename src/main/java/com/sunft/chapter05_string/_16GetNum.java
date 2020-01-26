package com.sunft.chapter05_string;

/**
 * 0左边必有1的二进制字符串数量
 * <p>
 * 【题目】
 * 给定一个整数N，求由"0"字符与"1"字符组成的长度为N的所有字符串中，满足"0"字
 * 符的左边必有"1"字符的字符串数量。
 * <p>
 * 【举例】
 * N=1。只由"0"与"1"组成，长度为1的所有字符串："0"、"1"。只有字符串"1"满足要求，所以返回1。
 * N=2。只由"0"与"1"组成，长度为2的所有字符串："00"、"01"、"10"、"11"。只有字符串"10"和"11"满足要求，所以返回2.
 * N=3。只由"0"与"1"组成，长度为3的所有字符串："000"、"001"、"010"、"011"、"100"、"101"、"110"、"111"。字符串"101"、
 * "110"、"111"满足要求，所以返回3。
 */
public class _16GetNum {

    public int getNum1(int n) {
        if (n < 1) {
            return 0;
        }
        return process(1, n);
    }

    private int process(int i, int n) {
        if (i == n - 1) {
            return 2;
        }

        if (i == n) {
            return 1;
        }

        return process(i + 1, n) + process(i + 2, n);
    }

    public int getNum2(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        int pre = 1;
        int cur = 1;
        int tmp = 0;
        for (int i = 2; i < n + 1; i++) {
            tmp = cur;
            cur += pre;
            pre = tmp;
        }
        return cur;
    }

    public int getNum3(int n) {
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

    private int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = m;
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = multiMatrix(res, tmp);
            }
            tmp = multiMatrix(tmp, tmp);
        }
        return res;
    }

    private int[][] multiMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m2[0].length; i++) {
            for (int j = 0; j < m1.length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

}
