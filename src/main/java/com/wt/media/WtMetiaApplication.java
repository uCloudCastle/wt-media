package com.wt.media;

import com.winston.ssm.config.DruidConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@MapperScan("com.wt.media.mapper")
@Import(DruidConfig.class)
public class WtMetiaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WtMetiaApplication.class, args);
    }

}
