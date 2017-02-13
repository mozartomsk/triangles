package ru.mozar.triangle.impl;

import ru.mozar.triangle.api.Triangle;
import ru.mozar.triangle.api.TriangleTypingService;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author mozar
 * @since 1.0.0
 */
class TriangleTypingServiceImpl implements TriangleTypingService {

    @Override
    public TriangleType typeTriangle(Triangle triangle) {
        checkNotNull(triangle, "[triangle] required");

        throw new IllegalStateException("Not implemented yet!");
    }
}