package cn.sea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2    // 开启 Swagger2
public class SwaggerConfig {

    public static final Contact CONTACT = new Contact("大海", "http://8.129.218.31:88/image/", "3243016771@qq.com");

    // 配置 Swagger 的 Docket 的 bean 实例
    @Bean
    public Docket docket(Environment environment) {

        // 判断是否是测试环境   application-dev.properties 测试环境的配置文件
        Profiles dev = Profiles.of("dev", "test");
        boolean isDev = environment.acceptsProfiles(dev);


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("大海")
                // enable: 是否启动swagger，如果为false则不能在浏览器中访问swagger
                .enable(isDev)
                .select()
                // RequestHandlerSelectors：扫描接口的方式
                // basePackage(package)：指定要扫描的包
                // any(); 扫描全部  none(); 不扫描
                // withClassAnnotation(RestController.class)：扫描类上的注解，参数是一个注解的字节码文件
                // withMethodAnnotation(GetMapping.class)：扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("cn.sea.controller"))
                // paths: 过滤路径
                //.paths(PathSelectors.ant("/lu/**")) // 只扫描指定路径

                .build(); // build
    }

    @Bean
    public Docket docketB() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("BBB");
    }

    @Bean
    public Docket docketC() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("CCC");
    }

    // 配置swagger的信息
    private ApiInfo apiInfo() {
        return new ApiInfo("柒拾贰的SwaggerAPI文档",
                "放弃不难，但坚持一定很酷",
                "v1.0",
                "http://8.129.218.31:88/image/",
                 CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }

}
