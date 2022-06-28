package com.xwh.it;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.xwh.**"})
@MapperScan("com.xwh.**.mapper")
@Slf4j
@EnableWebMvc
@EnableSwagger2
public class DemoApplication {

    public static void main(String[] args) {
        System.out.println("(♥◠‿◠)ﾉﾞ   启动   ლ(´ڡ`ლ)");
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)");
    }

}
