package cn.zyj;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableGlobalMethodSecurity(prePostEnabled = true)
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
@MapperScan("cn.zyj.mapper")
@EnableScheduling
@EnableSwagger2
public class YiMyblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(YiMyblogApplication.class, args);
    }
}
