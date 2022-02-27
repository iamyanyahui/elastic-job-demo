package com.yahui.sort;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-02-27
 */
public class BasicSorter<T extends Comparable<T>> extends Sorter<T> {
    public void selectionSort(T[] a) {
        int len = a.length;
        for (int i = 0; i < len; i++) {
            int minI = i;
            for (int j = i + 1; j < len; j++) {
                if (less(a[j], a[minI])) {
                    minI = j;
                }
            }
            exch(a, i, minI);
        }
    }

    public void insertionSort(T[] a) {
        int len = a.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public void shellSort(T[] a) {
        int len = a.length;
        int h = 0;
        while (h < len / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }
}
