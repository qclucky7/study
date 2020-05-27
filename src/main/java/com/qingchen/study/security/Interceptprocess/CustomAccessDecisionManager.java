//package com.qingchen.study.security.Interceptprocess;
//
//import org.apache.commons.collections4.CollectionUtils;
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.authentication.AnonymousAuthenticationToken;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.util.Collection;
//
///**
// * @ClassName CustomAccessDecisionManager
// * @description: 验证当前用户是否有此权限
// * @author: WangChen
// * @create: 2020-05-25 11:36
// **/
//@Component
//public class CustomAccessDecisionManager implements AccessDecisionManager {
//
//    @Override
//    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
//
//        /*
//         * authentication： 当前登录用户信息
//         * o：当前请求对象(FilterInvocation对象)
//         *collection：FilterInvocationSecurityMetadataSource接口实现类中getAttributes方法的返回值
//         * */
//        System.out.println("Authentication = " + authentication.getAuthorities().toString() + "\n" +
//                           "Object = " + o.toString() + "\n" +
//                           "collection = " + collection.toString()
//                );
//
//        //用户未登录
//        if (authentication instanceof AnonymousAuthenticationToken) {
//            throw new AccessDeniedException("非法请求!");
//        }
//        //请求路径不在数据库中
//        if (CollectionUtils.isEmpty(collection)){
//            return;
//        }
//        for (ConfigAttribute attribute : collection) {
//            //获取当前登录用户的角色
//            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//            for (GrantedAuthority authority : authorities) {
//                //当前登录用户具备所需的角色 则无需判断
//                if (authority.getAuthority().equals(attribute.getAttribute())) {
//                    return;
//                }
//            }
//        }
//        throw new AccessDeniedException("非法请求!");
//    }
//
//    @Override
//    public boolean supports(ConfigAttribute configAttribute) {
//        return true;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return true;
//    }
//}
