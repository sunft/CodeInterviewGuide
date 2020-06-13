package com.sunft.chapter09_other;

/**
 * 【题目】
 * 给定一个字符串str， 返回str中最长回文子串的长度。
 * <p>
 * 【举例】
 * str="123"， 其中的最长回文子串为"1"、 "2"或者"3"， 所以返回1。
 * str="abc1234321ab"， 其中的最长回文子串为"1234321"， 所以返回7。
 * <p>
 * 【进阶题目】
 * 给定一个字符串str， 想通过添加字符的方式使得str整体都变成回
 * 文字符串， 但要求只能在str的末尾添加字符， 请返回在str后面添加的
 * 最短字符串。
 * <p>
 * 【举例】
 * str="12"。 在末尾添加"1"之后， str变为"121"， 是回文串。 在末
 * 尾添加"21"之后， str变为"1221"， 也是回文串。 但"1"是所有添加方案
 * 中最短的， 所以返回"1"。
 * <p>
 * 【要求】如果str的长度为N， 解决原问题和进阶问题的时间复杂度都达
 * 到O(N)。
 */
public class _30ManacherAlgorithm {

    private char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public int maxLcpsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];
        int index = -1;
        int pR = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i != charArr.length; i++) {
            pArr[i] = pR > i ? Math.min(pArr[2 * index - 1], pR - i) : 1;
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    public String shortestEnd(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];
        int index = -1;
        int pR = -1;
        int maxContainsEnd = -1;
        for (int i = 0; i != charArr.length; i++) {
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]]) {
                    pArr[i] ++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }
            if (pR == charArr.length) {
                maxContainsEnd = pArr[i];
                break;
            }
        }
        char[] res = new char[str.length() - maxContainsEnd + 1];
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = charArr[i * 2 + 1];
        }
        return String.valueOf(res);
    }

}
