package ru.mozar.triangle.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.mozar.triangle.api.Triangle;

/**
 * @author mozar
 * @since 1.0.0
 *
 * Implementation of Triangle. Separated implementation is required for control of instances generation.
 */
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class TriangleImpl implements Triangle {

    private double a,b,c;
}