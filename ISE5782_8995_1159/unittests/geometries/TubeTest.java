package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Tube class
 * @author Erez Polak and Eliran Salama.
 */
class TubeTest {
    /**
     * Test method for {@link geometries.Tube#getNormal()}.
     */
    @Test
    void getNormal() {

        Point p = new Point(0,0,0);
        Vector v = new Vector(0,0,1);
        Ray r = new Ray(p,v);
        Tube t = new Tube(5,r);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual normal of the tube.

        Point testPoint01 = new Point(5,0,3);
        Vector expectedNormal = new Vector(1,0,0);
        assertEquals(t.getNormal(testPoint01), expectedNormal ,"ERROR: getNormal() wrong value");

        // =============== Boundary Values Tests ==================
        // TC02: Test when head of ray and point creat 90 degrees with the hinge

        Point testPoint11 = new Point(5,0,0);
        assertThrows(IllegalArgumentException.class, ()-> t.getNormal(testPoint11) , // check if throw exception
        "getNormal for the plane does not throw an exception");
    }

    /**
     * Test method for {@link geometries.Tube#getRaduis()}.
     */
    @Test
    void getRaduis() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual radius of the tube for the raduis value 5.
        Point p = new Point(1,2,3);
        Vector v = new Vector(2,3,4);
        Ray r = new Ray(p,v);
        Tube t = new Tube(5,r);
        assertEquals(t.getRaduis(),5,"ERROR: getRaduis() wrong value");
    }

    /**
     * Test method for {@link geometries.Tube#getRay()}.
     */
    @Test
    void getRay() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual radius of the tube for the raduis value 5.
        Point p = new Point(1,2,3);
        Vector v = new Vector(2,3,4);
        Ray r = new Ray(p,v);
        Tube t = new Tube(5,r);
        assertEquals(t.getRay(),r,"ERROR: getRaduis() wrong value");
    }

    /**
     * Test method for {@link geometries.Intersectable#findIntersections(primitives.Ray)} ()}.
     */
    @Test
    void testFindIntersections() {

    }
}