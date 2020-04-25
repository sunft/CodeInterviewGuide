package com.sunft.chapter09_other;

/**
 * 蓄水池算法
 *
 * 【题目】
 * 有一个机器按自然数序列的方式吐出球（1号球， 2号球， 3号球， ……） ， 你有一个袋子， 袋子最多只能装下K个球， 并且除袋子
 * 以外， 你没有更多的空间。 设计一种选择方式， 使得当机器吐出第N号球的时候（N>K） ， 你袋子中的球数是K个， 同时可以保证从1号
 * 球到N号球中的每一个， 被选进袋子的概率都是K/N。 举一个更具体的例子， 有一个只能装下10个球的袋子， 当吐出100个球时， 袋子里
 * 有10个球， 并且1～100号中的每一个球被选中的概率都是10/100。 然后继续吐球， 当吐出1000个球时， 袋子里有10个球， 并且1～1000号
 * 中的每一个球被选中的概率都是10/1000。 继续吐球， 当吐出i个球时， 袋子里有10个球， 并且1～i号中的每一个球被选中的概率都是
 * 10/i， 即吐球的同时， 已经吐出的球被选中的概率也动态地变化。
 */
public class _07GetKNumsRand {

    public int rand(int max) {
        return (int) (Math.random() * max) + 1;
    }

    public int[] getKNumsRand(int k, int max) {
        if (max < 1 || k < 1) {
            return null;
        }
        int[] res = new int[Math.min(k, max)];
        for (int i = 0; i != res.length; i++) {
            res[i] = i + 1; //前K个数直接进袋子
        }
        for (int i = k + 1; i < max + 1; i++) {
            if (rand(i) <= k) {//决定i进不进栈
                res[rand(k) - 1] = i;//i随机替掉袋子中的一个
            }
        }
        return res;
    }

}
