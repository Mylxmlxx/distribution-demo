package com.yzy.distributiondemo.DistributedAlgorithms;

import java.util.*;

/**
 * @author yzy
 * @version 1.0
 * @description TODO
 * @date 2023/8/7 13:27
 */
public class ConsistentHash {
    private final List<Node> realNodes = new ArrayList<>();//物理节点
    private final Integer virtualNodeNum = 10;//虚拟节点数量
    private final Map<Node, List<Integer>> virtualNodes = new HashMap<>();//物理节点和虚拟节点的映射关系
    private final SortedMap<Integer, Node> hashRing = new TreeMap<>();//哈希环

    public void addServer(Node node) {
        realNodes.add(node);
        List<Integer> virtualNodeList = new ArrayList<>();
        for (Integer i = 0; i < virtualNodeNum; i++) {
            Node v = new Node(node.getName() + "--" + i, node.getId() + "--" + i);
            int hash = v.FNVHash(v.getId());
            hashRing.put(hash, v);
            virtualNodeList.add(hash);
        }
        virtualNodes.put(node, virtualNodeList);
    }


    public void removeServer(Node node) {
        realNodes.remove(node);
        List<Integer> virtualNodeList = virtualNodes.get(node);
        for (Integer i : virtualNodeList) {
            hashRing.remove(i);
        }
        // 移除物理节点和虚拟节点的映射关系
        virtualNodes.remove(node, virtualNodeList);
    }

    public Node getServer(String key) {
        // 计算key的hash值
        int hash = new Node().FNVHash(key);
        SortedMap<Integer, Node> subMap = hashRing.tailMap(hash);
        if (subMap.isEmpty()) {
            return hashRing.get(hashRing.firstKey());
        } else {
            return subMap.get(subMap.firstKey());
        }
    }
}
