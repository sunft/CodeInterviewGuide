package com.sunft.chapter08_array_matrix;

/**
 * 将正方形矩阵顺时针转动90°
 * 【题目】
 * 给定一个N×N的矩阵matrix，把这个矩阵调整成顺时针转动90°后的形式。
 * 例如：
 * 1    2   3   4
 * 5    6   7   8
 * 9    10  11  12
 * 13   14  15  16
 * <p>
 * 顺时针转动90°后为：
 * 13   9   5   1
 * 14   10  6   2
 * 15   11  7   3
 * 16   12  8   4
 * <p>
 * 【要求】
 * 额外空间复杂度为O(1)。
 */
public class _02Rotate {

    /**
     * 使用矩阵分圈处理的方式
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int tR = 0;
        int tC = 0;
        int dR = matrix.length - 1;
        int dC = matrix[0].length - 1;
        while (tR < dR) {
            rotateEdge(matrix, tR++, tC++, dR--, dC--);
        }
    }

    private void rotateEdge(int[][] m, int tR, int tC, int dR, int dC) {
        int times = dC - tC;//times就是总的组数
        int tmp = 0;
        for (int i = 0; i != times; i++) {//一次循环就是一组占据调整
            tmp = m[tR][tC + 1];
            m[tR][tC + i] = m[dR - i][tC];
            m[dR - i][tC] = m[dR][dC - i];
            m[dR][dC - i] = m[tR + i][dC];
            m[tR + i][dC] = tmp;
        }
    }

}
