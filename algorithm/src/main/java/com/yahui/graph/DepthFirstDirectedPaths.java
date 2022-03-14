package com.yahui.graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-13
 */
public class DepthFirstDirectedPaths {

    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public DepthFirstDirectedPaths(Digraph g, int s) {
        this.s = s;
        this.marked = new boolean[g.V()];
        this.edgeTo = new int[g.V()];
        dfs(g, s);
    }

    private void dfs(Digraph g, int s) {
        marked[s] = true;
        for (int v : g.adj(s)) {
            if (!marked[v]) {
                edgeTo[v] = s;
                dfs(g, v);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i=v; i!=s; i=edgeTo[i]) {
            stack.push(i);
        }
        stack.push(s);
        return stack;
    }
}
