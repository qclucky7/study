package com.qingchen.study.prototype;

import java.util.Objects;

/**
 * @ClassName Quote
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 13:39
 **/
public class Quote implements Cloneable{

    private long quoteId;

    private String name;

    public Quote(long quoteId, String name) {
        this.quoteId = quoteId;
        this.name = name;
    }

    public long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(long quoteId) {
        this.quoteId = quoteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Quote clone() throws CloneNotSupportedException {
        return (Quote)super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quote)) return false;
        Quote quote = (Quote) o;
        return getQuoteId() == quote.getQuoteId() &&
                Objects.equals(getName(), quote.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuoteId(), getName());
    }

    @Override
    public String toString() {
        return "Quote{" +
                "quoteId=" + quoteId +
                ", name='" + name + '\'' +
                '}';
    }
}
