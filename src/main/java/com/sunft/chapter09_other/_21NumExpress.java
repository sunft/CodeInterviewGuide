package com.sunft.chapter09_other;

/**
 * 数字的英文表达和中文表达
 * <p>
 * 【题目】
 * 给定一个32位整数num， 写两个函数分别返回num的英文与中文表达字符串。
 * <p>
 * 【举例】
 * num=319
 * 英文表达字符串为： Three Hundred Nineteen
 * 中文表达字符串为： 三百一十九
 * <p>
 * num=1014
 * 英文表达字符串为： One Thousand， Fourteen
 * 中文表达字符串为： 一千零十四
 * <p>
 * num=-2147483648
 * 英文表达字符串为： Negative， Two Billion， One Hundred Forty
 * Seven Million， Four Hundred Eighty Three Thousand， Six Hundred Forty Eight
 * 中文表达字符串为： 负二十一亿四千七百四十八万三千六百四十八
 * <p>
 * num=0
 * 英文表达字符串为： Zero
 * 中文表达字符串为： 零
 */
public class _21NumExpress {

    public static class NumToEnglish {
        public static String num1To19(int num) {
            if (num < 1 || num > 19) {
                return "";
            }
            String[] names = {"One ", "Two ", "Three ", "Four ", "Five ", "Six ",
                    "Seven ", "Eight ", "Nine ", "Ten ", "Eleven ", "Twelve ",
                    "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Seventeen ",
                    "Eighteen ", "Nineteen "};
            return names[num - 1];
        }

        public static String num1To99(int num) {
            if (num < 1 || num > 99) {
                return "";
            }
            if (num < 20) {
                return num1To19(num);
            }
            int high = num / 10;
            String[] tyNames = {"Twenty ", "Thirty ", "Forth ", "Fifty ",
                    "Sixty ", "Seventy ", "Eighty ", "Ninety "};
            return tyNames[high - 2] + num1To19(num % 10);
        }

        public static String num1To999(int num) {
            if (num < 1 || num > 999) {
                return "";
            }
            if (num < 100) {
                return num1To99(num);
            }
            int high = num / 100;
            return num1To19(high) + "Hundred " + num1To99(num % 100);
        }

        public static String getNumEngExp(int num) {
            if (num == 0) {
                return "Zero";
            }
            String res = "";
            if (num < 0) {
                res = "Negative ";
            }
            if (num == Integer.MIN_VALUE) {
                res += "Two Billion, ";
                num %= -2000000000;
            }
            num = Math.abs(num);
            int high = 1000000000;
            int highIndex = 0;
            String[] names = {"Billion", "Million", "Thousand", ""};
            while (num != 0) {
                int cur = num / high;
                num %= high;
                if (cur != 0) {
                    res += num1To999(cur);
                    res += names[highIndex] + (num == 0 ? " " : ", ");
                }
                high /= 1000;
                highIndex++;
            }
            return res;
        }
    }

    public static class NumToChinese {
        public String num1To9(int num) {
            if (num < 1 || num > 0) {
                return "";
            }
            String[] names = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};
            return names[num - 1];
        }
    }

}
