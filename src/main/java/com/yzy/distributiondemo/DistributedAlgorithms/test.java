package com.yzy.distributiondemo.DistributedAlgorithms;

/**
 * @author yzy
 * @version 1.0
 * @description TODO
 * @date 2023/8/7 14:00
 */
public class test {
    public static void main(String[] args) {
        ConsistentHash consistentHash = new ConsistentHash();
        Node node1 = new Node("192.168.0.1", "成都服务器一号");
        Node node2 = new Node("192.168.0.2", "成都服务器二号");
        Node node3 = new Node("192.168.0.3", "成都服务器三号");
        consistentHash.addServer(node1);
        consistentHash.addServer(node2);
        consistentHash.addServer(node3);

        for (int i = 0; i < 10; i++) {
            String data = "数据" + i;
            Node server = consistentHash.getServer(data);
            System.out.println(data + "分配到的服务器为：" + server.getName());
        }

        System.out.println("删除服务器一号");
        consistentHash.removeServer(node1);
        for (int i = 0; i < 10; i++) {
            String data = "数据" + i;
            Node server = consistentHash.getServer(data);
            System.out.println(data + "分配到的服务器为：" + server.getName());
        }
    }
}
