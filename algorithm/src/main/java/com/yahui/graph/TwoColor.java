package com.yahui.graph;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-13
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColor = true;

    public TwoColor(Graph g) {
        marked = new boolean[g.V()];
        color = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                dfs(g, i);
            }
        }
    }

    private void dfs(Graph g, int i) {
        marked[i] = true;
        for (int j : g.adj(i)) {
            if (!marked[j]) {
                color[j] = !color[i];
                dfs(g, j);
            } else {
                if (color[i] == color[j]) {
                    isTwoColor = false;
                }
            }
        }
    }

    public boolean isTwoColor() {
        return isTwoColor;
    }
}
