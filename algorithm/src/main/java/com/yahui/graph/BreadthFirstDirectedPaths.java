package com.yahui.graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-13
 */
public class BreadthFirstDirectedPaths {

    private int s;
    private boolean[] marked;
    private int[] edgeTo;
    private Queue<Integer> queue;

    public BreadthFirstDirectedPaths(Digraph g, int s) {
        this.s = s;
        this.marked = new boolean[g.V()];
        this.edgeTo = new int[g.V()];
        queue = new LinkedList<>();
        queue.add(s);
        bfs(g);
    }

    private void bfs(Digraph g) {
        while (!queue.isEmpty()) {
            int v = queue.poll();
            marked[v] = true;
            for (int w : g.adj(v)) {
                if (!marked[w]) {
                    queue.add(w);
                    edgeTo[w] = v;
                }
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
        Deque<Integer> stack = new LinkedList<>();
        for (int i=v; i!=s; i = edgeTo[i]) {
            stack.push(i);
        }
        stack.push(s);
        return stack;
    }
}
