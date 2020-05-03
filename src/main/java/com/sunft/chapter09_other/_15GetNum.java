package com.sunft.chapter09_other;

/**
 * 一种字符串和数字的对应关系
 * 【题目】
 * 一个char类型的数组chs， 其中所有的字符都不同。
 * 例如， chs=['A' ， 'B' ， 'C' ， … 'Z' ]， 则字符串与整数的对应关系
 * 如下：
 * A， B… Z， AA， AB…AZ， BA， BB...ZZ， AAA... ZZZ， AAAA...
 * 1， 2…26， 27， 28… 52， 53， 54…702， 703...18278， 18279...
 * <p>
 * 例如， chs=['A' ， 'B' ， 'C' ]， 则字符串与整数的对应关系如下：
 * A， B， C， AA， AB...CC， AAA...CCC， AAAA...
 * 1， 2， 3， 4， 5 …12， 13 ... 39， 40...
 * 给定一个数组chs， 实现根据对应关系完成字符串与整数相互转换的两个函数。
 */
public class _15GetNum {

    public String getString(char[] chs, int n) {
        if (chs == null || chs.length == 0 || n < 1) {
            return "";
        }
        int cur = 1;
        int base = chs.length;
        int len = 0;
        while (n >= cur) {
            len++;
            n -= cur;
            cur *= base;
        }
        char[] res = new char[len];
        int index = 0;
        int nCur = 0;
        do {
            cur /= base;
            nCur = n / cur;
            res[index++] = getKthCharAtChs(chs, nCur + 1);
            n %= cur;
        } while (index != res.length);
        return String.valueOf(res);
    }

    private char getKthCharAtChs(char[] chs, int k) {
        if (k < 1 || k > chs.length) {
            return 0;
        }
        return chs[k - 1];
    }

    public int getNum(char[] chs, String str) {
        if (chs == null || chs.length == 0) {
            return 0;
        }
        char[] strc = str.toCharArray();
        int base = chs.length;
        int cur = 1;
        int res = 0;
        for (int i = strc.length - 1; i != -1; i--) {
            res += getNthFromChar(chs, strc[i]) * cur;
            cur *= base;
        }
        return res;
    }

    private int getNthFromChar(char[] chs, char ch) {
        int res = -1;
        for (int i = 0; i != chs.length; i++) {
            if (chs[i] == ch) {
                res = i + 1;
                break;
            }
        }
        return res;
    }

}
