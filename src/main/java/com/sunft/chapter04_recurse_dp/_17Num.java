package com.sunft.chapter04_recurse_dp;

/**
 * N皇后问题
 *
 * 【题目】
 *  N皇后问题是指在N×N的棋盘上要摆N个皇后，要求任何两个皇后不同行、不同列，
 *  也不在同一条斜线上。给定一个整数n，返回n皇后的摆法有多少种。
 *
 *  【举例】
 *   n，返回1。
 *   n=2或3,2皇后和3皇后问题无论怎么摆都不行，返回0。
 *   n=8，返回92。
 *
 */
public class _17Num {

    public int num1(int n) {
        if (n < 1) {
            return 0;
        }

        int[] record = new int[n];
        return process1(0, record, n);
    }

    private int process1(int i, int[] record, int n) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j ++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    private boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k ++) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;
            }
        }
        return true;
    }

    public int num2(int n) {
        //因为本方法中为运算的载体是int型变量，所以该方法只能算1~32皇后问题
        //如果想计算更多的皇后问题，需使用包含更多位的变量
        if (n < 1 || n > 32) {
            return 0;
        }

        int upperLim = n == 32 ? -1 : (1<<n) - 1;
        return process2(upperLim, 0, 0, 0);
    }

    private int process2(int upperLim, int colLim, int leftDiaLim, int rightDiaLim) {
        if (colLim == upperLim) {
            return 1;
        }

        int pos = 0;
        int mostRightOne = 0;
        pos = upperLim & (~(colLim | leftDiaLim | rightDiaLim));
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(upperLim, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1,
                    (rightDiaLim | mostRightOne) >>> 1);
        }

        return res;
    }

}
