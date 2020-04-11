package com.sunft.chapter09_other;

/**
 * 一行代码求两个数的最大公约数
 * <p>
 * 【题目】
 * 给定两个不等于0的整数M和N， 求M和N的最大公约数。
 */
public class _02Gcd {

    public int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }

}
