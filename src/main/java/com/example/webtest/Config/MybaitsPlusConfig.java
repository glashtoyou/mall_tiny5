package com.example.webtest.Config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.webtest.mapper")
public class MybaitsPlusConfig {
}
