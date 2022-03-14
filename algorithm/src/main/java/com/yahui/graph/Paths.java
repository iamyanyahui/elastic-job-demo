package com.yahui.graph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-13
 */
public class Paths {
    private boolean[] marked;
    private int[] edgeTo; // 从起点到某一个顶点的已知路径的最后一个顶点
    private int s; // 起点

    public Paths(Graph g, int s) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        dfs(g, s);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int i : g.adj(v)) {
            if (!marked[i]) {
                edgeTo[i] = v;
                dfs(g, i);
            }
        }
    }

    public boolean hasPath(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPath(v)) {
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
