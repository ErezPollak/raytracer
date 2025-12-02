package primitives;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for primitives.Point class
 *
 * @author Erez Polak and Eliran Salama.
 */
class PointTest {

    /**
     * Test method for {@link primitives.Point#subtract(Point)}.
     */
    @Test
    void testSubtract() {
        Point p1 = new Point(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that the function returns the actual subtraction of the vectors.
        Point p2 = new Point(5, 6, 8);
        Vector subtractionVector = new Vector(-4, -4, -5);
        assertEquals(p1.subtract(p2), subtractionVector, "the subtraction() function returns wrong value.");

        // =============== Boundary Values Tests ==================

        // TC11: Test that the program throws an exception for subtraction zero.
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1),
                "do not throw error when subtracts the same point");
    }

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        Point p1 = new Point(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that the function returns the actual subtraction of the vectors.
        Vector v = new Vector(5, 6, 8);
        Point addingPiont = new Point(6, 8, 11);
        assertEquals(p1.add(v), addingPiont, "the add() function returns wrong value.");

        // =============== Boundary Values Tests ==================

        // TC11: Test that the program throws an exception for subtraction zero.
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1),
                "do not throw error when subtracts the same point");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)} .
     */
    @Test
    void testDistanceSquared() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that the function returns the actual squared distance between the points.
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(5, 6, 8);
        double distance = 16 + 16 + 25;
        assertEquals(p1.distanceSquared(p2), distance, "the distanceSquared() function returns wrong value.");
    }

    /**
     * Test method for {@link primitives.Point#distance(Point)}.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that the function returns the actual squared distance between the points.
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(5, 6, 8);
        double distance = Math.sqrt(16 + 16 + 25);
        assertEquals(p1.distance(p2), distance, "the distanceSquared() function returns wrong value.");
    }

//    /**
//     * Test method for {@link Point#getXyz()}.
//     */
//    @Test
//    void getXyz() {
//        // ============ Equivalence Partitions Tests ==============
//
//        // TC01: Test that the function returns the xyz value of the point.
//        Point p1 = new Point(1,2,3);
//        Double3 d = new Double3(1,2,3);
//        assertEquals(p1.getXyz() , d , "the getXYZ() function returns wrong value.");
//
//    }

}