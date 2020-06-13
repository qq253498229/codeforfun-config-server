package cn.codeforfun.config;

import org.springframework.context.annotation.Configuration;

@Configuration
@tk.mybatis.spring.annotation.MapperScan(basePackages = "cn.codeforfun")
public class MyBatisConfiguration {
}
