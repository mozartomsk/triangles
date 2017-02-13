package ru.mozar.triangle.api;

/**
 * @author mozar
 * @since 1.0.0
 *
 * Management service for triangles. Separated triangle implementation is required for control of instances generation.
 */
public interface TriangleService {

    /**
     * Generates and validate triangle by three sides of given lengths.
     *
     * @return generated triangle.
     */
    Triangle createAndValidate(double a, double b, double c) throws IllegalArgumentException;

    /**
     * Validate given triangle to check if it correct triangle.
     */
    void validate(Triangle triangle) throws IllegalArgumentException;
}