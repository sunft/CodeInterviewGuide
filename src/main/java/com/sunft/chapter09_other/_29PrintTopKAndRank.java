package com.sunft.chapter09_other;

import java.util.HashMap;
import java.util.Map;

/**
 * 出现次数的TOP K问题
 * <p>
 * 【题目】
 * 给定String类型的数组strArr， 再给定整数k， 请严格按照排名顺序打印出现次数前k名的字符串。
 * <p>
 * 【举例】
 * strArr=["1"， "2"， "3"， "4"]， k=2
 * No.1: 1， times: 1
 * No.2: 2， times: 1
 * 这种情况下， 所有的字符串都出现一样多， 随便打印任何两个字符串都可以。
 * <p>
 * strArr=["1"， "1"， "2"， "3"]， k=2
 * 输出：
 * No.1: 1， times: 2
 * No.2: 2， times: 1
 * 或者输出：No.1: 1， times: 2
 * No.2: 3， times: 1
 * <p>
 * 【 要求】
 * 如果strArr长度为N， 时间复杂度请达到O(Nlogk)。
 * <p>
 * 【 进阶题目】
 * 设计并实现TopKRecord结构， 可以不断地向其中加入字符串，并且可以根据字符串出现的情况随时打印加入次数最多前k个字符
 * 串， 具体为：
 * 1．k在TopKRecord实例生成时指定， 并且不再变化（ k是构造函数的参数） 。
 * 2．含有add(String str)方法， 即向TopKRecord中加入字符串。
 * 3．含有printTopK()方法， 即打印加入次数最多的前k个字符串，
 * 打印有哪些字符串和对应的次数即可， 不要求严格按排名顺序打印。
 * <p>
 * 【 举例】
 * TopKRecord record = new TopKRecord(2); // 打印Top 2的结构
 * record.add("A");
 * record.printTopK();此时打印：
 * TOP:
 * Str: A Times: 1
 * record.add("B");
 * record.add("B");
 * record.printTopK();
 * 此时打印：
 * TOP:
 * Str: A Times: 1
 * Str: B Times: 2
 * 或者打印
 * TOP:
 * Str: B Times: 2
 * Str: A Times: 1
 * record.add("C");
 * record.add("C");record.printTopK();
 * 此时打印：
 * TOP:
 * Str: B Times: 2
 * Str: C Times: 2
 * 或者打印
 * TOP:
 * Str: C Times: 2
 * Str: B Times: 2
 * <p>
 * 【要求】
 * 1．在任何时刻， add方法的时间复杂度不超过O(logk)。
 * 2．在任何时刻， printTopK方法的时间复杂度不超过O(k)。
 */
public class _29PrintTopKAndRank {

    /**
     * 初级问题
     *
     * @param arr
     * @param topK
     */
    public void printTopKAndRank(String[] arr, int topK) {
        if (arr == null || topK < 1) {
            return;
        }
        Map<String, Integer> map = new HashMap<>();
        // 生成哈希表(字符串词频)
        for (int i = 0; i != arr.length; i++) {
            String cur = arr[i];
            if (!map.containsKey(cur)) {
                map.put(cur, 1);
            } else {
                map.put(cur, map.get(cur) + 1);
            }
        }
        Node[] heap = new Node[topK];
        int index = 0;
        // 遍历哈希表，决定每条信息是否进堆
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String str = entry.getKey();
            int times = entry.getValue();
            Node node = new Node(str, times);
            if (index != topK) {
                heap[index] = node;
                heapInsert(heap, index++);
            } else {
                if (heap[0].times < node.times) {
                    heap[0] = node;
                    heapify(heap, 0, topK);
                }
            }
        }
        // 把小根堆的所有元素按词频从大到小排序
        for (int i = index - 1; i != 0; i--) {
            swap(heap, 0, i);
            heapify(heap, 0, i);
        }
        // 严格按照排名打印k条记录
        for (int i = 0; i != heap.length; i++) {
            if (heap[i] == null) {
                break;
            } else {
                System.out.print("No." + (i + 1) + ": ");
                System.out.print(heap[i].str + ", times: ");
                System.out.println(heap[i].times);
            }
        }
    }

    private void swap(Node[] heap, int index1, int index2) {
        Node tmp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = tmp;
    }

    private void heapify(Node[] heap, int index, int heapSize) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int smallest = index;
        while (left < heapSize) {
            if (heap[left].times < heap[index].times) {
                smallest = left;
            }
            if (right < heapSize && heap[right].times < heap[smallest].times) {
                smallest = right;
            }
            if (smallest != index) {
                swap(heap, smallest, index);
            } else {
                break;
            }
            index = smallest;
            left = index * 2 + 1;
            right = index * 2 + 2;
        }
    }

    private void heapInsert(Node[] heap, int index) {
        while (index != 0) {
            int parent = (index - 1) / 2;
            if (heap[index].times < heap[parent].times) {
                swap(heap, parent, index);
                index = parent;
            } else {
                break;
            }
        }
    }

    private static class TopKRecord {
        private Node[] heap;
        private int index;
        private Map<String, Node> strNodeMap;
        private Map<Node, Integer> nodeIndexMap;

        public TopKRecord(int size) {
            this.heap = new Node[size];
            this.index = 0;
            this.strNodeMap = new HashMap<>();
            this.nodeIndexMap = new HashMap<>();
        }

        public void add(String str) {
            Node curNode = null;
            int preIndex = -1;
            if (!strNodeMap.containsKey(str)) {
                curNode = new Node(str, 1);
                strNodeMap.put(str, curNode);
                nodeIndexMap.put(curNode, -1);
            } else {
                curNode = strNodeMap.get(str);
                curNode.times++;
                preIndex = nodeIndexMap.get(curNode);
            }
            if (preIndex == -1) {
                if (index == heap.length) {
                    if (heap[0].times < curNode.times) {
                        nodeIndexMap.put(heap[0], -1);
                        nodeIndexMap.put(curNode, 0);
                        heap[0] = curNode;
                        heapify(0, index);
                    }
                } else {
                    nodeIndexMap.put(curNode, index);
                    heap[index] = curNode;
                    heapInsert(index++);
                }
            } else {
                heapify(preIndex, index);
            }
        }

        /**
         * 进阶问题
         */
        public void printTopK() {
            System.out.println("TOP: ");
            for (int i = 0; i != heap.length; i++) {
                if (heap[i] == null) {
                    break;
                }
                System.out.print("Str: " + heap[i].str);
                System.out.println(" Times: " + heap[i].times);
            }
        }

        private void heapInsert(int index) {
            while (index != 0) {
                int parent = (index - 1) / 2;
                if (heap[index].times < heap[parent].times) {
                    swap(parent, index);
                    index = parent;
                } else {
                    break;
                }
            }
        }

        private void swap(int index1, int index2) {
            nodeIndexMap.put(heap[index1], index2);
            nodeIndexMap.put(heap[index2], index1);
            Node tmp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = tmp;
        }

        private void heapify(int index, int heapSize) {
            int l = index * 2 + 1;
            int r = index * 2 + 2;
            int smallest = index;
            while (l < heapSize) {
                if (heap[l].times < heap[index].times) {
                    smallest = l;
                }
                if (r < heapSize && heap[r].times < heap[smallest].times) {
                    smallest = r;
                }
                if (smallest != index) {
                    swap(smallest, index);
                } else {
                    break;
                }
                index = smallest;
                l = index * 2 + 1;
                r = index * 2 + 2;
            }
        }
    }

    private static class Node {
        public String str;
        public int times;

        public Node(String s, int t) {
            this.str = s;
            this.times = t;
        }
    }

}
