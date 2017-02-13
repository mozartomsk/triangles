package ru.mozar.triangle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import ru.mozar.triangle.api.Triangle;
import ru.mozar.triangle.api.TriangleService;
import ru.mozar.triangle.api.TriangleTypingService;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author mozar
 * @since 1.0.0
 *
 * Runs aplication, that gets three triangle side lengths from arguments. <br/>
 * Validate triangle params, and determine what type is it.
 */
@SpringBootConfiguration
@ComponentScan("ru.mozar.triangle.impl")
public class TrianglesCommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TrianglesCommandLineRunner.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return new CommandLineRunner() {

            @Autowired
            private TriangleService triangleService;

            @Autowired
            private TriangleTypingService triangleTypingService;

            @Override
            public void run(String... args) throws Exception {
                double a = readDouble(args, 0);
                double b = readDouble(args, 1);
                double c = readDouble(args, 2);

                Triangle triangle = triangleService.createAndValidate(a, b, c);
                System.out.println("Triangle: " + triangle);

                System.out.println("Type: " + triangleTypingService.typeTriangle(triangle));
            }
        };
    }

    private double readDouble(String[] args, int i) {
        if (args.length <= i) {
            throw new IllegalArgumentException(String.format("No value for %d triangle side!", i));
        }
        String valueString = args[i];
        if (isBlank(valueString)) {
            throw new IllegalArgumentException("Bad side length value");
        }
        try {
            return Double.valueOf(valueString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Bad side length value: " + e.getMessage(), e);
        }
    }
}