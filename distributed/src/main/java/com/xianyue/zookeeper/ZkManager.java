package com.xianyue.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * @author Xianyue
 */
public class ZkManager extends ContainerWatcher {
    private static final Logger logger = LoggerFactory.getLogger(ZkManager.class);
    private String              znodeName;

    public ZkManager(String host, String znodeName) {
        super(host);
        this.znodeName = "/" + znodeName;
    }

    /**
     * 创建zookeeper group, 如果存在，则直接返回true
     * 
     * @return
     */
    public boolean createZnode() {
        try {
            if (zk.exists(znodeName, true) == null) {
                zk.create(znodeName, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            }
        } catch (InterruptedException | KeeperException e) {
            logger.error("create group error. zookeeper host:{}, groupName: {},error: {}", host, znodeName, e.getMessage());
            return false;
        }
        return true;
    }

    public boolean createSubNode(String nodeName, String content) {
        String fullPath = znodeName + "/" + nodeName;
        try {
            zk.create(fullPath, content.getBytes(Charset.forName("utf-8")), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            logger.info("create path : {}", fullPath);
        } catch (KeeperException | InterruptedException e) {
            logger.error("create subNode error. host: {}, znodeName: {}, subNodeName: {},errorMessage: {}", host, znodeName, nodeName, e.getMessage());
            return false;
        }
        return true;
    }


    public void setData(String name, String content) {

    }
}
