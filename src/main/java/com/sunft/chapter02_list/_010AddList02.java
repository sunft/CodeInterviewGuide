package com.sunft.chapter02_list;

public class _010AddList02 {

    public Node<Integer> addList(Node<Integer> head1, Node<Integer> head2) {
        head1 = reverseList(head1);//反转链表head1
        head2 = reverseList(head2);//反转链表head2

        int ca = 0;
        int n1 = 0;
        int n2 = 0;
        int n = 0;
        Node<Integer> c1 = head1;
        Node<Integer> c2 = head2;
        Node<Integer> node = null;
        Node<Integer> pre = null;

        while (c1 != null || c2 != null) {
            n1 = c1 != null ? (c1.e == null? 0 : c1.e) : 0;//获取c1的值
            n2 = c2 != null ? (c2.e == null? 0 : c2.e) : 0;//获取c2的值
            n = n1 + n2 + ca;
            pre = node;
            node = new Node<>(n % 10);
            node.next = pre;
            ca = n / 10;
            c1 = c1 != null ? c1.next : null;
            c2 = c2 != null ? c2.next : null;
        }

        if (ca == 1) {
            pre = node;
            node = new Node<>(1);
            node.next = pre;
        }
        reverseList(head1);
        reverseList(head2);
        return node;

    }

    /**
     * 逆序链表
     * @param head
     * @return
     */
    private Node<Integer> reverseList(Node<Integer> head) {
        Node<Integer> pre = null;
        Node<Integer> next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 结点
     * @param <Integer>
     */
    private static class Node<Integer> {
        Integer e;//结点数据域
        Node<Integer> next; //指向下一个结点

        public Node(Integer e) {
            this.e = e;
        }
    }

}
