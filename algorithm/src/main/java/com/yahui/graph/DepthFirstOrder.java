package com.yahui.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-13
 */
public class DepthFirstOrder {

    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Deque<Integer> reversePost;

    private boolean[] marked;

    public DepthFirstOrder(Digraph g) {
        marked = new boolean[g.V()];
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new ArrayDeque<>();

        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                dfs(g, i);
            }
        }
    }

    private void dfs(Digraph g, int s) {
        marked[s] = true;
        pre.add(s);
        for (int v : g.adj(s)) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
        post.add(s);
        reversePost.push(s);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
