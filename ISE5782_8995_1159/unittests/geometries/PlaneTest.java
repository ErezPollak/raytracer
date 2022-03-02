package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#getQ0()}.
     */
    @Test
    void getQ0() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual point of the plane.
        Point point1 = new Point(1,2,3);
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
        Point point1 = new Point(1,2,0);
        Point point2 = new Point(4,5,0);
        Point point3 = new Point(7,8,0);
        Plane p = new Plane(point1,point2,point3);

        Vector expectedNormal = new Vector(0,0,1);

        assertEquals(p.getNormal(point1).normalize(), expectedNormal ,"ERROR: getNormal()() wrong value");
    }
}