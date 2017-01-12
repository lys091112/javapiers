package com.xianyue.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xianyue
 */
public class AppServer {

    private static final Logger logger    = LoggerFactory.getLogger(AppServer.class);
    private String              groupNode = "ygroup";
    private String              subNode   = "sub";

    public void connectZookeeper(String address) throws Exception {
        ZooKeeper zk = new ZooKeeper("localhost:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("get connect state: " + event.getState());
            }
        });

        //节点的创建是以/为区分，如果只有一个/,代表znode节点，如果有多个，那么代表在某个nameSpace下创建某个subPath
        String createPath = zk.create("/" + groupNode + "/" + subNode, address.getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
//        String createPath = zk.create("/man" + groupNode , address.getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("create : " + createPath);
    }

    public void handle() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {
        String address = "server-" + System.currentTimeMillis();
        if (args.length != 0) {
           address = args[0];
        }

        AppServer as = new AppServer();
        as.connectZookeeper(address);
        as.handle();
    }
}
