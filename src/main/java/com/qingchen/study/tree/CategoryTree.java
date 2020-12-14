package com.qingchen.study.tree;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * @ClassName CategoryTree
 * @description:
 * @author: WangChen
 * @create: 2020-09-24 18:29
 **/
public class CategoryTree {

    @JsonProperty(value = "parent_title")
    private String parentTitle;

    @JsonProperty(value = "url")
    private String url;

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "child_titles")
    private List<String> childTitles;

    @JsonProperty(value = "create_time")
    private Date createTime;

    public CategoryTree(String parentTitle, String url, String title, List<String> childTitles, Date createTime) {
        this.parentTitle = parentTitle;
        this.url = url;
        this.title = title;
        this.childTitles = childTitles;
        this.createTime = createTime;
    }

    @JsonProperty(value = "child_trees")
    private List<CategoryTree> childTrees;

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getChildTitles() {
        return childTitles;
    }

    public void setChildTitles(List<String> childTitles) {
        this.childTitles = childTitles;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<CategoryTree> getChildTrees() {
        return childTrees;
    }

    public void setChildTrees(List<CategoryTree> childTrees) {
        this.childTrees = childTrees;
    }

    @Override
    public String toString() {
        return "CategoryTree{" +
                "parentTitle='" + parentTitle + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", childTitles=" + childTitles +
                ", createTime=" + createTime +
                ", childTrees=" + childTrees +
                '}';
    }
}
