package com.sunft.chapter07_bit_operation;

/**
 * 在其他数都出现偶数次的数组中找到出现奇数次的数
 * <p>
 * 【题目】
 * 给定一个整型数组arr，其中只有一个数出现了奇数次，其他的数都出现了偶数次，打印这个数
 * <p>
 * 【进阶】
 * 有两个数出现了奇数次，其他的数都出现了偶数次，打印这个数。
 * <p>
 * 【要求】
 * 时间复杂度为O(N)，额外空间复杂度为O(1)。
 */
public class _05PrintOddTimesNum {

    public void printOddTimesNum1(int[] arr) {
        int eO = 0;
        for (int cur : arr) {
            eO ^= cur;
        }
        System.out.println(eO);
    }

    public void printOddTimesNum2(int[] arr) {
        int eO = 0, eOhasOne = 0;
        for (int curNum : arr) {
            eO ^= curNum;
        }
        int rightOne = eO & (~eO + 1);
        for (int cur : arr) {
            if ((cur & rightOne) != 0) {
                eOhasOne ^= cur;
            }
        }
        System.out.println(eOhasOne + " " + (eO ^ eOhasOne));
    }

}
