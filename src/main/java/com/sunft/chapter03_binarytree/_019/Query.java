package com.sunft.chapter03_binarytree._019;

/**
 * 一个Query类的实例表示一条查询语句，表示想要查询o1结点
 * 和o2结点的最近公共祖先结点
 *
 * 给定一棵二叉树的头结点head，并给定所有的查询语句，即一个
 * Query类型的数组Query[] ques，请返回Node类型的数组Node[] ans，
 * ans[i]代表ques[i]这条查询的答案，即ques[i].o1和ques[i].o2的最近公共祖先
 *
 */
public class Query {

    public Node o1;
    public Node o2;

    public Query(Node o1, Node o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

}
