package com.sunft.chapter03_binarytree;

/**
 * 【题目】
 *  给定彼此独立的两棵树头结点分别为t1和t2，判断t1树是否包含
 *  t2树的全部的拓扑结构。
 *
 * @param <E>
 */
public class _011Contains<E extends Comparable<? super E>> {

    public boolean contains(Node<E> t1, Node<E> t2) {
        return check(t1, t2) || contains(t1.left, t2) || contains(t1.right, t2);
    }

    private boolean check(Node<E> h, Node<E> t2) {
        if (t2 == null) {
            return true;
        }

        if (h == null || (h.e.compareTo(t2.e) != 0)) {
            return false;
        }

        return check(h.left, t2.left) && check(h.right, t2.right);
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
