package com.yahui.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-13
 */
public class BreadthFirstPaths {

    private boolean[] marked;
    private Queue<Integer> queue;
    private int[] edgeTo;
    private int s;

    public BreadthFirstPaths(Graph g, int s) {
        this.s = s;
        this.queue = new LinkedList<>();
        this.edgeTo = new int[g.V()];
        marked = new boolean[g.V()];
        bfs(g, s);
    }

    private void bfs(Graph g, int s) {
        queue.add(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int i : g.adj(v)) {
                if (!marked[i]) {
                    marked[i] = true;
                    edgeTo[i] = v;
                    queue.add(i);
                }
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
