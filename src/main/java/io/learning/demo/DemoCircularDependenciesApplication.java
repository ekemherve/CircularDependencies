package io.learning.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DemoCircularDependenciesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoCircularDependenciesApplication.class, args);
    }

}
