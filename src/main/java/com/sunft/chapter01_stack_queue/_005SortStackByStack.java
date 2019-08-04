package com.sunft.chapter01_stack_queue;

import java.util.Stack;

/**
 * 用一个栈实现另一个栈的排序
 * 【题目】
 * 一个栈中元素的类型为是可比较的类型，现在想将该栈从顶到底按从大到小的顺序排序，
 * 只允许申请一个栈。除此之外，可以申请新的变量，但不能申请额外的数据结构。
 */
public class _005SortStackByStack<E extends Comparable<? super E>> {

    private Stack<E> stack;//待排序的栈

    public _005SortStackByStack() {
        stack = new Stack<>();
    }

    /**
     * 往待排序的栈中添加元素
     * @param e
     */
    public void add(E e) {
        stack.add(e);
    }

    /**
     * 对栈进行排序
     * @return
     */
    public Stack<E> sort() {
        //元素个数为0或者1直接返回
        if (stack.isEmpty() || stack.size() == 1) {
            return stack;
        }

        Stack<E> help = new Stack<>();

        while (!stack.isEmpty()) {
            E current = stack.pop();
            while (!help.isEmpty() && help.peek().compareTo(current) > 0) {
                stack.push(help.pop());
            }
            help.push(current);
        }

        /**
         * 反序入栈操作
         */
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }

        return stack;
    }

}
