package com.yahui.graph;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-13
 */
public class CC {

    private int count;
    private int[] id; // 顶点所在连通分量的id
    private boolean[] marked;


    public CC(Graph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                dfs(g, i);
                count++;
            }
        }
    }

    private void dfs(Graph g, int i) {
        marked[i] = true;
        id[i] = count;
        for (int j : g.adj(i)) {
            if (!marked[j]) {
                dfs(g, j);
            }
        }
    }

    public boolean connected(int v, int w) {
        return id(v) == id(w);
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }
}
