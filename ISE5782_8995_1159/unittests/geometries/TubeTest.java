package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    /**
     * Test method for {@link geometries.Tube#getNormal()}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual normal of the tube.
        Point p = new Point(1,2,3);
        Vector v = new Vector(2,3,4);
        Ray r = new Ray(v,p);
        Tube t = new Tube(5,r);
        assertEquals(t.getNormal,?,"ERROR: getNormal() wrong value")
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
        Ray r = new Ray(v,p);
        Tube t = new Tube(5,r);
        assertEquals(s.getRaduis(),5,"ERROR: getRaduis() wrong value")
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
        Ray r = new Ray(v,p);
        Tube t = new Tube(5,r);
        assertEquals(s.getRay(),r,"ERROR: getRaduis() wrong value")
    }
}