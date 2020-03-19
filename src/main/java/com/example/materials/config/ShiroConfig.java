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
		//领导
		filerMap.put("/api/user/save", "perms[user:admin]"); // 添加用户
		filerMap.put("/api/user/delete", "perms[user:admin]"); //删除用户
		filerMap.put("/api/user/findByPage", "perms[user:admin]"); //用户列表
		filerMap.put("/api/project/save", "perms[user:admin]"); //新建项目
		filerMap.put("/api/project/update", "perms[user:admin]"); //更新项目内容

		//上传文档、图片
		filerMap.put("/api/word/uploadWord", "perms[user:upload]"); //word相关权限
		filerMap.put("/api/word/save", "perms[user:upload]"); //word相关权限
		filerMap.put("/api/word/update", "perms[user:upload]"); //word相关权限
		filerMap.put("/api/word/delete", "perms[user:upload]"); //word相关权限
		filerMap.put("/api/picture/uploadImage", "perms[user:upload]"); //上传图片相关权限
		filerMap.put("/api/picture/save", "perms[user:upload]"); //上传图片相关权限
		filerMap.put("/api/picture/update", "perms[user:upload]"); //上传图片相关权限
		filerMap.put("/api/picture/delete", "perms[user:upload]"); //上传图片相关权限
		//合同
		filerMap.put("/api/contract/save", "perms[user:contract]"); //合同
		filerMap.put("/api/contract/update", "perms[user:contract]"); //合同
		//金额、支付
		filerMap.put("/api/money/save", "perms[user:money]"); //金额
		filerMap.put("/api/money/update", "perms[user:money]"); //金额
		filerMap.put("/api/detail/save", "perms[user:money]"); //支付详情
		filerMap.put("/api/detail/update", "perms[user:money]"); //支付详情
		//物料
		filerMap.put("/api/materiel/save", "perms[user:materiel]"); //物料
		filerMap.put("/api/materiel/update", "perms[user:materiel]"); //物料
		filerMap.put("/api/materiel/delete", "perms[user:materiel]"); //物料
		filerMap.put("/api/materiel/updateStatus", "perms[user:materiel]"); //物料
		//普通身份
		//filerMap.put("", "perms[user:common]");

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
