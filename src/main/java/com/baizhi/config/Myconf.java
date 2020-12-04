package com.baizhi.config;


import com.baizhi.realm.Myrealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class Myconf {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactory(DefaultWebSecurityManager defaultWebSecurityManager) {
        //创建shiro过滤器工厂
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //将defaultWebSecurityManager交由shiro过滤器工厂管理
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //制定拦截规则
        HashMap<String, String> map = new HashMap<>();
        map.put("/**", "authc");
        map.put("/user/login", "anon");
        //配置退出过滤器  具体的退出代码Shiro已经实现
        map.put("/logout", "logout");
        //记住我管理器
        map.put("/**", "user");
        map.put("/main/main.jsp", "anon");
        //设置拦截集合
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //指定被拦截后返回的页面路径
        shiroFilterFactoryBean.setLoginUrl("/user/login.jsp");
        return shiroFilterFactoryBean;
    }

    @Bean
    //创建web的SecurityManager
    public DefaultWebSecurityManager getDefaultSecurityManager(Myrealm myrealm, DefaultWebSessionManager defaultWebSessionManager, CookieRememberMeManager cookieRememberMeManager, EhCacheManager ehCacheManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        //将自定义Realm交给安全管理器
        defaultWebSecurityManager.setRealm(myrealm);
        //将session管理器交给安全管理器
        defaultWebSecurityManager.setSessionManager(defaultWebSessionManager);
        //将记住我管理器交给安全管理器

        defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager);
        //将缓存管理器交给安全管理器
        defaultWebSecurityManager.setCacheManager(ehCacheManager);
        return defaultWebSecurityManager;
    }

    @Bean
    public Myrealm getMyrealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        Myrealm myrealm = new Myrealm();
        myrealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myrealm;
    }

    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        //创建凭证对象
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //加密属性设置
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        //散列次数哦
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager() {
        //创建Session管理器对象
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        //设置session过期时间  参数:long类型的过期时间单位时毫秒
        defaultWebSessionManager.setGlobalSessionTimeout((1 * 1000) * 60 * 2);
        return defaultWebSessionManager;
    }

    @Bean
    //将记住我管理器对象交给Spring工厂管理
    public CookieRememberMeManager getRememberMeManager(SimpleCookie simpleCookie) {
        //创建记住我管理器
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        //将Cookie对象交给记住我管理器
        cookieRememberMeManager.setCookie(simpleCookie);
        return cookieRememberMeManager;
    }

    @Bean
    //将Cookie对象交给Spring工厂管理
    public SimpleCookie getCookie() {
        //创建Cookie对象
        SimpleCookie simpleCookie = new SimpleCookie();
        //设置记住我的名字  登陆页面 checkbox 中name的名字
        simpleCookie.setName("rememberme");

        //设置过期时间  单位秒
        simpleCookie.setMaxAge(1 * 60 * 5);
        return simpleCookie;
    }

    //创建缓存管理器交给Spring工厂管理
    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        return ehCacheManager;
    }
}
