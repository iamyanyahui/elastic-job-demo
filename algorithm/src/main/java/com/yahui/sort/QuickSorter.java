package com.yahui.sort;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-02-27
 */
public class QuickSorter<T extends Comparable<T>> extends BasicSorter<T> {
    public void sort(T[] a) {
        sort(a, 0, a.length - 1);
    }

    public void sort(T[] a, int l, int h) {
        if (l == h) {
            return;
        }
        int p = partition(a, l, h);
        sort(a, l, p);
        sort(a, p + 1, h);
    }

    private int partition(T[] a, int l, int h) {
        T temp = a[l];
        int i=l, j=h;
        while (i < j) {
            while (i < j && less(temp, a[j])) {
                j--;
            }
            a[i] = a[j];
            while (i < j && !less(temp, a[i])) {
                i++;
            }
            a[j] = a[i];
        }

        a[i] = temp;
        return i;
    }
}
