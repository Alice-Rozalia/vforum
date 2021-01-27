package org.vforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.vforum.utils.IdWorker;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: 白鸟亦悲否？
 * @Date: 2021/1/25 17:20
 */
@SpringBootApplication
@MapperScan("org.vforum.mapper")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }

}
