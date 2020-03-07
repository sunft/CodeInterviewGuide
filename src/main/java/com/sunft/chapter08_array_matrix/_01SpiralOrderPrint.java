package com.sunft.chapter08_array_matrix;

/**
 * 转圈打印矩阵
 * <p>
 * 【题目】
 * 给定一个整型矩阵matrix，请按照转圈的方式打印它。
 * 例如：
 * 1    2   3   4
 * 5    6   7   8
 * 9    10  11  12
 * 13   14  15  16
 * 打印结果为：1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10
 * <p>
 * 【要求】
 * 额外空间复杂度为O(1)
 * <p>
 * 【解答】
 * 矩阵分圈处理
 */
public class _01SpiralOrderPrint {

    /**
     * 矩阵分圈处理
     *
     * @param matrix 二维数组
     */
    public void spiralOrderPrint(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (tR <= dR && tC <= dC) {
            printEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    /**
     * 打印矩阵的最外层
     *
     * @param m  矩阵
     * @param tR
     * @param tC
     * @param dR
     * @param dC
     */
    private void printEdge(int[][] m, int tR, int tC, int dR, int dC) {
        if (tR == dR) {//子矩阵只有一行时
            for (int i = tC; i <= dC; i++) {
                System.out.print(m[tR][i] + " ");
            }
        } else if (tC == dC) {//子矩阵只有一列时
            for (int i = tR; i <= dR; i++) {
                System.out.print(m[i][tC] + " ");
            }
        } else {//一般情况
            int curC = tC;
            int curR = tR;
            while (curC != dC) {
                System.out.print(m[tR][curC] + " ");
                curC++;
            }
            while (curR != dR) {
                System.out.print(m[curR][dC] + " ");
                curR++;
            }
            while (curC != tC) {
                System.out.print(m[dR][curC] + " ");
                curC--;
            }
            while (curR != tR) {
                System.out.print(m[curR][tC] + " ");
                curR--;
            }
        }
    }

}
