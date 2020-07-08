package com.wt.media;

import com.winston.ssm.config.DruidConfig;
import lombok.Data;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.bind.WebDataBinder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@MapperScan("com.wt.media.mapper")
@Import(DruidConfig.class)
public class WtMetiaApplication {

    public static void main(String[] args) {
//        test();
        SpringApplication.run(WtMetiaApplication.class, args);
    }

    @Data
    static class Person {

        public String name;
        public Integer age;

        // 基本数据类型
        public Boolean flag;
        public int index;
        public List<String> list;
        public Map<String, String> map;

    }

    public static void test(){
        Person person = new Person();
        WebDataBinder binder = new WebDataBinder(person, "person");

        // 设置属性（此处演示一下默认值）
        MutablePropertyValues pvs = new MutablePropertyValues();

        // 使用!来模拟各个字段手动指定默认值
        //pvs.add("name", "fsx");
        pvs.add("!name", "不知火舞");
        pvs.add("age", 18);
        pvs.add("!age", 10); // 上面有确切的值了，默认值不会再生效

        binder.bind(pvs);
        System.out.println(person);
    }

}
