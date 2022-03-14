package com.yahui.graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-13
 */
public class DirectedCycle {

    private boolean[] marked;
    private int[] edgeTo;
    private Deque<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph g) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        onStack = new boolean[g.V()];

        for (int i = 0; i <g.V(); i++) {
            if (!marked[i]) {
                dfs(g, i);
            }
        }
    }

    private void dfs(Digraph g, int i) {
        marked[i] = true;
        onStack[i] = true;
        for (int v : g.adj(i)) {
            if (hasCycle()) {
                return;
            }
            if (!marked[v]) {
                edgeTo[v] = i;
                dfs(g, v);
            } else if (onStack[v]){
                cycle = new ArrayDeque<>();
                for (int j = i; j!=v; j=edgeTo[j]) {
                    cycle.add(j);
                }
                cycle.add(v);
                cycle.add(i);
            }
        }
        onStack[i] = false;
    }

    public boolean hasCycle() {
        return this.cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
