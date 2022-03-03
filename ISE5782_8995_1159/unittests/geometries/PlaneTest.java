package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    /**
     * Test method for {@link geometries.Plane#Plane()}.
     */
    @Test
    void testConstructor(){
        // =============== Boundary Values Tests ==================
        // TC01: Test that when 2 points coming together if the vector normal is 0 and throw exception.
        Point point1 = new Point(1,2,0); // create 3 points for the plane
        Point point2 = new Point(1,2,0);
        Point point3 = new Point(7,8,3);
        Plane p = new Plane(point1,point2,point3);
        assertThrows(IllegalArgumentException.class, ()-> Plane p = new Plane(point1,point2,point3); , // check if throw exception
                "Constructor for the plane does not throw an exception");

        // =============== Boundary Values Tests ==================
        // TC01: Test that the result is zero when the vectors are orthogonal.
        Point point1 = new Point(1,2,3); // create 3 points for the plane
        Point point2 = new Point(2,4,6);
        Point point3 = new Point(4,8,12);
        assertThrows(IllegalArgumentException.class, ()-> Plane p = new Plane(point1,point2,point3); , // check if throw exception
        "Constructor for the plane does not throw an exception");

    }


    /**
     * Test method for {@link geometries.Plane#getQ0()}.
     */
    @Test
    void getQ0() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual point of the plane.
        Point point1 = new Point(1,2,3); // Create 3 points for the plane
        Point point2 = new Point(4,5,6);
        Point point3 = new Point(7,8,9);
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
        Point point3 = new Point(7,8,0);
        Plane p = new Plane(point1,point2,point3);

        Vector expectedNormal1 = new Vector(0,0,1);
        Vector expectedNormal2 = new Vector(0,0,-1);

        assertTrue(((p.getNormal(point1).normalize() == expectedNormal1 ||
                p.getNormal(point1).normalize() == expectedNormal2) &&
                p.getNormal(point1).length() == 1 ) ,"ERROR: getNormal()() wrong value"); // check if we get the currect vector

}