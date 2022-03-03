package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual normal of the cylinder when point on base.
        Point p = new Point(0,0,0);
        Vector v = new Vector(0,0,1);
        Ray r = new Ray(v,p);
        Cylinder c = new Cylinder(5,r,5);
        Point testPoint = new Point(-4,0,0);
        Vector expectedNormal = new Vector(0,0,1);
        Vector expectedNormal = new Vector(0,0,-1);
        assertTrue((c.getNormal(testPoint).normalize() == expectedNormal1 ||
                c.getNormal(testPoint).normalize() == expectedNormal2) ,"ERROR: getNormal()() wrong value"); // check if we get the currect vector

        // ============ Equivalence Partitions Tests ==============
        // TC02: Test that the function returns the actual normal of the cylinder when point on the other base.
        Point p = new Point(0,0,0); // creat cylinder - we need point, vector, and ray.
        Vector v = new Vector(0,0,1);
        Ray r = new Ray(v,p);
        Cylinder c = new Cylinder(5,r,5);
        Point testPoint = new Point(-4,0,5);
        Vector expectedNormal1 = new Vector(0,0,1);
        Vector expectedNormal2 = new Vector(0,0,-1);
        assertTrue((c.getNormal(testPoint).normalize() == expectedNormal1 ||
                c.getNormal(testPoint).normalize() == expectedNormal2) ,"ERROR: getNormal()() wrong value"); // check if we get the currect vector

        // ============ Equivalence Partitions Tests ==============
        // TC03: Test that the function returns the actual normal of the cylinder when point on the side.
        Point p = new Point(0,0,0); // creat cylinder - we need point, vector, and ray.
        Vector v = new Vector(0,0,1);
        Ray r = new Ray(v,p);
        Cylinder c = new Cylinder(5,r,5);
        Point testPoint = new Point(5,0,3);
        Vector expectedNormal1 = new Vector(); // להשלים
        assertEquals(c.getNormal(testPiont).normalize(), expectedNormal ,"ERROR: getNormal() wrong value");

        // =============== Boundary Values Tests ==================
        // TC04: Test when head of ray and point are the same ( the center of the first base)
        Point p = new Point(0,0,0); // creat cylinder - we need point, vector, and ray.
        Vector v = new Vector(0,0,1);
        Ray r = new Ray(v,p);
        Cylinder c = new Cylinder(5,r,5);
        Point testPoint = new Point(0,0,0);
        assertThrows(IllegalArgumentException.class, ()-> c.getNormal(testPoint); , // check if throw exception
        "getNormal for the plane does not throw an exception");

        // =============== Boundary Values Tests ==================
        // TC05: Test when head of ray and point are the same ( the center of the second base)
        Point p = new Point(0,0,0); // creat cylinder - we need point, vector, and ray.
        Vector v = new Vector(0,0,1);
        Ray r = new Ray(v,p);
        Cylinder c = new Cylinder(5,r,5);
        Point testPoint = new Point(0,0,5);
        assertThrows(IllegalArgumentException.class, ()-> c.getNormal(testPoint); , // check if throw exception
        "getNormal for the plane does not throw an exception");
    }

    /**
     * Test method for {@link geometries.Cylinder#getHigh()}.
     */
    @Test
    void getHigh() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual high of the cylinder for the high value 5.
        Point p = new Point(1,0,0);
        Vector v = new Vector(0,0,1);
        Ray r = new Ray(v,p);
        Cylinder c = new Cylinder(5,r,5);
        assertEquals(c.getHigh(),5,"ERROR: getHigh() wrong value");
    }

}