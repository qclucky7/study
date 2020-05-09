package com.qingchen.study.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName TreeNodeUtils
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 18:26
 **/
public class TreeNodeUtils {


    private static ThreadLocal<Short> threadLocal = ThreadLocal.withInitial(() -> (short) 1);

    public static List<TreeNode> getNodeChild(List<TreeNode> list, List<TreeNode> treeNodeList,
                                               long parentId, int depth){
        System.out.println("thread = " + Thread.currentThread().getId());
        if (depth > Short.MAX_VALUE){
            return Collections.emptyList();
        }
        short start = 0;
        threadLocal.set((short)depth);
        System.out.println("threadLocal value = " + threadLocal.get());
        return getNodeChild(list, treeNodeList, parentId, start);
    }

    private static List<TreeNode> getNodeChild(List<TreeNode> list, List<TreeNode> treeNodeList,
                                               long parentId, short start){
        //System.out.println("Thread = " + Thread.currentThread().getId()+ " start = " + start);
        start++;
        for (TreeNode treeNode : list) {
            if (treeNode.getParentId() == parentId){
                List<TreeNode> treeNodes = new ArrayList<>();
                List<TreeNode> nodeChild = Collections.emptyList();
                if (start != threadLocal.get()){
                    nodeChild = getNodeChild(list, treeNodes, treeNode.getId(), start);
                }
                treeNode.setTreeNodes(nodeChild);
                treeNodeList.add(treeNode);
            }
        }
        return Collections.unmodifiableList(treeNodeList);
    }


    public static List<TreeNode> getNodeChild(List<TreeNode> list, List<TreeNode> treeNodeList,
                                               long parentId){

        for (TreeNode treeNode : list) {
            if (treeNode.getParentId() == parentId){
                List<TreeNode> treeNodes = new ArrayList<>();
                treeNode.setTreeNodes(getNodeChild(list, treeNodes, treeNode.getId()));
                treeNodeList.add(treeNode);
            }
        }
        return Collections.unmodifiableList(treeNodeList);
    }

}
