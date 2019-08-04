package com.sunft.chapter01_stack_queue;

import java.util.Stack;

/**
 * 思路二的实现
 */
public class _001MyStack02<E extends Comparable<? super E>> {

    private Stack<E> stackValue;//用于存储数据
    private Stack<E> stackMin;  //栈顶用于存储当前stackValue中的最小值

    /**
     * 实例化两个栈
     */
    public  _001MyStack02() {
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
            E min = getMin();
            if (e.compareTo(min) >= 0) {
                stackMin.push(min);
            } else {
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
        stackMin.pop();//同步移除
        return stackValue.pop();
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
