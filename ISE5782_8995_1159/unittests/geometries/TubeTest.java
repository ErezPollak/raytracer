package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual normal of the tube.
        Tube t = new Tube(5);
        assertEquals(t.getNormal,?,"ERROR: getNormal() wrong value")
    }

    @Test
    void getRaduis() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual radius of the tube for the raduis value 5.
        Tube t = new Tube(5);
        assertEquals(t.getRaduis(),5,"ERROR: getRaduis() wrong value")
    }
}