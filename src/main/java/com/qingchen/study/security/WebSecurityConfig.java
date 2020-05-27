//package com.qingchen.study.security;
//
//import com.qingchen.study.security.Interceptprocess.CustomAccessDecisionManager;
//import com.qingchen.study.security.Interceptprocess.PermissionFilter;
//import com.qingchen.study.security.globalsecurityex.JwtAuthenticationEntryPoint;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.ObjectPostProcessor;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//
///**
// * @ClassName WebSecurityConfig
// * @description:
// * @author: WangChen
// * @create: 2020-05-22 15:05
// **/
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private JwtAuthenticationEntryPoint unauthorizedHandler;
//
////    @Autowired
////    private UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    private CustomAccessDecisionManager customAccessDecisionManager;
//
//    @Autowired
//    private PermissionFilter permissionFilter;
//
//
//    @Autowired
//    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        //authenticationManagerBuilder
//                //.userDetailsService(userDetailsService)
//                //.passwordEncoder(passwordEncoder());
//
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
////    @Bean
////    public JwtAuthenticationTokenFilter authenticationTokenFilterBean(){
////        return new JwtAuthenticationTokenFilter();
////
////    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity
//                //token的验证方式不需要开启csrf的防护
//                .csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
//                .and()
//                //设置无状态的连接,即不创建session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests()
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
//                        o.setAccessDecisionManager(customAccessDecisionManager);
//                        o.setSecurityMetadataSource(permissionFilter);
//                        return o;
//                    }
//                })
//                //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                //当前的url允许进行匿名访问,即不需要身份认证
////                .antMatchers(
////                        HttpMethod.GET,
////                        "/",
////                        "/*.html",
////                        "/favicon.ico",
////                        "/**/*.html",
////                        "/**/*.css",
////                        "/**/*.js"
////                ).permitAll()
//                .antMatchers(HttpMethod.POST,
//                        "/user/register",
//                                    "/user/login")
//                .permitAll()
////                .antMatchers(HttpMethod.POST, "/**").permitAll() //debug 时, 不用对 Authorization 作验证
//                .anyRequest().authenticated();
//
//        //配置自己的验证过滤器
////        httpSecurity
////                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//
//
//        // disable page caching
//        httpSecurity.headers().cacheControl();
//
//    }
//
//}
