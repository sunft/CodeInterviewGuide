package com.sunft.chapter03_binarytree._019;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Tarjan类实现处理流程
 */
public class Tarjan {

    private HashMap<Node, LinkedList<Node>> queryMap;
    private HashMap<Node, LinkedList<Integer>> indexMap;
    private HashMap<Node, Node> ancestorMap;
    private DisjointSets sets;

    public Tarjan() {
        queryMap = new HashMap<>();
        indexMap = new HashMap<>();
        ancestorMap = new HashMap<>();
        sets = new DisjointSets();
    }

    public Node[] query(Node head, Query[] ques) {
        Node[] ans = new Node[ques.length];
        setQueries(ques, ans);
        sets.makeSets(head);
        setAnswers(head, ans);
        return ans;
    }

    private void setQueries(Query[] ques, Node[] ans) {
        Node o1 = null;
        Node o2 = null;
        for (int i = 0; i != ans.length; i++) {
            o1 = ques[i].o1;
            o2 = ques[i].o2;
            if (o1 == o2 || o1 == null || o2 == null) {
                ans[i] = o1 != null ? o1 : o2;
            } else {
                if (!queryMap.containsKey(o1)) {
                    queryMap.put(o1, new LinkedList<>());
                    indexMap.put(o1, new LinkedList<>());
                }

                if (!queryMap.containsKey(o2)) {
                    queryMap.put(o2, new LinkedList<>());
                    queryMap.put(o2, new LinkedList<>());
                }
                queryMap.get(o1).add(o2);
                indexMap.get(o1).add(i);
                queryMap.get(o2).add(o1);
                indexMap.get(o2).add(i);
            }
        }
    }

    private void setAnswers(Node head, Node[] ans) {
        if (head == null) {
            return;
        }

        setAnswers(head.left, ans);
        sets.union(head.left, head);
        ancestorMap.put(sets.findFather(head), head);
        setAnswers(head.right, ans);
        sets.union(head.right, head);
        ancestorMap.put(sets.findFather(head), head);
        LinkedList<Node> nList = queryMap.get(head);
        LinkedList<Integer> iList = indexMap.get(head);
        Node node = null;
        Node nodeFather = null;
        int index = 0;
        while (nList != null && !nList.isEmpty()) {
            node = nList.poll();
            index = iList.poll();
            nodeFather = sets.findFather(node);
            if (ancestorMap.containsKey(nodeFather)) {
                ans[index] = ancestorMap.get(nodeFather);
            }
        }
    }

}
