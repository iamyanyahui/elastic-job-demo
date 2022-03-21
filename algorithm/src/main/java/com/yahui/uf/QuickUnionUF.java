package com.yahui.uf;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-20
 */
public class QuickUnionUF implements UF {
    private int[] id;

    public QuickUnionUF(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    @Override
    public int find(int v) {
        while (id[v] != v) {
            v = id[v];
        }
        return v;
    }

    @Override
    public void union(int v, int w) {
        int vId = find(v);
        int wId = find(w);
        if (vId == wId) {
            return;
        }

        id[wId] = vId;
    }
}
