package com.yahui.graph;

import java.util.HashSet;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-13
 */
public class Graph {
    private int E;
    private int V;
    private HashSet<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new HashSet[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new HashSet<>();
        }
    }

    // 顶点数
    public int V() {
        return this.V;
    }

    // 边数
    public int E() {
        return this.E;
    }

    // 顶点v的邻接表
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public void addEdge(int a, int b) {
        adj[a].add(b);
        adj[b].add(a);
        this.E++;
    }
}
