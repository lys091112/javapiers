package com.xianyue.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * @author Xianyue
 */
public class ContainerWatcher implements Watcher{
    private static final Logger logger = LoggerFactory.getLogger(ContainerWatcher.class);
    private static final int SESSION_TIMEOUT = 5000;

    protected ZooKeeper zk;
    private CountDownLatch latch = new CountDownLatch(1);
    protected String host;

    public ContainerWatcher(String host) {
        this.host = host;
    }

    private void connect() throws Exception {
        zk = new ZooKeeper(host, SESSION_TIMEOUT ,this);
        latch.await();
    }

    @Override
    public void process(WatchedEvent event) {
        if(event.getState() == Event.KeeperState.SyncConnected) {
            latch.countDown();
        }
    }

    public void close() {
        try {
            this.zk.close();
        } catch (InterruptedException e) {
            logger.error("close zookeeper error. ost is {}, errorinfo is {}", host, e.getMessage());
        }
    }
}
