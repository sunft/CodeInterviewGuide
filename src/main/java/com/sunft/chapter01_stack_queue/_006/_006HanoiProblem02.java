package com.sunft.chapter01_stack_queue._006;

import java.util.Stack;

/**
 * 使用栈的方式实现汉诺塔
 */
public class _006HanoiProblem02 {

    public int hanoiProblem2(int num, String left, String mid, String right) {
        Stack<Integer> ls = new Stack<>();//左边的栈
        Stack<Integer> ms = new Stack<>();//中间的栈
        Stack<Integer> rs = new Stack<>();//右边的栈
        ls.push(Integer.MAX_VALUE);//压入最大的整数
        ms.push(Integer.MAX_VALUE);//压入最大的整数
        rs.push(Integer.MAX_VALUE);//压入最大的整数
        for (int i = num; i > 0; i --) {
            ls.push(i);
        }
        Action[] record = {Action.NO};
        int step = 0;
        while(rs.size() != num + 1) {
            step += fStackToStack(record, Action.MToL, Action.LToM, ls, ms, left, mid);
            step += fStackToStack(record, Action.LToM, Action.MToL, ms, ls, mid, left);
            step += fStackToStack(record, Action.RToM, Action.MToR, ms, rs, mid, left);
            step += fStackToStack(record, Action.MToR, Action.RToM, rs, ms, right, mid);
        }
        return step;
    }

    private int fStackToStack(Action[] record, Action preNoAct, Action nowAct, Stack<Integer> fStack, Stack<Integer> tStack, String from, String to) {
        if (record[0] != preNoAct && fStack.peek() < tStack.peek()) {
            tStack.push(fStack.pop());
            System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);
            record[0] = nowAct;
            return 1;
        }
        return 0;
    }

}
