package com.qingchen.study.filter.springbootfilter;

import com.qingchen.study.utils.StringUtils;
import com.qingchen.study.vlife.ErrorCode;
import com.qingchen.study.vlife.ErrorCodeException;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName MyFilter
 * @description:
 * @author: WangChen
 * @create: 2020-03-31 19:49
 **/
public class MyFilter implements Filter {


    Set<String> noFilteringUrl = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        noFilteringUrl.add("/my/test");
        noFilteringUrl.add("/redis/user");
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter执行！");
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestUri = getRequestUri(request);
        System.out.println("requestUri" + requestUri);
//        if (!noFilteringUrl.contains(requestUri)){
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"未经授权");
//        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


    /**
     * 获取请求的URI，这里是去掉 Context Path 的URI
     *
     * @param request
     * @return
     */
    private String getRequestUri(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        if (contextPath != null && uri.startsWith(contextPath)) {
            uri = uri.substring(contextPath.length());
        }
        if (StringUtils.isEmpty(uri)) {
            return "";
        } else {
            if (!uri.startsWith("/")) {
                uri = "/" + uri;
            }
            String newUri;
//            if (!StringUtils.isEmpty(baseUriPath) && uri.startsWith(basePackage)) {
//                newUri = uri.replaceFirst(baseUriPath, "");
//            } else {
//                newUri = uri;
//            }
            newUri = uri;
            return newUri;
        }
    }
}
