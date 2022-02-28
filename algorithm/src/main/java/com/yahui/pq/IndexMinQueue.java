package com.yahui.pq;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-02-27
 */
public class IndexMinQueue<T extends Comparable<T>> {
    private int[] pq;
    private T[] items;
    private int[] qp;
    private int n;

    public IndexMinQueue(int maxN) {
        pq = new int[maxN + 1];
        items = (T[]) new Object[maxN];
        qp = new int[maxN];
        n = 0;
    }

    public void insert(int k, T item) {
        items[k] = item;
        pq[++n] = k;
        qp[k] = n;
        swim(n);
    }

    public void change(int k, T item) {
        items[k] = item;
        swim(qp[k]);
        sink(qp[k]);
    }

    public void delete(int k) {
        items[k] = null;
        int i = qp[k];
        exch(i, n--);
        swim(i);
        sink(i);
        qp[k] = 0;
    }


    public T min() {
        return items[minIndex()];
    }

    public int minIndex() {
        return pq[1];
    }

    public int delMin() {
        int min = pq[1];
        pq[1] = pq[n--];
        sink(1);
        items[min] = null;
        qp[min] = 0;
        qp[pq[1]] = 1;
        return min;
    }

    private void swim(int i) {
        while (i > 1 && less(i/2, i)) {
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
            if (less(i, j)) {
                break;
            }

            exch(i, j);
            i = j;
        }
    }

    private void exch(int i, int j) {
        int t1 = pq[i];
        int t2 = pq[j];
        pq[i] = t2;
        qp[t2] = i;

        qp[j] = t1;
        qp[t1] = j;
    }

    private boolean less(int i, int j) {
        return items[pq[i]].compareTo(items[pq[j]]) < 0;
    }
}
