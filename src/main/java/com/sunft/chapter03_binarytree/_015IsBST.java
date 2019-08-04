package com.sunft.chapter03_binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断一棵二叉树是否为搜索二叉树和完全二叉树
 *
 * 【题目】
 *  给定一个二叉树的头结点head，已知其中没有重复值的结点，实现两个函数判断这棵二叉树是否是
 *  搜索二叉树和完全二叉树
 */
public class _015IsBST<E extends Comparable<? super E>> {

    public boolean isBST(Node<E> head) {
        if (head == null) {
            return true;
        }

        boolean res = true;
        Node<E> pre = null;
        Node<E> cur1 = head;
        Node<E> cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }

                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            if (pre != null && (pre.e.compareTo(cur1.e) > 0)) {
                res = false;
            }
            pre = cur1;
            cur1 = cur1.right;
        }

        return res;
    }

    public boolean isCBT(Node<E> head) {
        if (head == null) {
            return true;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        boolean leaf = false;
        Node<E> l = null;
        Node<E> r = null;
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }

            if (l != null) {
                queue.offer(l);
            }

            if (r != null) {
                queue.offer(r);
            } else {
                leaf = true;
            }
        }

        return true;
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
