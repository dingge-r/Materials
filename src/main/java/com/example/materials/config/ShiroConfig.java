package com.example.materials.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		Map<String, String> filerMap = new LinkedHashMap<String, String>();
		//开放的接口
		filerMap.put("/api/user/login", "anon");

		//需要权限的接口
		filerMap.put("/api/user/save", "perms[user:admin]"); // 添加用户的权限
		filerMap.put("/api/user/delete", "perms[user:admin]"); //删除用户

		filerMap.put("api/token/role/*", "perms[role:perm]");

		//返回404，请先登录
		shiroFilterFactoryBean.setLoginUrl("/PleaseLoginFirst");

		//返回404，权限不足
		shiroFilterFactoryBean.setUnauthorizedUrl("/AccessDeniedWithoutPermission");


		shiroFilterFactoryBean.setFilterChainDefinitionMap(filerMap);
		return shiroFilterFactoryBean;
	}

	@Bean(name = {"securityManager"})
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

		securityManager.setRealm(userRealm);
		//securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	@Bean(name = {"userRealm"})
	public UserRealm getRealm() { return new UserRealm(); }


}