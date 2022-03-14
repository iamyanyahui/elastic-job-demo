package com.yahui.graph;

import java.util.HashSet;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-13
 */
public class Digraph {
    private int V;
    private int E;
    private HashSet<Integer>[] edge;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        this.edge = new HashSet[V];
        for (int i = 0; i < V; i++) {
            edge[i] = new HashSet<>();
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public void addEdge(int v, int w) {
        edge[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return edge[v];
    }

    public Digraph reverse() {
        Digraph d = new Digraph(V);
        for (int i = 0; i < V; i++) {
            for (int j : adj(i)) {
                d.addEdge(j, i);
            }
        }
        return d;
    }
}
