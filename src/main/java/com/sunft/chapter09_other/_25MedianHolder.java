package com.sunft.chapter09_other;

import java.util.Comparator;

/**
 * 随时找到数据流的中位数
 * <p>
 * 【题目】
 * 有一个源源不断地吐出整数的数据流， 假设你有足够的空间来保
 * 存吐出的数。 请设计一个名叫MedianHolder的结构， MedianHolder可
 * 以随时取得之前吐出所有数的中位数。
 * <p>
 * 【要求】
 * 1．如果MedianHolder已经保存了吐出的N个数， 那么任意时刻将
 * 一个新数加入到MedianHolder的过程， 其时间复杂度是O(logN)。
 * 2．取得已经吐出的N个数整体的中位数的过程， 时间复杂度
 * 为O(1)。
 */
public class _25MedianHolder {

    private _24MyHeap<Integer> minHeap;
    private _24MyHeap<Integer> maxHeap;

    public _25MedianHolder() {
        this.minHeap = new _24MyHeap<>(new MinHeapComparator());
        this.maxHeap = new _24MyHeap<>(new MaxHeapComparator());
    }

    public void addNumber(Integer num) {
        if (this.maxHeap.isEmpty()) {
            this.maxHeap.add(num);
            return;
        }
        if (this.maxHeap.getHead() >= num) {
            this.maxHeap.add(num);
        } else {
            if (this.minHeap.isEmpty()) {
                this.minHeap.add(num);
                return;
            }
            if (this.minHeap.getHead() > num) {
                this.maxHeap.add(num);
            } else {
                this.minHeap.add(num);
            }
        }
        this.modifyTwoHeapsSize();
    }

    public Integer getMedian() {
        long maxHeapSize = this.maxHeap.getSize();
        long minHeapSize = this.minHeap.getSize();
        if (maxHeapSize + minHeapSize == 0) {
            return null;
        }
        Integer maxHeapHead = this.maxHeap.getHead();
        Integer minHeapHead = this.minHeap.getHead();
        if (((maxHeapSize + minHeapSize) & 1) == 0) {
            return (maxHeapHead + minHeapHead) / 2;
        } else if (maxHeapSize > minHeapSize) {
            return maxHeapHead;
        } else {
            return minHeapHead;
        }
    }

    private void modifyTwoHeapsSize() {
        if (this.maxHeap.getSize() == this.minHeap.getSize() + 2) {
            this.minHeap.add(this.maxHeap.popHead());
        }
        if (this.minHeap.getSize() == this.maxHeap.getSize() + 2) {
            this.maxHeap.add(this.minHeap.popHead());
        }
    }

    /**
     * 生成大根堆的比较器
     */
    private static class MaxHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    }

    /**
     * 生成小根堆的比较器
     */
    private static class MinHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

}
