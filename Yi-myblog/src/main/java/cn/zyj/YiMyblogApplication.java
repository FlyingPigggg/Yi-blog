package cn.zyj;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
@MapperScan("cn.zyj.mapper")
@EnableScheduling
public class YiMyblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(YiMyblogApplication.class, args);
    }
}
