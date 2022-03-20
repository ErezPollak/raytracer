package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Plane class
 * @author Erez Polak and Eliran Salama.
 */

class PlaneTest {
    /**
     * Test method for {@link geometries.Plane#Plane(Point, Point, Point)}.
     */
    @Test
    void testConstructor(){
        // =============== Boundary Values Tests ==================

        // TC01: Test that when 2 points coming together if the vector normal is 0 and throw exception.
        Point point01 = new Point(1,2,0); // create 3 points for the plane
        Point point02 = new Point(1,2,0);
        Point point03 = new Point(7,8,3);
        assertThrows(IllegalArgumentException.class, ()-> new Plane(point01,point02,point03), // check if throw exception
                "Constructor for the plane does not throw an exception");

        // =============== Boundary Values Tests ==================
        // TC02: Test that the result is zero when the vectors are orthogonal.
        Point point11 = new Point(1,2,3); // create 3 points for the plane
        Point point12 = new Point(2,4,6);
        Point point13 = new Point(4,8,12);
        assertThrows(IllegalArgumentException.class, ()->  new Plane(point11,point12,point13) , // check if throw exception
        "Constructor for the plane does not throw an exception");

    }


    /**
     * Test method for {@link geometries.Plane#getQ0()}.
     */
    @Test
    void getQ0() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual point of the plane.
        Point point1 = new Point(1,2,3); // Create 3 points for the plane not on the same line
        Point point2 = new Point(4,5,6);
        Point point3 = new Point(7,8,10);
        Plane p = new Plane(point1,point2,point3);
        assertEquals(p.getQ0(),point1,"ERROR: getQ0() wrong value");
    }

    /**
     * Test method for {@link geometries.Plane#getNormal()}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual normal of the plane.
        Point point1 = new Point(1,2,0); // creat 3 points for the plane
        Point point2 = new Point(4,5,0);
        Point point3 = new Point(7,9,0);
        Plane p = new Plane(point1,point2,point3);

        Vector expectedNormal1 = new Vector(0,0,1);
        Vector expectedNormal2 = new Vector(0,0,-1);

        Vector actualNormal = p.getNormal(point1);

        assertTrue(((actualNormal.equals(expectedNormal1) ||
                actualNormal.equals(expectedNormal2)) &&
                actualNormal.length() == 1 ) ,"ERROR: getNormal() wrong value"); // check if we get the currect vector

    }

    /**
     * Test method for {@link geometries.Intersectable#findIntersections(primitives.Ray)} ()}.
     */
    @Test
    void testFindIntersections() {

    }
}