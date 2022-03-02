package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual normal of the sphere.
        Sphere s = new Sphere(5);
        assertEquals(s.getNormal,?,"ERROR: getNormal() wrong value")
    }

    @Test
    void getRaduis() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual radius of the sphere for the raduis value 5.
        Sphere s = new Sphere(5);
        assertEquals(s.getRaduis(),5,"ERROR: getRaduis() wrong value")
    }

}