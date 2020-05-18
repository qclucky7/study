package com.qingchen.study.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @ClassName Goods
 * @description:
 * @author: WangChen
 * @create: 2020-05-18 17:46
 **/
@Document(indexName = "index_goods", type = "good_list")
public class Goods {

    @Id
    @Field(type = FieldType.Long)
    private Long id;
    @Field(type = FieldType.Text)
    private String goodsName;
    @Field(type = FieldType.Text)
    private String goodsContent;
    @Field(type = FieldType.Text)
    private String keyWorld;
    @Field(type = FieldType.Text)
    private String type;

    public Goods() {
    }

    public Goods(Long id, String goodsName, String goodsContent, String keyWorld, String type) {
        this.id = id;
        this.goodsName = goodsName;
        this.goodsContent = goodsContent;
        this.keyWorld = keyWorld;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsContent() {
        return goodsContent;
    }

    public void setGoodsContent(String goodsContent) {
        this.goodsContent = goodsContent;
    }

    public String getKeyWorld() {
        return keyWorld;
    }

    public void setKeyWorld(String keyWorld) {
        this.keyWorld = keyWorld;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsContent='" + goodsContent + '\'' +
                ", keyWorld='" + keyWorld + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
