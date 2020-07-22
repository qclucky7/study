//package com.qingchen.study.elasticsearch.entity;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
///**
// * @ClassName Article
// * @description:
// * @author: WangChen
// * @create: 2020-05-16 20:18
// **/
//@Document(indexName = "index_article", type = "article_01")
//public class Article {
//
//    @Id
//    @Field(type = FieldType.Long)
//    private Long id;
//    @Field(type = FieldType.Text, store = true)
//    private String title;
//    @Field(type = FieldType.Text, store = true)
//    private String context;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContext() {
//        return context;
//    }
//
//    public void setContext(String context) {
//        this.context = context;
//    }
//}
