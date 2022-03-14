package com.yahui.graph;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-13
 */
public class Topological {

    private Iterable<Integer> order;

    public Topological(Digraph g) {
        DirectedCycle cycle = new DirectedCycle(g);
        if (!cycle.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(g);
            order = depthFirstOrder.reversePost();
        }
    }

    public boolean idDAG() {
        return order != null;
    }

    public Iterable<Integer> order() {
        return order;
    }
}
