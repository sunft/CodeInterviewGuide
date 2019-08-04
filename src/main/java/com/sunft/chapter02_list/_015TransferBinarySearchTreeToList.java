package com.sunft.chapter02_list;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 将搜索二叉树转换成双向链表
 * 【题目】
 * 对二叉树的结点来说，有本身的值域，有指向左孩子和右孩子的两个指针；对双向链表的结点来说，
 * 有本身的值域，有指向上一个结点和下一个结点的指针。在结构上，两种结构有相似性，现在有一课
 * 搜索二叉树，请将其转换为一个有序的双向链表。
 */
public class _015TransferBinarySearchTreeToList<E extends Comparable<? super E>> {

    /**
     * 方法一：用队列等容器收集二叉树中序遍历结果的方法。时间复杂度为O(N)，额外空间复杂度为
     * O(N)，具体的过程如下：
     * 1. 生成一个队列，记为queue，按照二叉树中序遍历的顺序，将每个结点放入queue中。
     * 2. 从queue中依次弹出结点，并按照弹出的顺序重连所有的结点。
     * @param head
     * @return
     */
    public Node<E> convert01(Node<E> head) {
        Queue<Node<E>> queue = new LinkedList<>();
        inOrderToQueue(head, queue);
        if (queue.isEmpty()) {
            return head;
        }
        //重新连接
        head = queue.poll();
        Node<E> pre = head;
        pre.left = null;
        Node<E> cur = null;
        while (!queue.isEmpty()) {
            cur = queue.poll();
            pre.right = cur;
            cur.right = pre;
            pre = cur;
        }
        pre.right = null;
        return head;
    }

    /**
     * 中序遍历二叉树，将结点插入到队列中
     * @param head 头结点
     * @param queue 队列
     */
    private void inOrderToQueue(Node<E> head, Queue<Node<E>> queue) {
        if (head == null) {
            return;
        }

        inOrderToQueue(head.left, queue);
        queue.offer(head);
        inOrderToQueue(head.right, queue);
    }

    /**
     * 使用递归函数实现
     * @param head 头结点
     * @return
     */
    public Node<E> convert02(Node<E> head) {
        if (head == null) {
            return null;
        }

        Node<E> last = process(head);
        head = last.right;
        last.right = null;
        return head;
    }

    /**
     * 递归函数
     * @param head
     * @return
     */
    private Node<E> process(Node<E> head) {
        if (head == null) {
            return null;
        }

        Node<E> leftE = process(head.left);//左边结束
        Node<E> rightE = process(head.right);//右边结束

        Node<E> leftS = leftE != null ? leftE.right : null; //左边开始
        Node<E> rightS = rightE != null ? rightE.right : null;//右边开始

        if (leftE != null && rightE != null) {
            leftE.right = head;
            head.left = leftE;
            rightS.left = head;
            rightE.right = leftS;
            return rightE;
        } else if (leftE != null) {
            leftE.right = head;
            head.left = leftE;
            head.right = leftS;
            return head;
        } else if (rightE != null) {
            head.right = rightS;
            rightS.left = head;
            rightE.right = head;
            return rightE;
        } else {
            head.right = head;
            return head;
        }
    }

    /**
     * 结点
     * @param <E>
     */
    private static class Node<E extends Comparable<? super E>> {
        E e;//结点数据域
        Node<E> left; //左子树
        Node<E> right;//右子树

        public Node(E e) {
            this.e = e;
        }
    }

}
