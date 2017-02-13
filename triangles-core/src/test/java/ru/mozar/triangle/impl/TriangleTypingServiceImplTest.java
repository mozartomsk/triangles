package ru.mozar.triangle.impl;

import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import ru.mozar.triangle.api.Triangle;
import ru.mozar.triangle.api.TriangleTypingService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.mozar.triangle.api.TriangleTypingService.TriangleType.equilateral;
import static ru.mozar.triangle.api.TriangleTypingService.TriangleType.isosceles;
import static ru.mozar.triangle.api.TriangleTypingService.TriangleType.scalene;

@RunWith(value = Parameterized.class)
@RequiredArgsConstructor
public class TriangleTypingServiceImplTest {

    TriangleTypingServiceImpl triangleTypingService = new TriangleTypingServiceImpl();

    @Parameters
    public static List<Invariant> data() {
        return Arrays.asList(
                new Invariant(2, 2, 2, equilateral, "Equilateral triangle"),
                new Invariant(2.00001, 2, 2, isosceles, "Isosceles triangle"),
                new Invariant(2.00001, 2, 1.99999, scalene, "Scalene triangle")
        );
    }

    private final Invariant invariant;

    @RequiredArgsConstructor
    private static class Invariant {
        final double a, b, c;
        final TriangleTypingService.TriangleType expectedType;
        final String description;
    }

    @Test
    public void test() {
        assertNotNull("[invariant] required", invariant);

        Triangle triangle = mock(Triangle.class);

        when(triangle.getA()).thenReturn(invariant.a);
        when(triangle.getB()).thenReturn(invariant.b);
        when(triangle.getC()).thenReturn(invariant.c);

        try {
            assertEquals(invariant.description, triangleTypingService.typeTriangle(triangle), invariant.expectedType);
        } catch (Throwable t) {
            fail(String.format("Invariant '%s' failed with exception: %s", invariant.description, t.getMessage()));
        }
    }
}