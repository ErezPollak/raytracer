package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual normal of the cylinder.
        Point p = new Point(1,0,0);
        Vector v = new Vector(0,0,1);
        Ray r = new Ray(v,p);
        Cylinder c = new Cylinder(5,r,5);
        Point testPiont = new Point(-4,0,0);
        Vector expectedNormal = new Vector(-1,0,0);
        assertEquals(c.getNormal(testPiont).normalize(), expectedNormal ,"ERROR: getNormal() wrong value");
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