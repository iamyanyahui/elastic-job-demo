package com.yahui.sort;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-02-27
 */
public class MergeSorter<T extends Comparable<T>> extends BasicSorter<T> {
    private Object[] temp;

    public void sort(T[] a) {
        temp = new Object[a.length];
        sort(a, 0, a.length - 1);
    }

    public void sort(T[] a, int l, int h) {
        if (l == h) {
            return;
        }

        int mid = (l + h) / 2;
        sort(a, l, mid);
        sort(a, mid + 1, h);
        merge(a, l, mid, h);
    }

    private void merge(T[] a, int l, int mid, int h) {
        int i = l, j = mid + 1;
        int d = 0;
        while (i <= mid && j <= h) {
            if (less(a[i], a[j])) {
                temp[d++] = a[i++];
            } else {
                temp[d++] = a[j++];
            }
        }

        while (i <= mid) {
            temp[d++] = a[i++];
        }

        while (j <= h) {
            temp[d++] = a[j++];
        }

        i = l;
        d = 0;
        while (i <= h) {
            a[i++] = (T) temp[d++];
        }
    }
}
