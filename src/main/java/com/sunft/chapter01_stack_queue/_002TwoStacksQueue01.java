package com.sunft.chapter01_stack_queue;

import java.util.Stack;

/**
 * 由两个栈组成的队列
 * 【题目】编写一个类，用两个栈实现，支持队列的基本操作(add、poll、peek)
 * 【解答】自己的实现方式：每次入队时，往有数据的栈中压；每次出队列时，先把数据移到另一个栈中，取栈顶元素，再把数据移动到另一个栈中
 */
public class _002TwoStacksQueue01<E> {

    private Stack<E> stack1;
    private Stack<E> stack2;

    public _002TwoStacksQueue01() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /**
     * 将数据压入有数据的栈中，第一次压入stackPush中
     * @param e 待压入元素
     */
    public void add(E e) {
        if (stack2.isEmpty()) {
            stack1.push(e);
        } else {
            stack2.push(e);
        }
    }

    /**
     * 删除队列中的元素，注意取出之后要还原
     * @return
     */
    public E poll() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            throw new RuntimeException("the Queue is Empty.");
        }
        move();//将一个栈的底部变成另一个栈的栈顶元素
        E e;//待返回元素
        if (stack2.isEmpty()) {
            e = stack1.pop();
        } else {
            e = stack2.pop();
        }
        move();//执行还原操作
        return e;
    }

    /**
     * 返回队列中的元素，但是不执行删除操作，同样要注意还原
     * @return
     */
    public E peek() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            throw new RuntimeException("the Queue is Empty.");
        }
        move();//将一个栈的底部变成另一个栈的栈顶元素
        E e;//待返回元素
        if (stack2.isEmpty()) {
            e = stack1.peek();
        } else {
            e = stack2.peek();
        }
        move();//执行还原操作
        return e;
    }

    /**
     * 把有数据的栈中的元素移动到没有数据的栈中
     */
    private void move() {
        if (stack1.isEmpty()) {
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        } else {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }

    public static void main(String[] args) {

    }

}
