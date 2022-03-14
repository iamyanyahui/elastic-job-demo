package com.yahui.graph;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-13
 */
public class Search {
    private boolean[] marked;
    private int count;

    public Search(Graph g, int s) {
        marked = new boolean[g.V()];
        count = 0;
        dfs(g, s);
    }

    private void dfs(Graph g, int s) {
        marked[s] = true;
        count++;
        for (int i : g.adj(s)) {
            if (!marked[i]) {
                dfs(g, i);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }
}
