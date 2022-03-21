package com.yahui.mst;

import java.util.PriorityQueue;
import java.util.Queue;

import com.yahui.graph.Edge;
import com.yahui.graph.EdgeWeightedGraph;
import com.yahui.uf.QuickFindUF;
import com.yahui.uf.UF;

/**
 * Kruskal算法实现的最小生成数
 *
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-20
 */
public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph g) {
        // 1. 先排序边 2. 取出最小边，如果放到树里不会有环，就将边放到树中，然后union两个节点，循环直到无边
        PriorityQueue<Edge> pq = new PriorityQueue<>(g.E());
        UF uf = new QuickFindUF(g.V());
        for (Edge e : g.edges()) {
            pq.add(e);
        }

        while (!pq.isEmpty() && mst.size() < g.V() - 1) {
            Edge e = pq.poll();
            int v = e.either();
            int w = e.other(v);
            if (uf.connected(v, w)) {
                continue;
            }
            mst.add(e);
            uf.union(v, w);
        }
    }
}
