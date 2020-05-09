package com.qingchen.study.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Test
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 20:14
 **/
public class Test {

    @org.junit.Test
    public void myTest() throws Exception{

        List<TreeNode> treeNodes = Arrays.asList(
                new TreeNode(0, -1, "root"),
                new TreeNode(1, 0, "子节点_1层"),
                new TreeNode(2, 0, "子节点_1层"),
                new TreeNode(3, 1, "子节点_2层"),
                new TreeNode(4, 1, "子节点_2层"),
                new TreeNode(5, 2, "子节点_3层"),
                new TreeNode(6, 3, "子节点_4层"),
                new TreeNode(7, -1, "子节点_1层")
        );

        CompletableFuture<List<TreeNode>> listCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            List<TreeNode> treeNodeList = new ArrayList<>();
            return TreeNodeUtils.getNodeChild(treeNodes, treeNodeList,0, 2);
        }, Executors.newFixedThreadPool(1));
        List<TreeNode> treeNodes2 = listCompletableFuture2.get();

        System.out.println("treeNode2 = " + treeNodes2.toString());

        CompletableFuture<List<TreeNode>> listCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            List<TreeNode> treeNodeList = new ArrayList<>();
            return TreeNodeUtils.getNodeChild(treeNodes, treeNodeList, 0, 1);
        });
        List<TreeNode> treeNodes1 = listCompletableFuture1.get();

        System.out.println("treeNode1 = " + treeNodes1.toString());
        System.out.println("treeNode2 = " + treeNodes2.toString());


        //List<TreeNode> child = getNodeChild(treeNodes, treeNodeList,0, 3);

    }
}
