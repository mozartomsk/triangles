package ru.mozar.triangle.api;

/**
 * @author mozar
 * @since 1.0.0
 *
 * Component perform typing of triangles
 */
public interface TriangleTypingService {

    enum TriangleType {
        equilateral, isosceles, scalene;
    }

    TriangleType typeTriangle(Triangle triangle);
}