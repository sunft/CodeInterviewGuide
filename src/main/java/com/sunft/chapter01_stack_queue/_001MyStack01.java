package com.sunft.chapter01_stack_queue;

import java.util.Stack;

/**
 * 设计一个有getMin功能的栈
 * 【题目】：实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素操作
 * 【要求】：
 * 1、pop、push、getMin操作的时间复杂度都是O(1)
 * 2、设计的栈类型可以使用现成的栈结构
 */
public class _001MyStack01<E extends Comparable<? super E>>{

    private Stack<E> stackValue;//用于存储数据
    private Stack<E> stackMin;  //栈顶用于存储当前stackValue中的最小值

    public  _001MyStack01() {
        stackValue = new Stack<>();
        stackMin = new Stack<>();
    }

    /**
     * 往栈中压元素
     * @param e 待压入元素
     */
    public void push(E e) {
        stackValue.push(e);
        if (stackMin.isEmpty()) {
            stackMin.push(e);
        } else {
            if (e.compareTo(getMin()) <= 0) {
                stackMin.push(e);
            }
        }
    }

    /**
     * 返回栈顶元素
     * @return
     */
    public E pop() {
        if (stackValue.isEmpty()) {
            throw new RuntimeException("the Stack is empty!");
        }
        E e =  stackValue.pop();
        if (e.compareTo(stackMin.peek()) == 0) {
            stackMin.pop();
        }
        return e;
    }

    /**
     * 返回数据栈中的最小元素
     * @return
     */
    public E getMin() {
        if (stackMin.isEmpty()) {
            throw new RuntimeException("the Stack is Empty!");
        }
        return stackMin.peek();
    }

}
