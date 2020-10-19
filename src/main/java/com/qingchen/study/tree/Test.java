package com.qingchen.study.tree;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;
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


        List<CategoryTree> treeNodes1 = Arrays.asList(
                new CategoryTree(null, "XXXX", "饮料", null, new Date()),
                new CategoryTree(null, "XXXX", "饭", null, new Date()),
                new CategoryTree("饮料", "xxxxx", "可乐", null, new Date()),
                new CategoryTree("饮料", "XXXXX", "雪碧", null, new Date()),
                new CategoryTree("可乐", "xxxx", "百事可乐", null, new Date()),
                new CategoryTree("可乐", "xxxx", "可口可乐", null, new Date()),
                new CategoryTree("饭", "XXXX", "宫保鸡丁", null, new Date())
        );


        List<TreeNode> treeNodeList = new ArrayList<>();
        List<TreeNode> nodeChild = TreeNodeUtils.getNodeChild(treeNodes, treeNodeList, 0);

        List<CategoryTree> trees = new ArrayList<>();
        List<CategoryTree> nodeChilds = TreeNodeUtils.getNodeChild(treeNodes1, trees, null);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(nodeChilds));


    }

    @org.junit.Test
    public void test01() throws IOException {

        Map<String, String> callbackData = new HashMap<>(1);
        callbackData.put("asin_task_id", "123");

        Map<String, String> spiderJson = new HashMap<>(5);

        spiderJson.put("asin", "ABCDEF");
        spiderJson.put("version", String.valueOf(System.currentTimeMillis()));
        spiderJson.put("db_collection", "default");
        spiderJson.put("max_result_count", "10000");
        spiderJson.put("address", "");


        JSONObject req = new JSONObject();
        req.put("callback_data", callbackData);
        req.put("website_name", "uk");
        req.put("callback_url", "http://localhost:9999/v1/asin-tasks/call_back");
        req.put("spider_name", "details");
        req.put("spider_json", spiderJson);


        String s = JSON.toJSONString(req);


        ObjectMapper objectMapper = new ObjectMapper();
        SpiderRequest spiderRequest = objectMapper.readValue(s, SpiderRequest.class);


        System.out.println(spiderRequest.toString());


    }

}
