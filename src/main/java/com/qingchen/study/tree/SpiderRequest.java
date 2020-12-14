package com.qingchen.study.tree;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * @ClassName SpiderRequest
 * @description:
 * @author: WangChen
 * @create: 2020-09-24 09:23
 **/
public class SpiderRequest {

    @JsonProperty(value = "main_task_id")
    private String mainTaskId;

    @JsonProperty(value = "spider_name")
    private String spiderName;

    @JsonProperty(value = "website_name")
    private String websiteName;

    @JsonProperty(value = "spider_json")
    private Map<String, String> configJson;

    @JsonProperty(value = "callback_url")
    private String callbackUrl;

    @JsonProperty(value = "callback_data")
    private Map<String, String> callbackData;

    public String getMainTaskId() {
        return mainTaskId;
    }

    public void setMainTaskId(String mainTaskId) {
        this.mainTaskId = mainTaskId;
    }

    public String getSpiderName() {
        return spiderName;
    }

    public void setSpiderName(String spiderName) {
        this.spiderName = spiderName;
    }

    public Map<String, String> getConfigJson() {
        return configJson;
    }

    public void setConfigJson(Map<String, String> configJson) {
        this.configJson = configJson;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public Map<String, String> getCallbackData() {
        return callbackData;
    }

    public void setCallbackData(Map<String, String> callbackData) {
        this.callbackData = callbackData;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    @Override
    public String toString() {
        return "SpiderRequest{" +
                "mainTaskId='" + mainTaskId + '\'' +
                ", spiderName='" + spiderName + '\'' +
                ", websiteName='" + websiteName + '\'' +
                ", configJson=" + configJson +
                ", callbackUrl='" + callbackUrl + '\'' +
                ", callbackData=" + callbackData +
                '}';
    }
}
