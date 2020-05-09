package com.qingchen.study.tree;

import java.util.List;

/**
 * @ClassName TreeNode
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 18:26
 **/
public class TreeNode {

    private long id;

    private long parentId;

    private String name;

    private List<TreeNode> treeNodes;

    public TreeNode(long id, long parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeNode> getTreeNodes() {
        return treeNodes;
    }

    public void setTreeNodes(List<TreeNode> treeNodes) {
        this.treeNodes = treeNodes;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", treeNodes=" + treeNodes +
                '}';
    }
}
