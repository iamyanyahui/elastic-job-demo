package com.yahui.pq;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-02-27
 */
public class MinPQ<T extends Comparable<T>> extends PriorityQueue<T> {

    public MinPQ(int capacity) {
        super(capacity);
    }

    @Override
    public boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }
}
