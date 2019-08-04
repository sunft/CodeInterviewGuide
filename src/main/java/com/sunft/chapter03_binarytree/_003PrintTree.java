package com.sunft.chapter03_binarytree;

/**
 * 如何较为直观地打印二叉树
 *
 * 【题目】
 *  二叉树可以用常规的三种遍历结果来描述其结构，但是不够直观，尤其在二叉树中有重复值的时候，
 *  仅通过三种遍历的结果来构造二叉树的真实结构更是难上加难，有时根本不可能。给定一棵二叉树
 *  的头结点head，已经二叉树值的类型为32位整型，请实现一个打印二叉树的函数，可以直观地展示
 *  树的形状，也便于画出真实的结构。
 *
 * @param <E>
 */
public class _003PrintTree<E extends Comparable<? super E>> {

    /**
     * 以特定格式打印二叉树
     * @param head
     */
    public void printTree(Node<E> head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);//表示宽度为17
        System.out.println();
    }

    private void printInOrder(Node<E> head, int height, String to, int len) {
        if (head == null) {
            return;
        }

        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.e + to;
        int lenM = val.length();//计算值的占位数
        int lenL = (len - lenM) / 2; //计算左边应该填充的位数
        int lenR = len - lenM - lenL;//右边需要填充的位数
        val = getSpace(lenL) + val + getSpace(lenR);//要打印的值
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    private String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i ++) {
            buf.append(space);
        }
        return buf.toString();
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
