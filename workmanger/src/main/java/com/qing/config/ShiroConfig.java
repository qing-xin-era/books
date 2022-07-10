package com.qing.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig  {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean  bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);
        /*
         UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User user = userService.queryUserByName(userToken.getUsername());
        if(user==null){
            return  null;  //返回null时，抛出异常 UnknownAccountException
        }
        return new SimpleAuthenticationInfo("",user.getPwd(),"");
        anon: 无需认证
        authc: 必须认证
        user:  必须拥有记住我
        perms：拥有对某个资源的权限才能访问
        role：拥有某个角色的权限才能访问
         */
        Map<String, String> filterMap =new LinkedHashMap<>();
        /*
         授权，如果重复，后面不能覆盖
         filterMap.put("/user/*","anon");

        1.修改LogoutFilter的重定向地址
LogoutFilter logout = new LogoutFilter();
logout.setRedirectUrl(Constants.PREFIX +"/login");

2.修改ShiroFilterFactoryBean的filters
Map<String, Filter> filters=new HashMap<>();
filters.put(“logout”,logout);

3.修改ShiroFilterFactoryBean的filterChainDefinitionMap
Map<String, String> filterMap = new LinkedHashMap<>();
filterMap.put(Constants.PREFIX +"/logout",“logout”);





         */
        filterMap.put("/user/add","authc");
        filterMap.put("/user/update","authc");


        filterMap.put("/logout","logout");


        bean.setFilterChainDefinitionMap(filterMap);
        bean.setLoginUrl("/toLogin");
        bean.setUnauthorizedUrl("/unathor");

        return  bean;
    }

    @Bean(name = "securityManager")
        DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
       DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();

        securityManager.setRealm(userRealm);


        return  securityManager;
    }

    @Bean
    public  UserRealm userRealm(){
        return  new UserRealm();

    }
    @Bean
    public ShiroDialect getShiroDialect(){
        return  new ShiroDialect();
    }
}
