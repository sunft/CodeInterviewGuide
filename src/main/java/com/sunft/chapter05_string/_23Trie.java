package com.sunft.chapter05_string;

/**
 * 字典树(前缀树)的实现
 * 【题目】
 * 字典树又称为前缀树或Trie树，是处理字符串常见的数据结构。假设组成所有单词的
 * 字符仅是"a"~"z"，请实现字典树结构，并包含以下四个主要功能。
 * 1)void insert(String word): 添加word，可重复添加。
 * 2)void delete(String word): 删除word，如果word添加过多次，仅删除一个。
 * 3)boolean search(String word): 查询word是否在字典树中。
 * 4)int prefixNumber(String pre): 返回以字符串pre为前缀的单词数量。
 */
public class _23Trie {

    private TireNode root;

    public _23Trie() {
        root = new TireNode();
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }
        char[] chs = word.toCharArray();
        TireNode node = root;
        int index = 0;
        for (char ch : chs) {
            index = ch - 'a';
            if (node.map[index] == null) {
                node.map[index] = new TireNode();
            }
            node = node.map[index];
            node.path++;
        }
        node.end++;
    }

    public void delete(String word) {
        if (search(word)) {
            char[] chs = word.toCharArray();
            TireNode node = root;
            int index = 0;
            for (char ch : chs) {
                index = ch - 'a';
                if (node.map[index].path-- == 1) {
                    node.map[index] = null;
                    return;
                }
                node = node.map[index];
            }
            node.end--;
        }
    }

    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        char[] chs = word.toCharArray();
        TireNode node = root;
        int index = 0;
        for (char ch : chs) {
            index = ch - 'a';
            if (node.map[index] == null) {
                return false;
            }
            node = node.map[index];
        }
        return node.end != 0;
    }

    public int prefixNumber(String pre) {
        if (pre == null) {
            return 0;
        }
        char[] chs = pre.toCharArray();
        TireNode node = root;
        int index = 0;
        for (char ch : chs) {
            index = ch - 'a';
            if (node.map[index] == null) {
                return 0;
            }
            node = node.map[index];
        }
        return node.path;
    }

    private static class TireNode {
        public int path;
        public int end;
        public TireNode[] map;

        public TireNode() {
            path = 0;
            end = 0;
            map = new TireNode[26];
        }
    }

}
