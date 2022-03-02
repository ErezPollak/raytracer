package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static primitives.Util.*;

/**
 * Unit tests for primitives.Vector class
 * @author Erez Polak and Eliran Salama.
 */
class VectorTest {

    /**
     * Test method for {@link primitives.Vector#add(Vector)}.
     */
    @Test
    void add() {
        Vector someVector = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual sum of the vectors.
        Vector anotherVector = new Vector(2, 3, 4);
        Vector vectorsSam = new Vector(3 , 5 , 7);
        assertEquals(someVector.add(anotherVector) , vectorsSam , "ERROR: add() wrong value");

        // =============== Boundary Values Tests ==================
        // TC11: Test that the program throws an exception for sum zero.
        Vector minusVector = new Vector(-1, -2, -3);
        assertThrows(IllegalArgumentException.class, () -> someVector.add(minusVector) ,
                "crossProduct() for vector and its minus vector vectors does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#subtract(Point)}.
     */
    @Test
    void subtruct() {

        Vector someVector = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual subtraction of the vectors.
        Vector anotherVector = new Vector(2, 3, 5);
        Vector vectorsSubtraction = new Vector(-1 , -1 , -2);
        assertEquals(someVector.subtruct(anotherVector) , vectorsSubtraction , "ERROR: add() wrong value");

        // =============== Boundary Values Tests ==================
        // TC11: Test that the program throws an exception for sum zero.
        Vector sameVector = new Vector(1, 2, 3);
        assertThrows(IllegalArgumentException.class, () -> someVector.subtruct(sameVector),
                "crossProduct() for identical vectors does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void scale() {

        Vector someVector = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual scaled vector for the value 5.
        Vector scaleBy5 = new Vector(5, 10, 15);
        assertEquals(someVector.scale(5) , scaleBy5 , "ERROR: scale() wrong value");

        // =============== Boundary Values Tests ==================
        // TC11: Test that the program throws an exception for scaling by 0.
        assertThrows(IllegalArgumentException.class, () -> someVector.scale(0) ,
                "crossProduct() for parallel vectors does not throw an exception");

    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}.
     */
    @Test
    void dotProduct() {

        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that the result is positive when the angel is obtuse
        Vector v11 = new Vector(1, 2, 0);
        double d11 = v1.dotProduct(v11);
        assertTrue(d11 > 0 , "dot product returns wrong value for sharp angles");

        // TC02: Test that the result is positive when the angel is sharp
        Vector v12 = new Vector(-1, -2, 0);
        double d12 = v1.dotProduct(v12);
        assertTrue(d12 < 0 , "dot product returns wrong value for obtuse angles");

        // =============== Boundary Values Tests ==================

        // TC02: Test that the result is zero when the vectors are orthogonal.
        Vector v21 = new Vector(0, -3, 2);
        double d21 = v1.dotProduct(v21);
        assertTrue(d21 == 0 , "dot product returns wrong value for orthogonal vectors");

    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    void crossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001 ,
                "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)) , "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)) , "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3) ,
                "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link Vector#getXyz()}.
     */
    @Test
    void getXyz() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the function returns the actual Double3 object that defines the vector.
        Vector someVector = new Vector(1, 2, 3);
        Double3 d3 = new Double3(1,2,3);
        assertEquals(someVector.getXyz() , d3 , "ERROR: getXYZ() wrong value");

        // =============== Boundary Values Tests ==================
        //no boundary tests because the length cannot be zero.
    }

    /**
     * Test method for {@link Vector#lengthSquared()}.
     */
    @Test
    void lengthSquared() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the squared length of the vector equals to the actual squared length of the vector.
        Vector someVector = new Vector(1, 2, 3);
        double length = 14.0;
        assertEquals(someVector.lengthSquared() , length , "ERROR: lengthSquared() wrong value");

        // =============== Boundary Values Tests ==================
        //no boundary tests because the length cannot be zero.
    }

    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    void length() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the length of the vector equals to the actual length of the vector.
        Vector someVector = new Vector(0, 3, 4);
        double length = 5.0;
        assertEquals( someVector.length() , length ,"ERROR: length() wrong value" );

        // =============== Boundary Values Tests ==================
        //no boundary tests because the length cannot be zero.
    }

    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    void normalize() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the normal vector equals to the actual normal vector.
        Vector someVector = new Vector(1, 1, 1);
        double sqrt3d1 = 1.0 / Math.sqrt(3);
        Vector normalizedVector = new Vector(sqrt3d1, sqrt3d1, sqrt3d1);
        assertEquals(someVector.normalize() , normalizedVector ,"ERROR: normalize() wrong value" );

        // =============== Boundary Values Tests ==================
        //no boundary tests because the length cannot be zero.
    }

}