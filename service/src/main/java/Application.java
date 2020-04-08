import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;


@EnableAutoConfiguration
@RestController
@MapperScan(basePackages = "com.steris.wechat.dao")
@ServletComponentScan
@ComponentScan(basePackages = {"com.steris.wechat.controller","com.steris.wechat.service","com.steris.wechat.config","com.baidu.ueditor"})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
