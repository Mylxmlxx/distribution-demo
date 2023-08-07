package com.yzy.distributiondemo.DistributedAlgorithms;

import java.util.Map;

/**
 * @author yzy
 * @version 1.0
 * @description TODO
 * @date 2023/8/7 13:28
 */
public class Node {
    String name;
    String id;

    public Node() {
    }

    public Node(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int FNVHash(String data) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < data.length(); i++) {
            hash = (hash ^ data.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return Math.abs(hash);
    }
}
