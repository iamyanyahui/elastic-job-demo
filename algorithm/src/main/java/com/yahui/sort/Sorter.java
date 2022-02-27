package com.yahui.sort;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-02-27
 */
public class Sorter<T extends Comparable<T>> {
    public boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    public void exch(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
