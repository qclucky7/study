package com.qingchen.study.beancopy;

/**
 * @ClassName Goods
 * @description:
 * @author: WangChen
 * @create: 2020-06-26 19:55
 **/
public class Goods extends BaseEntity{

    private String goodName;
    private String url;

    public Goods(String goodName, String url) {
        this.goodName = goodName;
        this.url = url;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
