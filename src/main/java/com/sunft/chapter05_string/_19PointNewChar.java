package com.sunft.chapter05_string;

/**
 * 找到被指的新类型字符
 * 【题目】
 * 新类型字符的定义如下：
 * 1. 新类型字符是长度为1或者2的字符串。
 * 2. 表现形式可以仅是小写字母，例如，"e"；也可以是大写字母+小写字母，例如，"Ab"；
 * 还可以是大写字母+大写字母，例如，"DC"。
 * <p>
 * 现在给定一个字符串str，str一定是若干新类型字符正确组合的结果。比如"eaCCBi"，
 * 由新类型字符"e"、"a"、"CC"和"Bi"拼成。再给定一个整数k，代表str中的位置。请
 * 返回被k位置指中的新类型字符。
 * <p>
 * 【举例】
 * str="aaABCDEcBCg"。
 * 1. k=7时，返回"Ec"。
 * 2. k=4时，返回"CD"。
 * 3. k=10时，返回"g"。
 */
public class _19PointNewChar {

    public String pointNewchar(String s, int k) {
        if (s == null || s.equals("") || k < 0 || k >= s.length()) {
            return "";
        }

        char[] chas = s.toCharArray();
        int uNum = 0;
        for (int i = k - 1; i >= 0; i--) {
            if (!Character.isUpperCase(chas[i])) {
                break;
            }
            uNum++;
        }
        if ((uNum & 1) == 1) {
            return s.substring(k - 1, k + 1);
        }
        if (Character.isUpperCase(chas[k])) {
            return s.substring(k, k + 2);
        }
        return String.valueOf(chas[k]);
    }

}
