package com.yahui.sp;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import com.yahui.graph.DirectedEdge;
import com.yahui.graph.EdgeWeightedDigraph;
import com.yahui.pq.IndexMinQueue;

/**
 * Dijkstra算法实现的最短路径，类似Prim算法
 *
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-20
 */
public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinQueue<Double> pq;

    public DijkstraSP(EdgeWeightedDigraph g, int s) {
        edgeTo = new DirectedEdge[g.V()];
        distTo = new double[g.V()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[s] = 0.0;
        pq.insert(s, 0.0D);
        while (!pq.isEmpty()) {
            relax(g, pq.delMin());
        }
    }

    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;

                if (pq.contains(w)) {
                    pq.change(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.MAX_VALUE;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Deque<DirectedEdge> stack = new LinkedList<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            stack.push(e);
        }
        return stack;
    }
}
