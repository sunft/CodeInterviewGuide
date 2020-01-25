package com.sunft.chapter05_string;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 公式字符串求值
 * <p>
 * 【题目】
 * 给定一个字符串str，str表示一个公式，公式里可能有整数、加减乘除符号和左右括号，
 * 返回公式的计算结果。
 * <p>
 * 【举例】
 * str="48*((70-65)-43)+8*1"，返回-1816。
 * str="3+1*4"，返回7。
 * str="3+(1*4)"，返回7。
 * 【说明】
 * 1. 可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查。
 * 2. 如果是负数，就需要用括号括起来，比如"4*(-3)"。但如果负数作为公式的开头或
 * 括号部分的开头，则可以没有括号，比如"-3*4"和"(-3*4)"都是合法的。
 * 3. 不用考虑计算过程中会发生溢出的情况。
 */
public class _15GetValue {

    public int getValue(String exp) {
        return value(exp.toCharArray(), 0)[0];
    }

    private int[] value(char[] chars, int i) {
        Deque<String> deq = new LinkedList<>();
        int pre = 0;
        int[] bra = null;
        while (i < chars.length && chars[i] != ')') {
            if (chars[i] >= '0' && chars[i] <= '9') {
                pre = pre * 10 + chars[i++] - '0';
            } else if (chars[i] != '(') {
                addNum(deq, pre);
                deq.addLast(String.valueOf(chars[i++]));
                pre = 0;
            } else {
                bra = value(chars, i + 1);
                pre = bra[0];
                i = bra[1] + 1;
            }
        }
        addNum(deq, pre);
        return new int[]{getNum(deq), i};
    }

    private void addNum(Deque<String> deq, int num) {
        if (!deq.isEmpty()) {
            int cur = 0;
            String top = deq.pollLast();
            if ("+".equals(top) || "-".equals(top)) {
                deq.addLast(top);
            } else {
                cur = Integer.valueOf(deq.pollLast());
                num = "*".equals(top) ? (cur * num) : (cur / num);
            }
        }
        deq.addLast(String.valueOf(num));
    }

    private int getNum(Deque<String> deq) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!deq.isEmpty()) {
            cur = deq.pollFirst();
            if ("+".equals(cur)) {
                add = true;
            } else if ("-".equals(cur)) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

}

