package com.yahui.pq;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-02-27
 */
public class MaxPQ<T extends Comparable<T>> extends PriorityQueue<T> {

    public MaxPQ(int capacity) {
        super(capacity);
    }

    @Override
    public boolean less(int i, int j) {
        return pq[j].compareTo(pq[i]) < 0;
    }
}
