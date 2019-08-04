package com.sunft.chapter03_binarytree;

import java.util.Stack;

/**
 * 调整搜索二叉树中两个错误的结点
 * <p>
 * 【题目】
 * 一棵二叉树原本是搜索二叉树，但是其中有两个结点调换了位置，使得这棵二叉树
 * 不再是搜索二叉树，请找到这两个错误结点并返回。已知二叉树中所有结点的值都
 * 不一样，给定二叉树的头结点head，返回一个长度为2的二叉树的结点类型的数组
 * errs，errs[0]表示一个错误结点，errs[1]表示另一个错误结点。
 * <p>
 * 进阶：如果在原问题中得到了这两个错误结点，我们当然可以通过交换两个结点
 * 的结点值的方式让整颗二叉树重新成为二叉树。但现在要求你不能这么做，而是
 * 在结构上完全交换两个结点的位置，请实现调整的函数。
 */
public class _010GetTwoErrNodes<E extends Comparable<? super E>> {

    public Node<E>[] getTwoErrNodes(Node<E> head) {
        Node<E>[] errs = new Node[2];
        if (head == null) {
            return errs;
        }

        Stack<Node<E>> stack = new Stack<>();
        Node<E> pre = null;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (pre != null && (pre.e.compareTo(head.e) > 0)) {
                    errs[0] = errs[0] == null ? pre : errs[0];
                    errs[1] = head;
                }
                pre = head;
                head = head.right;
            }
        }
        return errs;
    }

    private Node[] getTwoErrParents(Node<E> head, Node<E> e1, Node<E> e2) {
        Node[] parents = new Node[2];
        if (head == null) {
            return parents;
        }

        Stack<Node<E>> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (head.left == e1 || head.right == e1) {
                    parents[0] = head;
                }

                if (head.left == e2 || head.right == e2) {
                    parents[1] = head;
                }
                head = head.right;
            }
        }
        return parents;
    }

    /**
     * 恢复算法
     *
     * @param head
     * @return
     */
    public Node<E> recoverTree(Node<E> head) {
        Node<E>[] errs = getTwoErrNodes(head);
        Node<E>[] parents = getTwoErrParents(head, errs[0], errs[1]);
        Node<E> e1 = errs[0];
        Node<E> e1P = parents[0];
        Node<E> e1L = e1.left;
        Node<E> e1R = e1.right;
        Node<E> e2 = errs[1];
        Node<E> e2P = parents[1];
        Node<E> e2L = e2.left;
        Node<E> e2R = e2.right;

        if (e1 == head) {
            if (e1 == e2P) {//情况1
                e1.left = e2L;
                e1.right = e2R;
                e2.right = e1;
                e2.left = e1L;
            } else if (e2P.left == e2) { //情况2
                e2P.left = e1;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            } else { //情况3
                e2P.right = e1;
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2L;
                e1.right = e2R;
            }
            head = e2;
        } else if (e2 == head) {
            if (e2 == e1P) {//情况4
                e2.left = e1L;
                e2.right = e1R;
                e1.left = e2;
                e1.right = e2R;
            } else if (e1P.left == e1) {//情况5
                e1P.left = e2;
                e1.left = e2L;
                e1.left = e1L;
                e2.right = e1R;
            } else {//情况6
                e1P.right = e2;
                e1.left = e2L;
                e1.right = e2R;
                e2.left = e1L;
                e2.right = e1R;
            }
            head = e1;
        } else {
            if (e1 == e2P) {
                if (e1P.left == e1) { //情况7
                    e1P.left = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1;
                } else { //情况8
                    e1P.right = e2;
                    e1.left = e2L;
                    e1.right = e2R;
                    e2.left = e1L;
                    e2.right = e1;
                }
            } else if (e2 == e1P) {
                if (e2P.left == e2) {//情况9
                    e2P.left = e1;
                    e2.left = e1L;
                    e2.right = e1R;
                    e1.left = e2;
                    e1.right = e2R;
                } else { //情况10
                    e2P.right = e1;
                    e2.left = e1L;
                    e2.right = e1R;
                    e1.left = e2;
                    e1.right = e2R;
                }
            } else {
                if (e1P.left == e1) {
                    if (e2P.left == e2) {//情况11
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.left = e2;
                        e2P.left = e1;
                    } else { //情况12
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.left = e2;
                        e2P.right = e1;
                    }
                } else {
                    if (e2P.left == e2) { //情况13
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.right = e2;
                        e2P.left = e1;
                    } else { //情况14
                        e1.left = e2L;
                        e1.right = e2R;
                        e2.left = e1L;
                        e2.right = e1R;
                        e1P.right = e2;
                        e2P.right = e1;
                    }
                }
            }
        }
        return head;
    }

    /**
     * 结点
     *
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
