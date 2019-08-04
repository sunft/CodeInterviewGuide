package com.sunft.chapter03_binarytree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _018Record01<E extends Comparable<? super E>> {

    private Map<Node<E>, Node<E>> map;
    
    public _018Record01(Node<E> head) {
        map = new HashMap<>();
        if (head != null) {
            map.put(head, null);
        }
        setMap(head);
    }

    private void setMap(Node<E> head) {
        if (head == null) {
            return;
        }

        if (head.left != null) {
            map.put(head.left, head);
        }

        if (head.right != null) {
            map.put(head.right, head);
        }
        setMap(head.left);
        setMap(head.right);
    }

    public Node<E> query(Node<E> o1, Node<E> o2) {
        Set<Node<E>> path = new HashSet<>();
        while (map.containsKey(o1)) {
            path.add(o1);
            o1 = map.get(o1);
        }

        while (!path.contains(o2)) {
            o2 = map.get(o2);
        }

        return o2;
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
