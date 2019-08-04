package com.sunft.chapter03_binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 *
 * 【题目】
 * 二叉树被记录成文件的过程叫做二叉树的序列化，通过文件内容重建原来二叉树的过程
 * 叫做二叉树的反序列化。给定一棵二叉树的头结点head，并已知二叉树的类型为32位整形。
 * 请设计一种二叉树序列化和反序列化的方案，并用代码实现。
 *
 * 方法一：通过先序遍历实现序列化和反序列化。
 * 方法二：通过层序遍历实现序列化和反序列化。
 *
 * @param <E>
 */
public class _004SerializeBinaryTree<E extends Comparable<? super E>> {

    /**
     * 通过先序遍历序列化二叉树
     * @param head
     * @return
     */
    public String serialByPre(Node<E> head) {
        if (head == null) {
            return "#!";
        }

        String res = head.e + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    /**
     * 通过先序遍历反序列化
     * @param preStr
     * @return
     */
    public Node<String> reconByPreString(String preStr) {
        String[] values = preStr.split("!");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i != values.length; i ++) {
            queue.offer(values[i]);
        }
        return reconPreOrder(queue);
    }

    /**
     * 构造二叉树
     * @param queue
     * @return
     */
    private Node<String> reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node<String> head = new Node<>(value);
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }

    /**
     * 二叉树的层序遍历
     * @param head
     * @return
     */
    public String serialByLevel(Node<E> head) {
        if (head == null) {
            return "#!";
        }

        String res = head.e + "!";
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.left != null) {
                res += head.left.e + "!";
                queue.offer(head.left);
            } else {
                res += "#!";
            }

            if (head.right != null) {
                res += head.right.e + "!";
                queue.offer(head.right);
            } else {
                res += "#!";
            }
        }
        return res;
    }

    /**
     * 根据层序遍历恢复二叉树
     * @param levelStr
     * @return
     */
    public Node<String> reconByLevelString(String levelStr) {
        String[] values = levelStr.split("!");
        int index = 0;
        Node<String> head = generateNodeByString(values[index++]);
        Queue<Node<String>> queue = new LinkedList<>();
        if (head != null) {
            queue.offer(head);
        }
        Node<String> node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNodeByString(values[index++]);
            node.right = generateNodeByString(values[index++]);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return head;
    }

    private Node<String> generateNodeByString(String value) {
        if ("#".equals(value)) {
            return null;
        }

        return new Node<String>(value);
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
