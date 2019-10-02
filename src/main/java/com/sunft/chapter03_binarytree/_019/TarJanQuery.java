package com.sunft.chapter03_binarytree._019;

public class TarJanQuery {

    //主方法
    public Node[] tarJanQuery(Node head, Query[] queries) {
        Node[] ans = new Tarjan().query(head, queries);
        return ans;
    }

}
