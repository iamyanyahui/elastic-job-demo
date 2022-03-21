package com.yahui.mst;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import com.yahui.graph.Edge;
import com.yahui.graph.EdgeWeightedGraph;

/**
 * 延迟Prim算法实现的最小生成树
 *
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-20
 */
public class LazyPrimMST {
    private Queue<Edge> mst;
    private boolean[] marked;
    private PriorityQueue<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph g) {
        pq = new PriorityQueue<>(g.E());
        mst = new LinkedList<>();
        marked = new boolean[g.V()];
        visit(g, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) {
                continue;
            }
            mst.add(e);
            if (!marked[v]) {
                visit(g, v);
            }
            if (!marked[w]) {
                visit(g, w);
            }
        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.add(e);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double weight = 0.0D;
        for (Edge e : mst) {
            weight += e.weight();
        }
        return weight;
    }
}
