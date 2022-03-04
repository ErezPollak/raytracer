package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point)}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual normal of the sphere.
        Point p = new Point(0,0,0);
        Sphere s = new Sphere(1,p);
        Point testPoint = new Point(1,1,1);
        Vector expectedVector = new Vector(1,1,1);
        assertEquals(s.getNormal(testPoint), expectedVector.normalize(),"ERROR: getNormal() wrong value");
    }

    /**
     * Test method for {@link geometries.Sphere#getRaduis()}.
     */
    @Test
    void getRaduis() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual radius of the sphere for the raduis value 5.
        Point p = new Point(1,2,3);
        Sphere s = new Sphere(5,p);
        assertEquals(s.getRaduis(),5,"ERROR: getRaduis() wrong value");
    }

    /**
     * Test method for {@link geometries.Sphere#getPoint()}.
     */
    @Test
    void getPoint() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual radius of the sphere for the raduis value 5.
        Point p = new Point(1,2,3);
        Sphere s = new Sphere(5,p);
        assertEquals(s.getPoint(),p,"ERROR: getPoint() wrong value");
    }

}