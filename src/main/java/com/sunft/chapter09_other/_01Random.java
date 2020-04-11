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

    public int rand1To5() {
        return (int) (Math.random() * 5) + 1;
    }

    public int rand1To7() {
        int num = 0;
        do {
            num = (rand1To5() - 1) * 5 + rand1To5() - 1;
        } while (num > 20);
        return num % 7 + 1;
    }

    public int rand01p() {
        //可随意改变p
        double p = 0.83;
        return Math.random() < p ? 0 : 1;
    }

    public int rand01() {
        int num;
        do {
            num = rand01p();
        } while (num == rand01p());
        return num;
    }

    public int rand0To3() {
        return rand01() * 2 + rand01();
    }

    public int rand1To6() {
        int num = 0;
        do {
            num = rand0To3() * 4 + rand0To3();
        } while (num > 11);
        return num % 6 + 1;
    }

    public int rand1ToM(int m) {
        return (int) (Math.random() * m) + 1;
    }

    public int rand1ToN(int n, int m) {
        int[] nMSys = getMSysNum(n - 1, m);
        int[] randNum = getRanMSysNumLessN(nMSys, m);
        return getNumFromMSysNum(randNum, m) + 1;
    }

    // 把value转成m进制数
    private int[] getMSysNum(int value, int m) {
        int[] res = new int[32];
        int index = res.length - 1;
        while (value != 0) {
            res[index--] = value % m;
            value = value / m;
        }
        return res;
    }

    // 等概率随机产生一个0~nMsys范围的数，只不过是用m进制表达的
    private int[] getRanMSysNumLessN(int[] nMSys, int m) {
        int[] res = new int[nMSys.length];
        int start = 0;
        while (nMSys[start] == 0) {
            start++;
        }

        int index = start;
        boolean lastEqual = true;
        while (index != nMSys.length) {
            res[index] = rand1ToM(m) - 1;
            if (lastEqual) {
                if (res[index] > nMSys[index]) {
                    index = start;
                    lastEqual = true;
                    continue;
                } else {
                    lastEqual = res[index] == nMSys[index];
                }
            }
            index++;
        }

        return res;
    }

    private int getNumFromMSysNum(int[] mSysNum, int m) {
        int res = 0;
        for (int i = 0; i != mSysNum.length; i++) {
            res = res * m + mSysNum[i];
        }
        return res;
    }

}
