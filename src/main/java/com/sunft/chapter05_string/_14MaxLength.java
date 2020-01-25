package com.sunft.chapter05_string;

import java.util.Objects;

/**
 * 括号字符串的有效性和最长有效长度
 * <p>
 * 【题目】
 * 给定一个字符串str，判断是不是整体有效的括号字符串。
 * <p>
 * 【举例】
 * str="()"，返回true；str="(()())"，返回true；str="(())"，返回true。
 * str="())"。返回false；str="()("，返回false；str="()a()"，返回false。
 * <p>
 * 【补充题目】
 * 给定一个括号字符串str，返回最长的有效括号子串。
 * <p>
 * 【举例】
 * str="(()())"，返回6；str="())"，返回2；str="()(()()("，返回4。
 */
public class _14MaxLength {

    public boolean isValid(String str) {
        if (Objects.isNull(str) || "".equals(str)) {
            return false;
        }

        char[] chas = str.toCharArray();
        int status = 0;
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] != ')' && chas[i] != '(') {
                return false;
            }

            if (chas[i] == ')' && --status < 0) {
                return false;
            }

            if (chas[i] == '(') {
                status++;
            }
        }
        return status == 0;
    }

    public int maxLength(String str) {
        if (Objects.isNull(str) || "".equals(str)) {
            return 0;
        }

        char[] chas = str.toCharArray();
        int[] dp = new int[chas.length];
        int pre = 0;
        int res = 0;
        for (int i = 1; i < chas.length; i++) {
            if (chas[i] == ')') {
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && chas[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
