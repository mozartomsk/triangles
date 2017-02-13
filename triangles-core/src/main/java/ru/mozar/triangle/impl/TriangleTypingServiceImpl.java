package ru.mozar.triangle.impl;

import org.springframework.stereotype.Service;
import ru.mozar.triangle.api.Triangle;
import ru.mozar.triangle.api.TriangleTypingService;

import static com.google.common.base.Preconditions.checkNotNull;
import static ru.mozar.triangle.api.TriangleTypingService.TriangleType.equilateral;
import static ru.mozar.triangle.api.TriangleTypingService.TriangleType.isosceles;
import static ru.mozar.triangle.api.TriangleTypingService.TriangleType.scalene;

/**
 * @author mozar
 * @since 1.0.0
 */
@Service
class TriangleTypingServiceImpl implements TriangleTypingService {

    @Override
    public TriangleType typeTriangle(Triangle triangle) {
        checkNotNull(triangle, "[triangle] required");

        if (triangle.getA() == triangle.getB() && triangle.getB() == triangle.getC()) {
            return equilateral;
        }
        if (triangle.getA() == triangle.getB() || triangle.getB() == triangle.getC() || triangle.getC() == triangle.getA()) {
            return isosceles;
        }
        return scalene;
    }
}