package com.cafe24.shoppingmall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

//@Configuration
//@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	 
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();

		// 자원 서버 접근 권한 설정
		http 
			.authorizeRequests()  
			.anyRequest().access("#oauth2.hasScope('read') or #oauth2.hasScope('write')");
//		.anyRequest().access("#oauth2.hasScope('read,write')");
		  
		http.csrf().disable();  
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("jemall_api");
	}
	
}
