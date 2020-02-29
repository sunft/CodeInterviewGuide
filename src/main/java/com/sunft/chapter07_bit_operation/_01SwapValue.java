package com.sunft.chapter07_bit_operation;

/**
 * 不用额外变量交换两个整数的值
 *
 * 【题目】
 * 如何不用任何额外变量交换两个整数的值？
 */
public class _01SwapValue {

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a = " + a + "; b = " + b);
    }
    
}
