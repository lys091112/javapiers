package com.xianyue.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  Xianyue
 */
public class AppClient {
//    private static final Logger logger = LoggerFactory.getLogger(AppClient.class);
    private String groupNode = "ygroup";
    private ZooKeeper zk;
    private Stat stat = new Stat();
    private volatile List<String> serverList = new ArrayList<>();
    private boolean onecFlag = true;

    public void connectZookeeper() throws Exception {
        zk = new ZooKeeper("localhost:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("get info ,path is " + event.getPath());
                if ( event.getType() == Event.EventType.NodeChildrenChanged && ("/" + groupNode).equals(event.getPath())) {
                    try {
                        updateServerList();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        updateServerList();
    }

    private void updateServerList() throws Exception {
        List<String> newServerList = new ArrayList<>();

        List<String> subList = zk.getChildren("/" + groupNode, onecFlag);
        onecFlag = false;
        for (String subNode: subList ) {
           byte[] data = zk.getData("/" + groupNode + "/" + subNode,false,stat);
            newServerList.add(new String(data, "utf-8"));
        }

        serverList = newServerList;

        System.out.println("server list updated: " + serverList);
    }

    public static void main(String[] args) throws Exception {
        AppClient client = new AppClient();
        client.connectZookeeper();
        client.handle();
    }

    private void handle() throws InterruptedException {
        System.out.println("sleep ");
        Thread.sleep(Long.MAX_VALUE);
    }
}
