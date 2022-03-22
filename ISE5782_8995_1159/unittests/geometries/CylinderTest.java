package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Cylinder class
 * @author Erez Polak and Eliran Salama.
 */
class CylinderTest {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {

        Point p = new Point(0,0,0);
        Vector v = new Vector(0,0,1);
        Ray r = new Ray(p,v);
        Cylinder c = new Cylinder(5,r,5);
        Vector expectedNormal1 = new Vector(0,0,1);
        Vector expectedNormal2 = new Vector(0,0,-1);


        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that the function returns the actual normal of the cylinder when point on base.
        Point testPoint01 = new Point(-4,0,0);
        assertTrue(c.getNormal(testPoint01).equals(expectedNormal1) ||
                c.getNormal(testPoint01).equals(expectedNormal2) ,"ERROR: getNormal() wrong value"); // check if we get the currect vector


        // TC02: Test that the function returns the actual normal of the cylinder when point on the other base.
        Point testPoint02 = new Point(-4,0,5);
        Vector actualNormal = c.getNormal(testPoint02);
        assertTrue(actualNormal.equals(expectedNormal1) ||
                actualNormal.equals(expectedNormal2) ,"ERROR: getNormal() wrong value"); // check if we get the currect vector


        // TC03: Test that the function returns the actual normal of the cylinder when point on the side.
        Point testPoint3 = new Point(5,0,3);
        Vector expectedNormal3 = new Vector(1,0,0);
        assertEquals(c.getNormal(testPoint3), expectedNormal3 ,"ERROR: getNormal() wrong value");

        // =============== Boundary Values Tests ==================

        // TC10: Test when head of ray and point are the same ( the center of the first base)
        Point testPoint10 = new Point(0,0,0);
        assertThrows(IllegalArgumentException.class, ()-> c.getNormal(testPoint10) , // check if throw exception
        "getNormal for the plane does not throw an exception");


        // TC11: Test when head of ray and point are the same ( the center of the second base)
        Point testPoint11 = new Point(0,0,5);
        assertThrows(IllegalArgumentException.class, ()-> c.getNormal(testPoint11) , // check if throw exception
        "getNormal for the plane does not throw an exception");

        // TC12: Test when head of ray and point are the same ( the center of the second base)
        Point testPoint12 = new Point(0,5,5);
        assertThrows(IllegalArgumentException.class, ()-> c.getNormal(testPoint12) , // check if throw exception
                "getNormal for the plane does not throw an exception");

    }


    /**
     * Test method for {@link geometries.Intersectable#findIntersections(primitives.Ray)} ()}.
     */
    @Test
    void testFindIntersections() {

    }

}