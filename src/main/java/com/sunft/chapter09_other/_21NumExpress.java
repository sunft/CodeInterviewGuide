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
            StringBuilder res = new StringBuilder();
            if (num < 0) {
                res = new StringBuilder("Negative ");
            }
            if (num == Integer.MIN_VALUE) {
                res.append("Two Billion, ");
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
                    res.append(num1To999(cur));
                    res.append(names[highIndex]).append(num == 0 ? " " : ", ");
                }
                high /= 1000;
                highIndex++;
            }
            return res.toString();
        }
    }

    public static class NumToChinese {
        public String num1To9(int num) {
            if (num < 1 || num > 9) {
                return "";
            }
            String[] names = {"一", "二", "三", "四", "五", "六", "七", "八", "九"};
            return names[num - 1];
        }

        public String num1To99(int num, boolean hasBai) {
            if (num < 1 || num > 99) {
                return "";
            }
            if (num < 10) {
                return num1To9(num);
            }
            int shi = num / 10;
            if (shi == 1 && (!hasBai)) {
                return "十" + num1To9(num % 10);
            } else {
                return num1To9(shi) + "十" + num1To9(num % 10);
            }
        }

        public String num1To999(int num) {
            if (num < 1 || num > 999) {
                return "";
            }
            if (num < 100) {
                return num1To99(num, false);
            }
            String res = num1To9(num / 100) + "百";
            int rest = num % 100;
            if (rest == 0) {
                return res;
            } else if (rest >= 10) {
                res += num1To99(rest, true);
            } else {
                res += "零" + num1To9(rest);
            }
            return res;
        }

        public String num1To9999(int num) {
            if (num < 1 || num > 9999) {
                return "";
            }
            if (num < 1000) {
                return num1To999(num);
            }
            String res = num1To9(num / 1000) + "千";
            int rest = num % 1000;
            if (rest == 0) {
                return res;
            } else if (rest >= 100) {
                res += num1To999(rest);
            } else {
                res += "零" + num1To99(rest, false);
            }
            return res;
        }

        public String num1To99999999(int num) {
            if (num < 1 || num > 99999999) {
                return "";
            }
            int wan = num / 10000;
            int rest = num % 10000;
            if (wan == 0) {
                return num1To9999(rest);
            }
            String res = num1To999(wan) + "万";
            if (rest == 0) {
                return res;
            } else {
                if (rest < 1000) {
                    return res + "零" + num1To999(rest);
                } else {
                    return res + num1To999(rest);
                }
            }
        }

        public String getNumChiExp(int num) {
            if (num == 0) {
                return "零";
            }
            String res = num < 0 ? "负" : "";
            int yi = Math.abs(num / 100000000);
            int rest = Math.abs(num % 100000000);
            if (yi == 0) {
                return res + num1To99999999(rest);
            }
            res += num1To9999(yi) + "亿";
            if (rest == 0) {
                return res;
            } else {
                if (rest < 10000000) {
                    return "零" + num1To99999999(rest);
                } else {
                    return res + num1To99999999(rest);
                }
            }
        }
    }

}
