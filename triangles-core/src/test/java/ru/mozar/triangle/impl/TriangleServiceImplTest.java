package ru.mozar.triangle.impl;

import junit.framework.TestCase;
import lombok.RequiredArgsConstructor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import ru.mozar.triangle.api.Triangle;

import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author mozar
 * @since 1.0.0
 */
@RunWith(value = Parameterized.class)
@RequiredArgsConstructor
public class TriangleServiceImplTest extends TestCase {

    private final Invariant invariant;

    private final TriangleServiceImpl triangleService = new TriangleServiceImpl();

    @Parameters
    public static List<Invariant> data() {
        return Arrays.asList(
                new Invariant(1, 1, 1, "usual triangle"),
                new Invariant(0, 1, 1, "flat triangle"),
                new Invariant(1, 0, 1, "flat triangle"),
                new Invariant(1, 1, 0, "flat triangle"),
                new Invariant(0, 0, 0, "point triangle"),

                new Invariant(3, 2, 1, "boarder case - flat triangle"),
                new Invariant(3, 1, 2, "boarder case - flat triangle"),
                new Invariant(2, 3, 1, "boarder case - flat triangle"),
                new Invariant(1, 3, 2, "boarder case - flat triangle"),
                new Invariant(2, 1, 3, "boarder case - flat triangle"),
                new Invariant(1, 2, 3, "boarder case - flat triangle"),

                new Invariant(3.00001, 1, 2, "A is greater then sum of others").setExceptionMessageSubstring("Side A is greater than sum of two others: 3.00001 > "),
                new Invariant(3.00001, 2, 1, "A is greater then sum of others").setExceptionMessageSubstring("Side A is greater than sum of two others: 3.00001 > "),
                new Invariant(2, 3.00001, 1, "B is greater then sum of others").setExceptionMessageSubstring("Side B is greater than sum of two others: 3.00001 > "),
                new Invariant(1, 3.00001, 2, "B is greater then sum of others").setExceptionMessageSubstring("Side B is greater than sum of two others: 3.00001 > "),
                new Invariant(2, 1, 3.00001, "C is greater then sum of others").setExceptionMessageSubstring("Side C is greater than sum of two others: 3.00001 > "),
                new Invariant(1, 2, 3.00001, "C is greater then sum of others").setExceptionMessageSubstring("Side C is greater than sum of two others: 3.00001 > "),

                new Invariant(-1, 1, 1, "A is opposite").setExceptionMessageSubstring("Side A can not be less than zero: -1.0"),
                new Invariant(1, -1, 1, "B is opposite").setExceptionMessageSubstring("Side B can not be less than zero: -1.0"),
                new Invariant(1, 1, -1, "C is opposite").setExceptionMessageSubstring("Side C can not be less than zero: -1.0")
            );
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @RequiredArgsConstructor
    private static class Invariant {
        final double a, b, c;
        final String description;

        /**
         * If null - no exception must be generated
         */
        String exceptionMessageSubstring = null;
        public Invariant setExceptionMessageSubstring(String exceptionMessageSubstring) {
            this.exceptionMessageSubstring = exceptionMessageSubstring;
            return this;
        }

        @Override
        public String toString() {
            return "(" + a + ", " + b + ", " + c + ") - " + description +
                    (isNotBlank(exceptionMessageSubstring) ? "/" + exceptionMessageSubstring : "");
        }
    }

    @Test
    public void test() {
        assertNotNull("[invariant] required", invariant);

        if (isBlank(invariant.exceptionMessageSubstring)) {
            try {
                Triangle triangle = triangleService.createAndValidate(invariant.a, invariant.b, invariant.c);

                assertEquals(buildMessage(invariant, "side A equals to given parameter"), invariant.a, triangle.getA());
                assertEquals(buildMessage(invariant, "side B equals to given parameter"), invariant.b, triangle.getB());
                assertEquals(buildMessage(invariant, "side C equals to given parameter"), invariant.c, triangle.getC());
            } catch (Throwable t) {
                fail(buildMessage(invariant, "must work successfully, but got error: " + t.getMessage()));
            }
        } else {
            try {
                triangleService.createAndValidate(invariant.a, invariant.b, invariant.c);
            } catch (Throwable t) {
                assertTrue(buildMessage(invariant, "message of exception equals to expected. Expected: '" +
                                invariant.exceptionMessageSubstring + "', but got: '" + t.getMessage() + "'"),
                        isNotBlank(t.getMessage()) && t.getMessage().startsWith(invariant.exceptionMessageSubstring));
                return;
            }
            fail(buildMessage(invariant, "must fail with exception: '" + invariant.exceptionMessageSubstring + "'"));
        }
    }

    private String buildMessage(Invariant invariant, String checkDescription) {
        return String.format("Invariant: '%s' failed! Failed check: '%s'",
                invariant.toString(), checkDescription);
    }
}