package com.sunft.chapter03_binarytree;

import java.util.Stack;

/**
 * 分别用递归和非递归方式实现二叉树先序、中序和后序遍历
 * 【题目】
 *  用递归和非递归方式，分别按照二叉树先序、中序和后序打印所以的结点。
 *  我们约定先序遍历顺序为根、左、右；中序遍历顺序为左、根、右；后序遍历顺序为左右根
 *
 *  该类使用循环遍历二叉树：先序、中序、后序三种方式
 *
 *  用递归方法解决的问题都能用非递归的方法实现。这是因为递归方法无非就是利用函数栈
 *  来保存信息，如果用自己申请的数据结构来代替函数栈，也可以实现相同的功能。
 *
 * @param <E>
 */
public class _001TraverseBinaryTreeByLoop<E extends Comparable<? super E>> {

    /**
     * 使用循环先序遍历二叉树
     * @param head
     */
    public void preOrderUnRecur(Node<E> head) {
        System.out.print("pre-order:");
        if (head != null) {
            Stack<Node<E>> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.e + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }

                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    /**
     * 使用循环中序遍历二叉树
     * 
     * @param head
     */
    public void inOrderUnRecur(Node<E> head) {
        System.out.print("in-order:");
        if (head != null) {
            Stack<Node<E>> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.e + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    /**
     * 使用循环后序遍历二叉树
     * @param head
     */
    public void posOrderUnRecur1(Node<E> head) {
        System.out.print("pos-order:");
        if (head != null) {
            Stack<Node<E>> s1 = new Stack<>();
            Stack<Node<E>> s2 = new Stack<>();
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }

            while (!s2.isEmpty()) {
                System.out.print(s2.pop().e + " ");
            }
        }
        System.out.println();
    }

    /**
     * 使用循环后序遍历二叉树 方式2
     * @param head
     */
    public void posOrderUnRecur2(Node<E> head) {
        System.out.print("pos-order:");
        if (head != null) {
            Stack<Node<E>> stack = new Stack<>();
            stack.push(head);
            Node<E> c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && head != c.left && head != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && head != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().e + " ");
                }
            }
        }
        System.out.println();
    }

    /**
     * 结点
     * @param <E>
     */
    private static class Node<E extends Comparable<? super E>> {
        E e;//结点数据域
        Node<E> left; //左孩子
        Node<E> right; //右孩子

        public Node(E e) {
            this.e = e;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

}
