package com.sunft.chapter05_string;

/**
 * 替换字符串中连续出现的指定字符串
 *
 * 【题目】
 *  给定三个字符串str、from和to，把str中所有from的子串全部替换成to字符串，
 *  对连续出现的from的部分要求只替换成一个to字符串，返回最终的结果字符串。
 *
 *  【举例】
 *   str="123abc"，from="abc"，to="4567"，返回"1234567"。
 *   str="123"，from="abc"，to="456"，返回"123"。
 *   str="123abcabc"，from="abc"，to="X"，返回"123X"。
 */
public class _06Replace {

    public String replace(String str, String from, String to) {
        if (str == null || from == null || "".equals(str) || "".equals(from)) {
            return str;
        }
        
        char[] chas = str.toCharArray();
        char[] chaf = from.toCharArray();
        int match = 0;
        for (int i = 0; i < chas.length; i ++) {
            if (chas[i] == chaf[match ++]) {
                if (match == chaf.length) {
                    clear(chas, i, chaf.length);
                    match = 0;
                }
            } else {
                match = 0;
            }
        }
        String res = "";
        String cur = "";
        for (int i = 0; i < chas.length; i ++) {
            if (chas[i] != 0) {
                cur = cur + String.valueOf(chas[i]);
            }
            if (chas[i] == 0 && (i == 0 || chas[i - 1] != 0)) {
                res = res + cur + to;
                cur = "";
            }
        }
        if (!cur.equals("")) {
            res = res + cur;
        }
        return res;
    }

    private void clear(char[] chas, int end, int len) {
        while (len -- != 0) {
            chas[end --] = 0;
        }
    }

}
