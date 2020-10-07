package com.example.demo.Shiro.Config;


import com.example.demo.Shiro.Realms.EmployeeRealm;
import com.example.demo.Shiro.Session.SessionManager;
import com.example.demo.Shiro.Untils.JwtFilter;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    EmployeeRealm employeeRealm;

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("SecurityManager") DefaultWebSecurityManager SecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(SecurityManager);
        //自定义过滤器
//        Map<String, Filter> myfilterMap = new LinkedHashMap<>();
//        myfilterMap.put("jwt", new JwtFilter());
        //拦截页面配置
        Map<String,String> filterMap = new LinkedHashMap<>();
        //匿名访问
      //  filterMap.put("/employee-user/login","anon");
        //认证之后才可访问
      //  filterMap.put("/**","");
        //未登录或无权限时跳转
        shiroFilterFactoryBean.setLoginUrl("http://localhost:8081/#/error");
        shiroFilterFactoryBean.setUnauthorizedUrl("http://localhost:8081/#/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    @Bean(name = "SecurityManager")
    public DefaultWebSecurityManager getSecurityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(employeeRealm);
        //自定义会话管理器
        defaultWebSecurityManager.setSessionManager(sessionManager());
        //自定义redis缓存管理器
        defaultWebSecurityManager.setCacheManager(redisCacheManager());
        return defaultWebSecurityManager;
    }

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;

    /**
     * redis控制器
     * @return
     */
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        return redisManager;
    }
    //sessionDAO
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }
    //会话管理器
    public DefaultWebSessionManager sessionManager(){
        SessionManager sessionManager = new SessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }
    //缓存管理器
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

}
