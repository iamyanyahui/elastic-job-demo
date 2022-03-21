package com.yahui.mst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yahui.graph.Edge;
import com.yahui.graph.EdgeWeightedGraph;
import com.yahui.pq.IndexMinQueue;

/**
 * 即使Prim算法实现的最小生成数
 *
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-20
 */
public class PrimMST {
    private boolean[] marked;
    private Edge[] edgeTo;
    private IndexMinQueue<Double> pq;
    private double[] distTo;

    public PrimMST(EdgeWeightedGraph g) {
        marked = new boolean[g.V()];
        edgeTo = new Edge[g.V()];
        pq = new IndexMinQueue<>(g.V());
        distTo = new double[g.V()];
        Arrays.fill(distTo, Double.MAX_VALUE);
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()) {
            visit(g, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            int w = e.other(v);
            if (marked[w]) {
                continue;
            }
            if (distTo[w] > e.weight()) {
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.change(w, e.weight());
                } else {
                    pq.insert(w, e.weight());
                }
            }
        }
    }

    public Iterable<Edge> edges() {
        List<Edge> es = new ArrayList<>(edgeTo.length);
        for (int i = 1; i < edgeTo.length; i++) {
            es.add(edgeTo[i]);
        }
        return es;
    }

    public double weight() {
        double weight = 0.0D;
        Iterable<Edge> es = edges();
        for (Edge e : es) {
            weight += e.weight();
        }
        return weight;
    }
}
