//package com.qingchen.study.security.Interceptprocess;
//
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//
///**
// * @ClassName PermissionFilter
// * @description: 路径匹配角色
// * @author: WangChen
// * @create: 2020-05-25 11:34
// **/
//@Component
//public class PermissionFilter implements FilterInvocationSecurityMetadataSource {
//
//    private static Map<String, List<ConfigAttribute>> map;
//
//    //@Resource
//    //private IRoleMapper roleMapper;
//
//    @PostConstruct
//    public void initialize(){
//
////        List<RoleBO> roles = roleMapper.queryRoleByPermission();
////        map = roles.stream()
////                .collect(Collectors.groupingByConcurrent(RoleBO::getPath,
////                         Collectors.mapping(roleBO -> new SecurityConfig(roleBO.getRoleName()),
////                                Collectors.toList())
////                ));
//    }
//
//    @Override
//    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
//
//        /*根据请求地址 分析请求该地址需要什么角色*/
//        //获取请求地址
//        String path = ((FilterInvocation) o).getRequestUrl();
//
//        for (Map.Entry<String, List<ConfigAttribute>> next : map.entrySet()) {
//            if (path.equals(next.getKey())) {
//                return next.getValue();
//            }
//        }
//        return Collections.emptyList();
//
//    }
//
//    @Override
//    public Collection<ConfigAttribute> getAllConfigAttributes() {
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return true;
//    }
//}
