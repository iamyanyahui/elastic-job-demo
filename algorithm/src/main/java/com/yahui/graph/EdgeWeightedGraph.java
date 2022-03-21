package com.yahui.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * 边带权重的无向图
 *
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-20
 */
public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private Set<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new Set[V];
        for (int i = 0; i < V; i++) {
            this.adj[i] = new HashSet<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Set<Edge> set = new HashSet<>();
        for (int i=0; i<V; i++) {
            for (Edge e : adj(i)) {
                set.add(e);
            }
        }
        return set;
    }
}
