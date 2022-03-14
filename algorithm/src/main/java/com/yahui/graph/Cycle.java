package com.yahui.graph;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-13
 */
public class Cycle {

    private boolean hasCycle;
    private boolean[] marked;


    public Cycle(Graph g) {
        this.marked = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                dfs(g, i, i);
            }
        }
    }

    private void dfs(Graph g, int v, int s) {
        marked[v] = true;
        for (int i : g.adj(v)) {
            if (hasCycle) {
                return;
            }
            if (!marked[i]) {
                dfs(g, i, v);
            } else {
                if (i != s) {
                    hasCycle = true;
                }
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
