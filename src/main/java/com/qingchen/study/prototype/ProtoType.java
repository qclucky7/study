package com.qingchen.study.prototype;

import java.util.Objects;

/**
 * @ClassName ProtoType
 * @description:
 * @author: WangChen
 * @create: 2020-04-23 13:38
 **/
public class ProtoType implements Cloneable{


    private long id;
    private Quote quote;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "ProtoType{" +
                "id=" + id +
                ", quote=" + quote +
                '}';
    }

    @Override
    protected ProtoType clone() throws CloneNotSupportedException {
        ProtoType clone = (ProtoType)super.clone();
        clone.setQuote(getQuote().clone());
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProtoType)) return false;
        ProtoType protoType = (ProtoType) o;
        return getId() == protoType.getId() &&
                Objects.equals(getQuote(), protoType.getQuote());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getQuote());
    }
}
