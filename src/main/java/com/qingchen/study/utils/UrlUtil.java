package com.qingchen.study.utils;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * @Auther: ywx
 * @Date: 18-10-10 18:02
 * @Description:
 */
public class UrlUtil {
    // http头 userAgent
    private static String[] userAgent = {
            "Mozilla/5.0 (Linux; U; Android 2.2; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.2",
            "Mozilla/5.0 (iPad; U; CPU OS 3_2_2 like Mac OS X; en-us) AppleWebKit/531.21.10 (KHTML, like Gecko) Version/4.0.4 Mobile/7B500 Safari/531.21.11",
            "Mozilla/5.0 (SymbianOS/9.4; Series60/5.0 NokiaN97-1/20.0.019; Profile/MIDP-2.1 Configuration/CLDC-1.1) AppleWebKit/525 (KHTML, like Gecko) BrowserNG/7.1.18121",
            "Nokia5700AP23.01/SymbianOS/9.1 Series60/3.0",
            "UCWEB7.0.2.37/28/998",
            "NOKIA5700/UCWEB7.0.2.37/28/977",
            "Openwave/UCWEB7.0.2.37/28/978",
            "Mozilla/4.0 (compatible; MSIE 6.0; ) Opera/UCWEB7.0.2.37/28/989"
    };

    /**
     * 根据url建立HttpURLConnection,并且做相应的爬虫配置，以防被对方拦截
     * 此方法仅支持HTTP请求方式
     * @param url
     * @param cookie
     * @return
     * @throws IOException
     */
    public static HttpURLConnection getInternetWormHttpConnection(@NonNull String url, @Nullable String cookie) throws IOException {
        Assert.notNull(url, "[UrlUtil] [getInternetWormHttpConnection] [url is empty!]");
        URL urlObj = new URL(url);
        HttpURLConnection http = (HttpURLConnection) urlObj.openConnection();
        http.setDoOutput(true);
        http.setRequestProperty("Pragma", "no-cache");
        http.setRequestProperty("Cache-Control", "no-cache");
        // 增加cookie
        if (StringUtils.isNotEmpty(cookie)) {
            http.setRequestProperty("Cookie", cookie);
        }
        // 模拟手机系统 User-Agent会告诉网站服务器，访问者是通过什么工具来请求的，如果是爬虫请求，一般会拒绝，如果是用户浏览器
        http.setRequestProperty("User-Agent", userAgent[new Random().nextInt(8)]);
        // 只接受text/html类型，当然也可以接受图片,pdf,*/*任意，就是tomcat/conf/web里面定义那些
        http.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        // 连接超时30秒
        System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
        // 读取超时30秒
        System.setProperty("sun.net.client.defaultReadTimeout", "30000");
        http.connect();
        return http;
    }

    /**
     * 根据url建立HttpURLConnection,并且做相应的爬虫配置，以防被对方拦截
     * 此方法仅支持HTTP请求方式
     * @param url
     * @return
     * @throws IOException
     */
    public static HttpURLConnection getInternetWormHttpConnection(@NonNull String url) throws IOException {
        return getInternetWormHttpConnection(url, null);
    }
}
