package ru.mozar.triangle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ru.mozar.triangle.impl", "ru.mozar.triangle.ws"})
public class TrianglesWebServiceRunner {

    public static void main(String[] args) {
        SpringApplication.run(TrianglesWebServiceRunner.class, args);
    }
}