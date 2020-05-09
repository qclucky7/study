package com.qingchen.study.jwt;

import com.alibaba.fastjson.JSON;

import java.time.Duration;
import java.util.Base64;

/**
 * @ClassName JWTUtils
 * @description:
 * @author: WangChen
 * @create: 2020-05-03 15:42
 **/
public class JWTUtils {

    private static final long EXPIRE_TIME = Duration.ofSeconds(10).toMillis();

    private static final String TOKEN_HEADER = Base64.getUrlEncoder()
            .encodeToString(JSON.toJSONString(new TokenHeader("jwt", "SHA256")).getBytes());

    private JWTUtils(){}

    private static class JWTUtilsProvider{
        private static JWTUtils jwtUtils = new JWTUtils();
    }

    public static JWTUtils getInstance(){
        return JWTUtilsProvider.jwtUtils;
    }

    public String getToken(long userId){
        return this.generateToken(userId);
    }

    private String handleTokenBody(long userId){
        TokenBody tokenBody = new TokenBody(
                userId,
                System.currentTimeMillis() + EXPIRE_TIME,
                System.currentTimeMillis());
        return Base64.getUrlEncoder().encodeToString(JSON.toJSONString(tokenBody).getBytes());
    }

    public String generateSecret(String tokenHeader, String tokenBody){
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder secretBody = stringBuilder
                .append(tokenHeader)
                .append(".")
                .append(tokenBody);
        return SHA256Utils.encode(JSON.toJSONString(secretBody));
    }

    private String generateToken(long userId){
        StringBuilder stringBuilder = new StringBuilder();
        String tokenBody = this.handleTokenBody(userId);
        String secret = this.generateSecret(TOKEN_HEADER, tokenBody);
        return stringBuilder
                     .append(TOKEN_HEADER)
                     .append(".")
                     .append(tokenBody)
                     .append(".")
                     .append(secret).toString();
    }


    public static class TokenBody{
        private long userId;
        private long expire;
        private long createTime;
        public TokenBody(long userId, long expire, long createTime) {
            this.userId = userId;
            this.expire = expire;
            this.createTime = createTime;
        }
        public long getUserId() {
            return userId;
        }
        public void setUserId(long userId) {
            this.userId = userId;
        }
        public long getExpire() {
            return expire;
        }
        public void setExpire(long expire) {
            this.expire = expire;
        }
        public long getCreateTime() {
            return createTime;
        }
        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
        @Override
        public String toString() {
            return "TokenBody{" +
                    "userId=" + userId +
                    ", expire=" + expire +
                    ", createTime=" + createTime +
                    '}';
        }
    }


    public static class TokenHeader{
        //表示这是一个jwt
        private String typ;
        //表示这个用到的算法
        private String alg;
        public TokenHeader(String typ, String alg) {
            this.typ = typ;
            this.alg = alg;
        }
        public String getTyp() {
            return typ;
        }
        public void setTyp(String typ) {
            this.typ = typ;
        }
        public String getAlg() {
            return alg;
        }
        public void setAlg(String alg) {
            this.alg = alg;
        }
        @Override
        public String toString() {
            return "TokenHeader{" +
                    "typ='" + typ + '\'' +
                    ", alg='" + alg + '\'' +
                    '}';
        }
    }
}
