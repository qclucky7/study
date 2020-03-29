package com.qingchen.study.vlife;

import com.alibaba.fastjson.JSONObject;
import com.qingchen.study.utils.StringUtils;

import java.io.IOException;

/**
 * @ClassName UserSession
 * @Description
 * @Author xushenglai
 * @Date 2018/8/21 下午2:53
 */
public class UserSession {

    private String sid; //session_id
    //session创建时间
    private long create_time;
    private long userid;
    private long login_time;
    //安全
    private String check;
    private long expires_time;
    private String aesKey;
    private String aesIv;
    //soft信息
    private String package_name; // 包名
    private int major = 0;
    private int minor = 0;
    private long Channel_id;
    //手机信息
    private String language;
    private String model;//机型型号
    private String imei;
    private String mac;
    private String android_id;
    private String mcc = null;//MCC(移动国家码)
    private String mnc = null;//MNC(移动网络码)
    private String ip;

    //保存即时通讯账户信息
    private String nim_accid;


    //客户端类型
    private String client_type;

    /******** 以下为微信端登录信息  ****************/
    //保存appid
    private String open_id;
    //保存appid
    private String appid;
    //保存小程序版本
    private String mp_version;

    /******** 以下为WEB或APP登录信息  ****************/
    private String mobile_number;


    //TODO 以下数据即将删除218.03.30
    //用户类型：0-普通用户；1-公众号
    @Deprecated
    private int type;

    //用户信息是否已确认：0-游客；1-认证用户
    @Deprecated
    private long identified;

    //是否已通过认证：0-未认证；1-已认证；2-审核中
    @Deprecated
    private int authenticated;


    //保存ProductKey
    private String product_key;
    //保存ProductID
    private long product_id;

    /**
     * 老板身份id
     */
    private long boss_id;

    private long lastActivityTime;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getLogin_time() {
        return login_time;
    }

    public void setLogin_time(long login_time) {
        this.login_time = login_time;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public long getExpires_time() {
        return expires_time;
    }

    public void setExpires_time(long expires_time) {
        this.expires_time = expires_time;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getAesIv() {
        return aesIv;
    }

    public void setAesIv(String aesIv) {
        this.aesIv = aesIv;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public long getChannel_id() {
        return Channel_id;
    }

    public void setChannel_id(long channel_id) {
        Channel_id = channel_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getAndroid_id() {
        return android_id;
    }

    public void setAndroid_id(String android_id) {
        this.android_id = android_id;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNim_accid() {
        return nim_accid;
    }

    public void setNim_accid(String nim_accid) {
        this.nim_accid = nim_accid;
    }

    public String getClient_type() {
        return client_type;
    }

    public void setClient_type(String client_type) {
        this.client_type = client_type;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMp_version() {
        return mp_version;
    }

    public void setMp_version(String mp_version) {
        this.mp_version = mp_version;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getIdentified() {
        return identified;
    }

    public void setIdentified(long identified) {
        this.identified = identified;
    }

    public int getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(int authenticated) {
        this.authenticated = authenticated;
    }

    public String getProduct_key() {
        return product_key;
    }

    public void setProduct_key(String product_key) {
        this.product_key = product_key;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public long getBoss_id() {
        return boss_id;
    }

    public void setBoss_id(long boss_id) {
        this.boss_id = boss_id;
    }

    public long getLastActivityTime() {
        return lastActivityTime;
    }

    public void setLastActivityTime(long lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
    }

    public String formatJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("s", sid);
        jsonObject.put("ct", create_time);
        jsonObject.put("u", userid);
        jsonObject.put("lt", login_time);
        jsonObject.put("c", check);
        jsonObject.put("ak", aesKey);
        jsonObject.put("e", expires_time);
        jsonObject.put("aiv", aesIv);
        jsonObject.put("pn", package_name);
        jsonObject.put("ma", major);
        jsonObject.put("l", language);
        jsonObject.put("ip", ip);
        jsonObject.put("na", nim_accid);
        jsonObject.put("ctp", client_type);
        jsonObject.put("oid", open_id);
        jsonObject.put("appid", appid);
        jsonObject.put("mpver", mp_version);
        jsonObject.put("mn", mobile_number);
        jsonObject.put("t", type);
        jsonObject.put("identified", identified);
        jsonObject.put("authenticated", authenticated);
        jsonObject.put("pk", product_key);
        jsonObject.put("pid", product_id);
        jsonObject.put("bid", boss_id);
        return jsonObject.toString();
    }

    public UserSession parseFromJson(String json) throws IOException {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        UserSession session = new UserSession();
        session.setSid(jsonObject.getString("s"));
        session.setCreate_time(jsonObject.getLong("ct"));
        session.setUserid(jsonObject.getLong("u"));
        session.setLogin_time(jsonObject.getLong("lt"));
        session.setCheck(jsonObject.getString("c"));
        session.setExpires_time(jsonObject.getLong("e"));
        session.setAesKey(jsonObject.getString("ak"));
        session.setAesIv(jsonObject.getString("aiv"));
        session.setPackage_name(jsonObject.getString("pn"));
        session.setMajor(jsonObject.getIntValue("ma"));
        session.setLanguage(jsonObject.getString("l"));
        session.setIp(jsonObject.getString("ip"));
        session.setNim_accid(jsonObject.getString("na"));
        session.setClient_type(jsonObject.getString("ctp"));
        session.setOpen_id(jsonObject.getString("oid"));
        session.setAppid(jsonObject.getString("appid"));
        session.setMp_version(jsonObject.getString("mpver"));
        session.setMobile_number(jsonObject.getString("mn"));
        session.setType(jsonObject.getIntValue("t"));
        session.setIdentified(jsonObject.getLong("identified"));
        session.setAuthenticated(jsonObject.getIntValue("authenticated"));
        session.setProduct_key(jsonObject.getString("pk"));
        session.setProduct_id(jsonObject.getLong("pid"));
        session.setBoss_id(jsonObject.getLong("bid"));
        return session;
    }

}
