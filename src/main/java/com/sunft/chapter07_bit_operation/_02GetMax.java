package com.sunft.chapter07_bit_operation;

/**
 * 不用任何比较判断找出两个数中较大的数
 * <p>
 * 【题目】
 * 给定两个32为整数a和b，放回a和b中较大的。
 * <p>
 * 【要求】
 * 不用任何比较判断。
 * <p>
 * 【解答】
 * 第一种方法。得到a-b的值的符号。
 */
public class _02GetMax {

    /**
     * 如果n为1，返回0；如果n为0，返回1。
     *
     * @param n
     * @return
     */
    public int flip(int n) {
        return n ^ 1;
    }

    /**
     * 返回整数n的符号
     *
     * @param n
     * @return
     */
    public int sign(int n) {
        return flip((n >> 31) & 1);
    }

    /**
     * 存在值溢出的情况
     *
     * @param a
     * @param b
     * @return
     */
    public int getMax1(int a, int b) {
        //获取两个数之差
        int c = a - b;
        //获得符号位
        int scA = sign(c);
        int scB = flip(scA);
        return a * scA + b * scB;
    }

    /**
     * 彻底解决溢出的问题
     *
     * @param a
     * @param b
     * @return
     */
    public int getMax2(int a, int b) {
        int c = a - b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        int difSab = sa ^ sb;
        int sameSab = flip(difSab);
        int returnA = difSab * sa - sameSab * sc;
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }

}
