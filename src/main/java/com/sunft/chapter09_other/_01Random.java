package com.sunft.chapter09_other;

/**
 * 从5随机到7随机及其扩展
 * <p>
 * 【题目】
 * 给定一个等概率随机产生1～5的随机函数rand1To5如下：
 * public int rand1To5() {
 * return (int) (Math.random() * 5) + 1;
 * }
 * 除此之外， 不能使用任何额外的随机机制， 请用rand1To5实现等
 * 概率随机产生1～7的随机函数rand1To7。
 * <p>
 * 【补充题目】
 * 给定一个以p概率产生0， 以1-p概率产生1的随机函数rand01p如
 * 下：
 * public int rand01p() {// 可随意改变p
 * double p = 0.83;
 * return Math.random() < p ? 0 : 1;
 * }
 * 除此之外， 不能使用任何额外的随机机制， 请用rand01p实现等
 * 概率随机产生1～6的随机函数rand1To6。
 * <p>
 * 【进阶题目】
 * 给定一个等概率随机产生1～M的随机函数rand1ToM如下：
 * public int rand1ToM(int m) {
 * return (int) (Math.random() * m) + 1;
 * }
 * 除此之外， 不能使用任何额外的随机机制。 有两个输入参数， 分
 * 别为m和n， 请用rand1ToM(m)实现等概率随机产生1～n的随机函数
 * rand1ToN。
 */
public class _01Random {
}
