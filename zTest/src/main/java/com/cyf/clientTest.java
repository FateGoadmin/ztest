package com.cyf;

import org.apache.zookeeper.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;

/**
 * @author cyf
 * @version 1.0
 * @date 2021/1/26 0026
 * @description
 **/
public class clientTest {


    private ZooKeeper zookeeper;

    @Before
    public void init() throws IOException, KeeperException, InterruptedException {
        final String  connectionString = "182.254.134.57:2182,182.254.134.57:2283,182.254.134.57:2383";
        zookeeper = new ZooKeeper(connectionString, 2000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

                try {
                    System.out.println(".......start........");
                    List<String> children = zookeeper.getChildren("/",true);
                    for(String iterator :children){
                        System.out.println(iterator + ",");
                    }
                    System.out.println("....end....");
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Test
    public void testCache() throws KeeperException, InterruptedException {
        String path = zookeeper.create("/testZookeeper", "hello Zookeeper".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        Assert.assertTrue("/testZookeeper".equals(path));
    }


    @Test
    public void testWatch() throws KeeperException, InterruptedException {
        List<String> children = zookeeper.getChildren("/",true);
        Thread.sleep(Long.MAX_VALUE);
   }


}
