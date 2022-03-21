package com.yahui.uf;

/**
 * 连通分量
 *
 * @author yanyahui <yanyahui@kuaishou.com>
 * Created on 2022-03-20
 */
public interface UF {
    default boolean connected(int v, int w) {
        return find(v) == find(w);
    }
    int find(int v);
    void union(int v, int w);
}
