package ru.mozar.triangle.api;

/**
 * @author mozar
 * @since 1.0.0
 *
 * Component perform typing of triangles
 */
public interface TriangleTypingService {

    /**
     * Type of Triangle <br/>
     * If triangle is equilateral, this is isosceles as well. In this case service returns <b>equilateral</b>.
     */
    enum TriangleType {
        equilateral, isosceles, scalene;
    }

    /**
     * Determine type of given triangle.
     */
    TriangleType typeTriangle(Triangle triangle);
}