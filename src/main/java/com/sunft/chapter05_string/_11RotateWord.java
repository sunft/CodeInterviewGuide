package com.sunft.chapter05_string;

/**
 * 翻转字符串
 *
 * 【题目】
 *  给定一个字符类型的数组chas，请在单词间做逆序调整。只要做到单词顺序逆序即可，
 *  对空格的位置没有特殊要求。
 *
 *  【举例】
 *   如果把chas看作字符串为"dog loves pig"，调整成"pig Loves dog"。
 *   如果把chas看作字符串为"I'm a student."，调整成"student. a I'm"。
 *
 *  【补充题目】
 *   给定一个字符类型的数组chas和一个整数size，请把大小为size的左半边区整体移动到
 *   右半区，左半区整体移动到左边。
 *
 *  【举例】
 *    如果把chas看作字符串为"ABCDE"，size=2，调整成"DEABC"。
 *
 *  【要求】
 *    如果chas长度为N，两道题都要求时间复杂度为O(N)，额外空间复杂度为O(1)。
 *
 */
public class _11RotateWord {

    public void rotateWord(char[] chas) {
        if (chas == null || chas.length == 0) {
            return;
        }
        reverse(chas, 0, chas.length - 1);
        int l = -1;
        int r = -1;
        for (int i = 0; i < chas.length; i ++) {
            if (chas[i] != ' ') {
                l = i == 0 || chas[i - 1] == ' '?i:1;
                r = i == chas.length - 1 || chas[i + 1] == ' ' ? i : r;
            }
            if (l != -1 && r != -1) {
                reverse(chas, 1, r);
                l = -1;
                r = -1;
            }
        }
    }

    private void reverse(char[] chas, int start, int end) {
        char tmp = 0;
        while (start < end) {
            tmp = chas[start];
            chas[start] = chas[end];
            chas[end] = tmp;
            start ++;
            end --;
        }
    }

    public void rotate1(char[] chas, int size) {
        if (chas == null || size <= 0 || size >= chas.length) {
            return;
        }
        reverse(chas, 0, size - 1);
        reverse(chas, size, chas.length - 1);
        reverse(chas, 0, chas.length - 1);
    }
    
    public void rotate2(char[] chas, int size) {
        if (chas == null || size <= 0 || size >= chas.length) {
            return;
        }
        int start = 0;
        int end = chas.length - 1;
        int lpart = size;
        int rpart = chas.length - size;
        int s = Math.min(lpart, rpart);
        int d = lpart - rpart;
        while (true) {
            exchange(chas, start, end, s);
            if (d == 0) {
                break;
            } else if (d > 0){
                start += s;
                lpart = d;
            } else {
                end -= s;
                rpart = -d;
            }
            s = Math.min(lpart, rpart);
            d = lpart - rpart;
        }
    }

    private void exchange(char[] chas, int start, int end, int size) {
        int i = end - size + 1;
        char tmp = 0;
        while (size -- != 0) {
            tmp = chas[start];
            chas[start] = chas[i];
            chas[i] = tmp;
            start ++;
            i ++;
        }
    }

}
