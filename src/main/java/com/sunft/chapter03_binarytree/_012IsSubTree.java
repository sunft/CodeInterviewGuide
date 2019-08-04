package com.sunft.chapter03_binarytree;

/**
 * 判断t1树中是否有与t2树拓扑结构完全相同的子树
 * <p>
 * 【题目】
 * 给定彼此独立的两棵树头结点分别为t1和t2，判断t1中是否有与t2树拓扑
 * 结构相同的子树。
 */
public class _012IsSubTree {

    public boolean isSubTree(Node t1, Node t2) {
        String t1Str = serialByPre(t1);
        String t2Str = serialByPre(t2);
        return getIndexOf(t1Str, t2Str) != -1;
    }

    /**
     * 按照先序遍历序列化树
     *
     * @param head
     * @return
     */
    private String serialByPre(Node head) {
        if (head == null) {
            return "#!";
        }
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    // KMP
    private int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }

        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    private int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }

        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
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
