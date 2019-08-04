package com.sunft.chapter01_stack_queue;

import java.util.Stack;

/**
 * 如何仅用递归函数和栈操作逆序一个栈
 * 【题目】
 * 一个栈依次压入1、2、3、4、5，那么从栈顶到栈底分别为5、4、3、2、1。将这个栈转置后，
 * 从栈顶到到栈底为1、2、3、4、5，也就是实现栈中元素的逆序，但是只能用递归函数实现，
 * 不能用其他数据结构
 */
public class _003ReserveStackWithRecursion01<E> {

    private Stack<E> stack;//用于存储数据

    /**
     * 无参构造
     * @param stack
     */
    public _003ReserveStackWithRecursion01(Stack<E> stack) {
        this.stack = stack;
    }

    /**
     * 移除并且返回栈底元素
     * @param stack
     * @return
     */
    public E getAndRemoveLastElement(Stack<E> stack) {
        E result = stack.pop();//获取栈顶元素
        if (stack.isEmpty()) {
            return result;
        } else {
            E last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    /**
     * 反转栈
     * @param stack
     */
    public void reserve(Stack<E> stack) {
        if (stack.isEmpty()) {
            return;
        }
        E i = getAndRemoveLastElement(stack);//获取栈底元素
        reserve(stack);
        stack.push(i);//入栈
    }

}
