package com.yahui.graph;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-13
 */
public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph g, int s) {
        this.marked = new boolean[g.V()];
        dfs(g, s);
    }

    private void dfs(Digraph g, int s) {
        marked[s] = true;
        for (int v : g.adj(s)) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    public DirectedDFS(Digraph g, Iterable<Integer> sources) {
        this.marked = new boolean[g.V()];
        for (int s : sources) {
            if (!marked[s]) {
                dfs(g, s);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }
}
