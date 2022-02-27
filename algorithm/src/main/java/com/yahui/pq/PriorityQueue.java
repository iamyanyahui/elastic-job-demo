package com.yahui.pq;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-02-27
 */
public abstract class PriorityQueue<T extends Comparable<T>> {
    protected T[] pq;
    protected int capacity;
    protected int n;

    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        n = 0;
        pq = (T[]) new Object[capacity + 1];
    }

    public void insert(T a) {
        pq[++n] = a;
        swim(n);
    }

    public T getTop() {
        return pq[1];
    }

    public T removeTop() {
        T tmp = pq[1];
        pq[1] = pq[n];
        pq[n] = null;
        --n;
        sink(1);
        return tmp;
    }

    private void swim(int i) {
        while (i > 1 && less(i, i/2)) {
            exch(i, i/2);
            i = i/2;
        }
    }

    private void sink(int i) {
        while (i*2 <= n) {
            int j = i*2;
            if (j < n && less(j+1, j)) {
                ++j;
            }
            if (!less(j, i)) {
                break;
            }
            exch(i, j);
            i = j;
        }
    }

    public void exch(int i, int j) {
        T tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
    }

    public abstract boolean less(int i, int j);
}
