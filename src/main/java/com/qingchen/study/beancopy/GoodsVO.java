package com.qingchen.study.beancopy;

/**
 * @ClassName GoodsVO
 * @description:
 * @author: WangChen
 * @create: 2020-06-26 19:58
 **/
public class GoodsVO{

    private String goodName;
    private String url;


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

    @Override
    public String toString() {
        return "GoodsVO{" +
                "goodName='" + goodName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
