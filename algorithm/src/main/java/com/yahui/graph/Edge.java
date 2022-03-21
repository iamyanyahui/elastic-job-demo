package com.yahui.graph;

/**
 * 带权重的边
 *
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-20
 */
public class Edge implements Comparable<Edge> {
    private int v;
    private int w;
    private double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either() {
        return v;
    }

    public int other(int i) {
        if (i == v) {
            return w;
        }
        return v;
    }

    public double weight() {
        return weight;
    }


    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.weight, o.weight);
    }
}
