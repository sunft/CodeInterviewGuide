package com.sunft.chapter04_recurse_dp;

/**
 * 表达式得到期望结果的组成种数
 *
 * 【题目】
 *  给定一个只由0(假)、1(真)、&(逻辑与)、|(逻辑或)和^(异或)五种字符组成的
 *  字符串express，再给定一个布尔值desired。返回express能有多少种组合方式，
 *  可以达到desired的结果。
 *
 *  【举例】
 *   express="1^0|0|1"，desired=false。
 *   只有1^((0|0|1))和1^(0|(0|1))的组合可以得到false，返回2。
 *   express="1"，desired=false。
 *   无组合则可以得到false，返回0.
 */
public class _13Num {

    public boolean isValid(char[] exp) {
        //表达式必须是奇数
        if ((exp.length & 1) == 0) {
            return false;
        }

        //奇数位必须是数字
        for (int i = 0; i < exp.length; i = i + 2) {
            if ((exp[i] != '1') && (exp[i] != '0')) {
                return false;
            }
        }

        //偶数位必须是对应的符号
        for (int i = 1; i < exp.length; i = i + 2) {
            if ((exp[i] != '&') && (exp[i] != '|') && (exp[i] != '^')) {
                return false;
            }
        }

        return true;
    }

    public int num1(String express, boolean desired) {
        if (express == null || express.equals("")) {
            return 0;
        }

        char[] exp = express.toCharArray();
        if (!isValid(exp)) {
            return 0;
        }

        return p(exp, desired, 0, exp.length - 1);
    }

    /**
     * 暴力递归法
     * @param exp
     * @param desired
     * @param l
     * @param r
     * @return
     */
    private int p(char[] exp, boolean desired, int l, int r) {
        if (l == r) {
            if (exp[l] == '1') {
                return desired ? 1 : 0;
            } else {
                return desired ? 0 : 1;
            }
        }

        int res = 0;
        if (desired) {
            for (int i = l + 1; i < r; i += 2) {
                switch (exp[i]) {
                    case '&':
                        res += p(exp, true, l, i - 1) * p(exp, true, i + 1, r);
                        break;
                    case '|':
                        res += p(exp, true, l, i - 1) * p(exp, false, i + 1, r);
                        res += p(exp, false, l, i - 1) * p(exp, true, i + 1, r);
                        res += p(exp, true, l, i - 1) * p(exp, true, i + 1, r);
                        break;
                    case '^':
                        res += p(exp, true, l, i - 1) * p(exp, false, i + 1, r);
                        res += p(exp, false, l, i - 1) * p(exp, true, i + 1, r);
                        break;
                }
            }
        } else {
            for (int i = l + 1; i < r; i += 2) {
                switch (exp[i]) {
                    case '&':
                        res += p(exp, false, l, i - 1) * p(exp, true, i + 1, r);
                        res += p(exp, true, l, i - 1) * p(exp, false, i + 1, r);
                        res += p(exp, false, l, i - 1) * p(exp, false, i + 1, r);
                        break;
                    case '|':
                        res += p(exp, false, l, i - 1) * p(exp, false, i + 1, r);
                        break;
                    case '^':
                        res += p(exp, true, l, i - 1) * p(exp, true, i + 1, r);
                        res += p(exp, false, l, i - 1) * p(exp, false, i + 1, r);
                        break;

                }
            }
        }
        return res;
    }

    /**
     * 动态规划法
     * @param express
     * @param desired
     * @return
     */
    public int num2(String express, boolean desired) {
        if (express == null || express.equals("")) {
            return 0;
        }

        char[] exp = express.toCharArray();
        if (!isValid(exp)) {
            return 0;
        }

        int[][] t = new int[exp.length][exp.length];
        int[][] f = new int[exp.length][exp.length];
        t[0][0] = exp[0] == '0' ? 0 : 1;
        f[0][0] = exp[0] == '1' ? 0 : 1;
        for (int i = 2; i < exp.length; i += 2) {
            t[i][i] = exp[i] == '0' ? 0 : 1;
            f[i][i] = exp[i] == '1' ? 0 : 1;
            for (int j = i - 2; j >= 0; j -= 2) {
                for (int k = j; k < i; k += 2) {
                    if (exp[k + 1] == '&') {
                        t[j][i] += t[j][k] + t[k + 2][i];
                        f[j][i] += (f[j][k] + t[j][k]) * f[k + 2][i] + f[j][k] * t[k + 2][i];
                    } else if (exp[k + 1] == '|') {
                        t[j][i] += (f[j][k] + t[j][k]) * t[k + 2][i] + t[j][k] * f[k + 2][i];
                        f[j][i] += f[j][k] * f[k + 2][i];
                    } else {
                        t[j][i] += f[j][k] * t[k + 2][i] + t[j][k] * f[k + 2][i];
                        f[j][i] += f[j][k] * f[k + 2][i] + t[j][k] * t[k + 2][i];
                    }
                }
            }
        }
        return desired ? t[0][t.length - 1] : f[0][f.length - 1];
    }

}
