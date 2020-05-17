package com.sunft.chapter09_other;

import java.util.Comparator;

/**
 * 设计一个没有扩容负担的堆结构
 * <p>
 * 【题目】
 * 堆结构一般是使用固定长度的数组结构来实现的。 这样的实现虽
 * 然足够经典， 但存在扩容的负担， 比如不断向堆中增加元素， 使得固
 * 定数组快耗尽时， 就不得不申请一个更大的固定数组， 然后把原来数
 * 组中的对象复制到新的数组里完成堆的扩容， 所以， 如果扩容时堆中
 * 的元素个数为N， 那么扩容行为的时间复杂度为O(N)。 请设计一种没
 * 有扩容负担的堆结构， 即在任何时刻有关堆的操作时间复杂度都不超
 * 过O(logN)。
 * <p>
 * 【要求】
 * 1．没有扩容的负担。
 * 2．可以生成小根堆， 也可以生成大根堆。
 * 3．包含getHead方法， 返回当前堆顶的值。
 * 4．包含getSize方法， 返回当前堆的大小。
 * 5．包含add(x)方法， 即向堆中新加元素x， 操作后依然是小根堆/大根堆。
 * 6．包含popHead方法， 即删除并返回堆顶的值， 操作后依然是小根堆/大根堆。
 * 7．如果堆中的节点个数为N， 那么各个方法的时间复杂度为：
 * getHead:O(1)。
 * getSize:O(1)。
 * add:O(logN)。
 * popHead:O(logN)。
 */
public class _24MyHeap<K> {

    private Node<K> head; //堆头节点
    private Node<K> last; //堆尾节点
    private long size;    //当前堆的大小
    private Comparator<K> comparator; //大根堆或小根堆

    public _24MyHeap(Comparator<K> comparator) {
        this.head = null;
        this.last = null;
        this.size = 0;
        this.comparator = comparator; //基于比较器决定是大根堆还是小根堆
    }

    public K getHead() {
        return head == null ? null : head.value;
    }

    public long getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加一个新节点到堆中
     *
     * @param value
     */
    public void add(K value) {
        Node<K> newNode = new Node<>(value);
        if (size == 0) {
            this.head = newNode;
            last = newNode;
            size++;
            return;
        }

        Node<K> node = last;
        Node<K> parent = node.parent;
        //找到正确的位置并插入到新节点
        while (parent != null && node != parent.left) {
            node = parent;
            parent = node.parent;
        }
        Node<K> nodeToAdd = null;
        if (parent == null) {
            nodeToAdd = mostLeft(head);
            nodeToAdd.left = newNode;
            newNode.parent = nodeToAdd;
        } else if (parent.right == null) {
            parent.right = newNode;
            newNode.parent = parent;
        } else {
            nodeToAdd = mostLeft(parent.right);
            nodeToAdd.left = newNode;
            newNode.parent = nodeToAdd;
        }
        last = newNode;
        //建堆过程及其调整
        heapInsertModify();
        size++;
    }

    public K popHead() {
        if (this.size == 0) {
            return null;
        }
        Node<K> res = head;
        if (size == 1) {
            head = null;
            last = null;
            size--;
            return res.value;
        }
        Node<K> oldLast = popLastAndSetPreviousLast();
        //如果弹出堆尾节点后，堆的大小等于1的处理
        if (this.size == 1) {
            head = oldLast;
            last = oldLast;
            return res.value;
        }
        //如果弹出堆尾节点后，堆的大小大于1的处理
        Node<K> headLeft = res.left;
        Node<K> headRight = res.right;
        oldLast.left = headLeft;
        if (headLeft != null) {
            headLeft.parent = oldLast;
        }
        oldLast.right = headRight;
        if (headRight != null) {
            headRight.parent = oldLast;
        }
        res.left = null;
        res.right = null;
        head = oldLast;
        //堆heapify过程
        heapify(oldLast);
        return res.value;
    }

    /**
     * 找到以node为头的子树中，最左的节点
     *
     * @param node
     * @return
     */
    private Node<K> mostLeft(Node<K> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 找到以node为头的子树中，最右的节点
     *
     * @param node
     * @return
     */
    private Node<K> mostRight(Node<K> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * 堆的heapify过程
     *
     * @param node
     */
    private void heapify(Node<K> node) {
        Node<K> left = node.left;
        Node<K> right = node.right;
        Node<K> most = node;
        while (left != null) {
            if (left != null && this.comparator.compare(left.value, most.value) < 0) {
                most = left;
            }
            if (right != null && this.comparator.compare(right.value, most.value) < 0) {
                most = right;
            }
            if (most != node) {
                swapClosedTwoNodes(most, node);
            } else {
                break;
            }
            left = node.left;
            right = node.right;
            most = node;
        }
        if (node.parent == last) {
            last = node;
        }
        while (node.parent == last) {
            node = node.parent;
        }
        head = node;
    }

    /**
     * 交换相邻的两个结点
     *
     * @return
     */
    private void swapClosedTwoNodes(Node<K> node, Node<K> parent) {
        if (node == null || parent == null) {
            return;
        }
        Node<K> parentParent = parent.parent;
        Node<K> parentLeft = parent.left;
        Node<K> parentRight = parent.right;
        Node<K> nodeLeft = node.left;
        Node<K> nodeRight = node.right;
        node.parent = parentParent;
        if (parentParent != null) {
            if (parent == parentParent.left) {
                parentParent.left = node;
            } else {
                parentParent.right = node;
            }
        }
        parent.parent = node;
        if (nodeLeft != null) {
            nodeLeft.parent = parent;
        }
        if (nodeRight != null) {
            nodeRight.parent = parent;
        }
        if (node == parent.left) {
            node.left = parent;
            node.right = parentRight;
            if (parentRight != null) {
                parentRight.parent = node;
            }
        } else {
            node.left = parentLeft;
            node.right = parent;
            if (parentLeft != null) {
                parentLeft.parent = node;
            }
        }
        parent.left = nodeLeft;
        parent.right = nodeRight;
    }

    /**
     * 在树中弹出堆尾节点后，找到原来的倒数第二个节点设置成新的堆尾节点
     *
     * @return
     */
    private Node<K> popLastAndSetPreviousLast() {
        Node<K> node = this.last;
        Node<K> parent = node.parent;
        while (parent != null && node != parent.right) {
            node = parent;
            parent = node.parent;
        }
        if (parent == null) {
            node = last;
            parent = node.parent;
            node.parent = null;
            if (node == parent.left) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            last = mostRight(head);
        } else {
            Node<K> newLast = mostRight(parent.left);
            node = last;
            parent = node.parent;
            node.parent = null;
            if (node == parent.left) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            last = newLast;
        }
        size--;
        return node;
    }

    /**
     * 建堆及调整过程
     */
    private void heapInsertModify() {
        Node<K> node = this.last;
        Node<K> parent = node.parent;
        if (parent != null && this.comparator.compare(node.value, parent.value) < 0) {
            last = parent;
        }
        while (parent != null && this.comparator.compare(node.value, parent.value) < 0) {
            swapClosedTwoNodes(node, parent);
            parent = node.parent;
        }
        if (head.parent != null) {
            head = head.parent;
        }
    }


    private static class Node<K> {
        public K value;
        public Node<K> left;
        public Node<K> right;
        public Node<K> parent;

        public Node(K data) {
            this.value = data;
        }
    }

}
