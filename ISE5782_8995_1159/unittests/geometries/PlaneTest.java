package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#getQ0()}.
     */
    @Test
    void getQ0() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual point of the plane.
        Point point = new Point(1,2,3);
        Vector vector = new Vector(3,4,5);
        Plane p = new Plane(point,vector);
        assertEquals(p.getQ0,point,"ERROR: getQ0() wrong value")

    }

    /**
     * Test method for {@link geometries.Plane#getNormal()}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual normal of the plane.
        Point point = new Point(1,2,3);
        Vector vector = new Vector(3,4,5)
        Plane p = new Plane(point,vector);
        assertEquals(p.getNormal(),vector.normalize,"ERROR: getNormal()() wrong value")
    }
}