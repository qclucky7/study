package com.qingchen.tdd;

import com.google.gson.annotations.SerializedName;

/**
 * @ClassName GsonFomat
 * @description:
 * @author: WangChen
 * @create: 2020-10-19 17:40
 **/
public class GsonFormat {


    /**
     * account_id : 11111
     * name : jack
     */

    @SerializedName("account_id")
    private String accountId;
    private String name;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
