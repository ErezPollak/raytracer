package primitives;


import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Point class
 * @author Erez Polak and Eliran Salama.
 */
class PointTest {

    /**
     * Test method for {@link primitives.Point#subtract(Point)}.
     */
    @Test
    void testSubtract() {
        Point p1 = new Point(1,2,3);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that the function returns the actual subtraction of the vectors.
        Point p2 = new Point(5,6,8);
        Vector subtractionVector = new Vector(-4 , -4 , -5);
        assertEquals(p1.subtract(p2) , subtractionVector , "the subtraction() function returns wrong value.");

        // =============== Boundary Values Tests ==================

        // TC11: Test that the program throws an exception for subtraction zero.
        assertThrows(IllegalArgumentException.class,() -> p1.subtract(p1) ,
                "do not throw error when subtracts the same point");
    }

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        Point p1 = new Point(1,2,3);
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that the function returns the actual subtraction of the vectors.
        Vector v = new Vector(5,6,8);
        Point addingPiont = new Point(6 , 8 , 11);
        assertEquals(p1.add(v) , addingPiont , "the add() function returns wrong value.");

        // =============== Boundary Values Tests ==================

        // TC11: Test that the program throws an exception for subtraction zero.
        assertThrows(IllegalArgumentException.class,() -> p1.subtract(p1) ,
                "do not throw error when subtracts the same point");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)} .
     */
    @Test
    void testDistanceSquared() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that the function returns the actual squared distance between the points.
        Point p1 = new Point(1,2,3);
        Point p2 = new Point(5,6,8);
        double distance = 16 + 16 + 25;
        assertEquals(p1.distanceSquared(p2) , distance , "the distanceSquared() function returns wrong value.");
    }

    /**
     * Test method for {@link primitives.Point#distance(Point)}.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that the function returns the actual squared distance between the points.
        Point p1 = new Point(1,2,3);
        Point p2 = new Point(5,6,8);
        double distance = Math.sqrt(16 + 16 + 25);
        assertEquals(p1.distance(p2) , distance , "the distanceSquared() function returns wrong value.");
    }


    @Test
    void testjson() throws Exception {

        Point point = new Point(new JSONObject("{\"x\": 4, \"y\": 5.5, \"z\": 6}"));
        assertEquals(4, point.getX(), "x");
        assertEquals(5.5, point.getY(), "y");
        assertEquals(6, point.getZ(), "z");


        point = new Point(new JSONObject("{\"d\": {\"value\": 4.7}}"));
        assertEquals(4.7, point.getX(), "d1");
        assertEquals(4.7, point.getY(), "d2");
        assertEquals(4.7, point.getZ(), "d3");


        point = new Point(new JSONObject("{\"d\": {\"d1\": 4, \"d2\": 5.5, \"d3\": 6}}"));
        assertEquals(4, point.getX(), "d1");
        assertEquals(5.5, point.getY(), "d2");
        assertEquals(6, point.getZ(), "d3");

    }

}