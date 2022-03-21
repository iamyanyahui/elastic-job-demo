package com.yahui.uf;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-20
 */
public class QuickFindUF implements UF {
    private int[] id;

    public QuickFindUF(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public int find(int v) {
        return id[v];
    }

    public void union(int v, int w) {
        int vId = id[v];
        int wId = id[w];
        if (wId == vId) {
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == wId) {
                id[i] = vId;
            }
        }
    }
}
