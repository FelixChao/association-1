package top.lvjp.association.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import top.lvjp.association.shiro.cache.MyShiroCache;
import top.lvjp.association.shiro.filter.KickFilter;
import top.lvjp.association.shiro.realm.CustomRealm;
import top.lvjp.association.shiro.session.MySessionDAO;
import top.lvjp.association.shiro.session.MySessionManager;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("kick", kickFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String, String> filterChain = new LinkedHashMap<>();

        filterChain.put("/**", "anon");
        filterChain.put("/manage/**", "kick");
        filterChain.put("/manage/user/**", "roles[root]");
        filterChain.put("/manage/picture/head/**", "roles[root]");
        filterChain.put("/manage/association/form", "roles[root]");
        filterChain.put("/manage/association/update", "roles[root]");
        filterChain.put("/manage/rights/list", "roles[root]");
        filterChain.put("/manage/rights/delete", "roles[root]");
        filterChain.put("/manage/association/**", "roles[root,admin]");
        filterChain.put("/manage/rights/**", "roles[root,admin]");
//        所有删除操作需要管理员权限
        filterChain.put("/manage/**/delete", "roles[root,admin]");
        filterChain.put("/manage/**/clean", "roles[root,admin]");
        filterChain.put("/manage/**", "authc");
//        filterChain.put("/logout", "logout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChain);
        // 过滤链中抛出异常不能捕获, 添加未授权接口返回信息
        shiroFilterFactoryBean.setUnauthorizedUrl("/unautho");
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        return shiroFilterFactoryBean;
    }

    @Bean
    public Filter kickFilter(){
        KickFilter kickFilter = new KickFilter();
        return kickFilter;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());
        securityManager.setSessionManager(sessionManager());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean
    public CustomRealm customRealm(){
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher  = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");
        matcher.setHashIterations(1);
        matcher.setStoredCredentialsHexEncoded(true);
        return matcher;
    }

    @Bean
    public SessionManager sessionManager() {
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        MySessionManager sessionManager = new MySessionManager();
        sessionManager.setGlobalSessionTimeout(30*60*1000);
        sessionManager.setSessionValidationInterval(30*60*1000);
        sessionManager.setSessionDAO(sessionDAO());
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    @Bean
    public SessionDAO sessionDAO(){
        MySessionDAO sessionDAO = new MySessionDAO();
        MyShiroCache cache = new MyShiroCache(cacheManager());
        sessionDAO.setCache(cache);
        return sessionDAO;
    }

    @Bean
    public CacheManager cacheManager(){
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:shiro/ehcache.xml");
        return cacheManager;
    }

    @Bean
    public RememberMeManager rememberMeManager(){
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        byte[] cipherKey = Base64.decode("XTx6CKLo/SdSgub+OPHSrw==");
        rememberMeManager.setCipherKey(cipherKey);
        rememberMeManager.setCookie(simpleCookie());
        return rememberMeManager;
    }

    @Bean
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("shiro-rememberMe");
        simpleCookie.setMaxAge(7*24*60*60);
        return simpleCookie;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager());
        return sourceAdvisor;
    }

}
