package com.yahui.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 边带权重的有向图
 *
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-20
 */
public class EdgeWeightedDigraph {
    private int V;
    private int E;
    private Set<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.adj = new Set[V];
        Arrays.fill(adj, new HashSet<>());
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Set<DirectedEdge> edges = new HashSet<>();
        for (int i = 0; i < V; i++) {
            for (DirectedEdge e : adj[i]) {
                edges.add(e);
            }
        }
        return edges;
    }
}
