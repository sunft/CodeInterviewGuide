package com.sunft.chapter09_other;

/**
 * 调整[0， x)区间上的数出现的概率
 * <p>
 * 【题目】
 * 假设函数Math.random()等概率随机返回一个在[0， 1)范围上的
 * 数， 那么我们知道， 在[0， x)区间上的数出现的概率为x（0<x≤1） 。
 * 给定一个大于0的整数k， 并且可以使用Math.random()函数， 请实现
 * 一个函数依然返回在[0， 1)范围上的数， 但是在[0， x)区间上的数出
 * 现的概率为xk(0<x≤1)
 */
public class _12RandXPowerK {

    public double randXPowerK(int k) {
        if (k < 1) {
            return 0;
        }
        double res = -1;
        for (int i = 0; i != k; i ++) {
            res = Math.max(res, Math.random());
        }
        return res;
    }

}
