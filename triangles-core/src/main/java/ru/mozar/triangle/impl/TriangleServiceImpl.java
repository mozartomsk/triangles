package ru.mozar.triangle.impl;

import com.google.common.base.Preconditions;
import javafx.util.Pair;
import org.springframework.stereotype.Service;
import ru.mozar.triangle.api.Triangle;
import ru.mozar.triangle.api.TriangleService;

import java.util.Arrays;
import java.util.List;

/**
 * @author mozar
 * @since 1.0.0
 */
@Service
class TriangleServiceImpl implements TriangleService {

    @Override
    public Triangle createAndValidate(double a, double b, double c) {
        Triangle result = new TriangleImpl(a, b, c);
        validate(result);
        return result;
    }

    @Override
    public void validate(Triangle triangle) {
        Preconditions.checkNotNull(triangle, "[triangle] required");
        Preconditions.checkArgument(triangle instanceof TriangleImpl, "[triangle] must be instance of TriangleImpl");

        List<Pair<String, Double>> sides = Arrays.asList(
                new Pair<>("A", triangle.getA()),
                new Pair<>("B", triangle.getB()),
                new Pair<>("C", triangle.getC())
        );
        sides.forEach(this::checkGtZero);

        checkSummLessThird(sides.get(0), sides.get(1), sides.get(2));
        checkSummLessThird(sides.get(1), sides.get(2), sides.get(0));
        checkSummLessThird(sides.get(2), sides.get(0), sides.get(1));
    }

    private void checkSummLessThird(Pair<String, Double> sideToCheck, Pair<String, Double> sideToSum1, Pair<String, Double> sideToSum2) {
        if (sideToCheck.getValue() > sideToSum1.getValue() + sideToSum2.getValue()) {
            throw new IllegalArgumentException("Side " + sideToCheck.getKey() + " is greater than sum of two others: " +
                    sideToCheck.getValue() + " > " + sideToSum1.getValue() + " + " + sideToSum2.getValue());
        }
    }

    private void checkGtZero(Pair<String, Double> side) {
        if (side.getValue() < 0d) {
            throw new IllegalArgumentException("Side " + side.getKey() + " can not be less than zero: " + side.getValue());
        }
    }
}
