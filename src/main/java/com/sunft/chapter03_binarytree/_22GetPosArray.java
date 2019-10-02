package com.sunft.chapter03_binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过先序和中序数组生成后续数组
 * 【题目】
 *  已知一棵二叉树所有的节点值都不同，给定这棵树的正确的先序和中序数组，
 *  不要重建整棵树，而是通过这两个数组直接生成正确的后续数组。
 */
public class _22GetPosArray {

    public int[] getPosArray(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }

        int len = pre.length;
        int[] pos = new int[len];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < len; i ++) {
            map.put(in[i], i);
        }

        setPost(pre, 0, len - 1, in, 0, len - 1, pos, len - 1, map);
        return pos;
    }

    /**
     * 从右往右依次填好后续数组s
     * si为后序数组s该填的位置
     * 返回值s该填的下一个位置
     * @param p
     * @param pi
     * @param pj
     * @param n
     * @param ni
     * @param nj
     * @param s
     * @param si
     * @param map
     * @return
     */
    private int setPost(int[] p, int pi, int pj, int[] n, int ni, int nj, int[] s, int si, Map<Integer, Integer> map) {
        if (pi > pj) {
            return si;
        }

        s[si--] = p[pi];
        int i = map.get(p[pi]);
        si = setPost(p, pj - nj + i + 1, pj, n, i + 1, nj, s, si, map);
        return setPost(p, pi + 1, pi + 1 - ni, n, ni, i - 1, s, si, map);
    }

}
