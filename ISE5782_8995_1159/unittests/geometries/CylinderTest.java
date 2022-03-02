package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    /**
     * Test method for {@link geometries.Cylinder#getNormal()}.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual normal of the cylinder.
        Cylinder c = new Cylinder(5);
        assertEquals(c.getNormal,?,"ERROR: getNormal() wrong value")
    }

    /**
     * Test method for {@link geometries.Cylinder#getHigh()}.
     */
    @Test
    void getHigh() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual high of the cylinder for the high value 5.
        Cylinder c = new Cylinder(5);
        assertEquals(c.getHigh(),5,"ERROR: getHigh() wrong value")
    }

}