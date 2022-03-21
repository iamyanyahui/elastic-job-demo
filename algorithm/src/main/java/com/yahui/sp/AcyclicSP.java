package com.yahui.sp;

import java.util.Arrays;

import com.yahui.graph.DirectedEdge;
import com.yahui.graph.EdgeWeightedDigraph;

/**
 * 无环加权有向图，基于拓扑排序的最短路径
 * 这里的Topological需要重新实现
 *
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-20
 */
public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph g, int s) {
        edgeTo = new DirectedEdge[g.V()];
        distTo = new double[g.V()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[s] = 0.0;
        // todo 需要实现拓扑
//        Topological top = new Topological(g);
//        for (int v : top.order()) {
//            relax(g, v);
//        }
    }

    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge e :  g.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }
}
