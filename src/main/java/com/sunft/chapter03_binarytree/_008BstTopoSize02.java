package com.sunft.chapter03_binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * 找到二叉树中符合搜索二叉树条件的最大拓扑结构
 * <p>
 * 【题目】
 * 给定一棵二叉树的头结点head，已知所有结点的值都不一样，返回其中最大的且符合
 * 搜索二叉树条件的最大拓扑结构的大小。
 */
public class _008BstTopoSize02 {
    
    public int bstTopoSize2(Node head) {
        Map<Node, Record> map = new HashMap<>();
        return posOrder(head, map);
    }

    private int posOrder(Node h, Map<Node, Record> map) {
        if (h == null) {
            return 0;
        }
        
        int ls = posOrder(h.left, map);
        int rs = posOrder(h.right, map);
        modifyMap(h.left, h.value, map, true);
        modifyMap(h.right, h.value, map, false);
        Record lr = map.get(h.left);
        Record rr = map.get(h.right);
        int lbst = lr == null ? 0 : lr.l + lr.r + 1;
        int rbst = rr == null ? 0 : rr.l + rr.r + 1;
        map.put(h, new Record(lbst, rbst));
        return Math.max(lbst + rbst + 1, Math.max(ls, rs));
    }

    private int modifyMap(Node n, int v, Map<Node, Record> m, boolean s) {
        if (n == null || (!m.containsKey(n))) {
            return 0;
        }

        Record r = m.get(n);
        if ((s && n.value > v) || ((!s) && n.value < v)) {
            m.remove(n);
            return r.l + r.r + 1;
        } else {
            int minus = modifyMap(s ? n.right : n.left, v, m, s);
            if (s) {
                r.r = r.r - minus;
            } else {
                r.l = r.l - minus;
            }
            m.put(n, r);
            return minus;
        }
    }


    private static class Record {
        private int l;
        private int r;

        Record(int left, int right) {
            this.l = left;
            this.r = right;
        }
    }

    /**
     * 结点
     */
    private static class Node {
        int value;//结点数据域
        Node left; //左孩子
        Node right; //右孩子

        public Node(int value) {
            this.value = value;
        }

    }

}
