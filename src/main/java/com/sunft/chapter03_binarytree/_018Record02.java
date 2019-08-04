package com.sunft.chapter03_binarytree;

import java.util.HashMap;
import java.util.Map;

public class _018Record02<E extends Comparable<? super E>> {

    private Map<Node<E>, HashMap<Node, Node>> map;

    public _018Record02(Node<E> head) {
        map = new HashMap<>();
        initMap(head);
        setMap(head);
    }

    private void initMap(Node<E> head) {
        if (head == null) {
            return;
        }
        map.put(head, new HashMap<>());
        initMap(head.left);
        initMap(head.right);
    }

    private void setMap(Node<E> head) {
        if (head == null) {
            return;
        }
        headRecord(head.left, head);
        headRecord(head.right, head);
        subRecord(head);
        setMap(head.left);
        setMap(head.right);
    }

    private void headRecord(Node<E> n, Node<E> h) {
        if (n == null) {
            return;
        }
        map.get(n).put(h, h);
        headRecord(n.left, h);
        headRecord(n.right, h);
    }

    private void subRecord(Node<E> head) {
        if (head == null) {
            return;
        }
        preLeft(head.left, head.right, head);
        subRecord(head.left);
        subRecord(head.right);
    }

    private void preLeft(Node<E> left, Node<E> right, Node<E> head) {
        if (left == null) {
            return;
        }
        preRight(left, right, head);
        preLeft(left.left, right, head);
        preLeft(left.right, right, head);
    }

    private void preRight(Node<E> left, Node<E> right, Node<E> head) {
        if (right == null) {
            return;
        }
        map.get(left).put(right, head);
        preRight(left, right.left, head);
        preRight(left, right.right, head);
    }

    public Node<E> query(Node<E> o1, Node<E> o2) {
        if (o1 == o2) {
            return o1;
        }
        if (map.containsKey(o1)) {
            return map.get(o1).get(o2);
        }
        if (map.containsKey(o2)) {
            return map.get(o2).get(o1);
        }
        return null;
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
