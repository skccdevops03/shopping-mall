package com.cafe24.shoppingmall.config.test;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.config.app.DBConfig;
import com.cafe24.config.app.MyBatisConfig;

@Configurable
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.shoppingmall.service",
				"com.cafe24.shoppingmall.repository"})
@Import({DBConfig.class, MyBatisConfig.class})
public class AppConfig {

}
