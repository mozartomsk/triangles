package ru.mozar.triangle.api;

/**
 * @author mozar
 * @since 1.0.0
 *
 * Basic runtime exception for all triangle functional exceptions
 */

public class TriangleRuntimeException extends RuntimeException {
    public TriangleRuntimeException(String message) {
        super(message);
    }
}