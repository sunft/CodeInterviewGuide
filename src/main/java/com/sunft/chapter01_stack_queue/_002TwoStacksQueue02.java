package com.sunft.chapter01_stack_queue;

import java.util.Stack;

/**
 *  * 由两个栈组成的队列
 *  * 【题目】编写一个类，用两个栈实现，支持队列的基本操作(add、poll、peek)
 *  * 【解答】按照书本的逻辑
 * @param <E>
 */
public class _002TwoStacksQueue02<E> {

    private Stack<E> stackPush;//压入数据的栈
    private Stack<E> stackPop; //弹出数据的栈

    public _002TwoStacksQueue02() {
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    /**
     * 待入队列的元素
     * @param e 待入栈元素
     */
    public void add(E e) {
        stackPush.push(e);
    }

    /**
     * 删除队列头部元素
     * @return
     */
    public E poll() {

        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new RuntimeException("the queue is empty!");
        }

        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }

        return stackPop.pop();
    }

    /**
     * 返回队列头部元素，不删除
     * @return
     */
    public E peek() {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new RuntimeException("the queue is empty!");
        }

        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }

        return stackPop.peek();
    }
}
