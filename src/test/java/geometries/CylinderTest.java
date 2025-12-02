package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Cylinder class
 *
 * @author Erez Polak and Eliran Salama.
 */
class CylinderTest {


    Vector direction = new Vector(1, 0, 0);
    Ray ray = new Ray(new Point(0, 0, 0), direction);
    Cylinder cylinder1 = new Cylinder(ray, 1, 4);


    /**
     * Test method for {@link geometries.Cylinder#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {

        Point p = new Point(0, 0, 0);
        Vector v = new Vector(0, 0, 1);
        Ray r = new Ray(p, v);
        Cylinder c = new Cylinder(r, 5, 5);
        Vector expectedNormal1 = new Vector(0, 0, 1);
        Vector expectedNormal2 = new Vector(0, 0, -1);


        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that the function returns the actual normal of the cylinder when point on base.
        Point testPoint01 = new Point(-4, 0, 0);
        assertTrue(c.getNormal(testPoint01).equals(expectedNormal1) ||
                c.getNormal(testPoint01).equals(expectedNormal2), "ERROR: getNormal() wrong value"); // check if we get the currect vector


        // TC02: Test that the function returns the actual normal of the cylinder when point on the other base.
        Point testPoint02 = new Point(-4, 0, 5);
        Vector actualNormal = c.getNormal(testPoint02);
        assertTrue(actualNormal.equals(expectedNormal1) ||
                actualNormal.equals(expectedNormal2), "ERROR: getNormal() wrong value"); // check if we get the currect vector


        // TC03: Test that the function returns the actual normal of the cylinder when point on the side.
        Point testPoint3 = new Point(5, 0, 3);
        Vector expectedNormal3 = new Vector(1, 0, 0);
        assertEquals(c.getNormal(testPoint3), expectedNormal3, "ERROR: getNormal() wrong value");

        // =============== Boundary Values Tests ==================

        // TC10: Test when head of ray and point are the same ( the center of the first base)
        Point testPoint10 = new Point(0, 0, 0);
        assertThrows(IllegalArgumentException.class, () -> c.getNormal(testPoint10), // check if throw exception
                "getNormal for the plane does not throw an exception");


        // TC11: Test when head of ray and point are the same ( the center of the second base)
        Point testPoint11 = new Point(0, 0, 5);
        assertThrows(IllegalArgumentException.class, () -> c.getNormal(testPoint11), // check if throw exception
                "getNormal for the plane does not throw an exception");

        // TC12: Test when head of ray and point are the same ( the center of the second base)
        Point testPoint12 = new Point(0, 5, 5);
        assertThrows(IllegalArgumentException.class, () -> c.getNormal(testPoint12), // check if throw exception
                "getNormal for the plane does not throw an exception");

    }

    //////////////////////////////////////////////////


    Cylinder cylinder = new Cylinder(new Ray(new Point(2, 0, 0), new Vector(0, 0, 1)), 1d, 2d);
    List<Point> result = null;

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestEP1() {
        //TC01 ray is outside and parallel to the cylinder's ray

        result = cylinder.findIntersections(new Ray(new Point(5, 0, 0), new Vector(0, 0, 1)));
        assertNull(result, "Wrong number of points");
    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestEP2() {
        //TC02 ray starts inside and parallel to the cylinder's ray

        result = cylinder.findIntersections(new Ray(new Point(2.5, 0, 1), new Vector(0, 0, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(2.5, 0, 2)), result, "Bad intersection point");
    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestEP3() {
        //TC03 ray starts outside and parallel to the cylinder's ray and crosses the cylinder

        result = cylinder.findIntersections(new Ray(new Point(2.5, 0, -1), new Vector(0, 0, 1)));
        assertEquals(2, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(2.5, 0, 0), new Point(2.5, 0, 2)), result, "Bad intersection point");
    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestEP4() {
        //TC04 ray starts from outside and crosses the cylinder

        result = cylinder.findIntersections(new Ray(new Point(-2, 0, 0.5), new Vector(1, 0, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(1, 0, 0.5), new Point(3, 0, 0.5)), result, "Bad intersection points");
    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestEP5() {
        //TC05 ray starts from inside and crosses the cylinder

        result = cylinder.findIntersections(new Ray(new Point(1.5, 0, 0.5), new Vector(1, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(3, 0, 0.5)), result, "Bad intersection points");
    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestEP6() {
        //TC06 ray starts from outside the cylinder and doesn't cross the cylinder

        result = cylinder.findIntersections(new Ray(new Point(5, 0, 0), new Vector(1, 0, 0)));
        assertNull(result, "Wrong number of points");
    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestEP7() {
        //TC07 ray starts from outside and crosses base and surface

        result = cylinder.findIntersections(new Ray(new Point(1, 0, -1), new Vector(1, 0, 1)));
        assertEquals(2, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(2, 0, 0), new Point(3, 0, 1)), result, "Bad intersection points");
    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestEP8() {
        //TC08 ray starts from outside and crosses surface and base

        result = cylinder.findIntersections(new Ray(new Point(4, 0, 2), new Vector(-1, 0, -1)));
        assertEquals(2, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(2, 0, 0), new Point(3, 0, 1)), result, "Bad intersection points");

    }
    // =============== Boundary Values Tests ==================

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestBVA1() {
        //TC09 ray is on the surface of the cylinder (not bases)

        result = cylinder.findIntersections(new Ray(new Point(3, 0, 0), new Vector(0, 0, 1)));
        assertNull(result, "Wrong number of points");
    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestBVA2() {
        //TC10 ray is on the base of the cylinder and crosses 2 times

        result = cylinder.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 0, 0)));
        assertNull(result, "Wrong number of points");
    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestBVA3() {
        //TC11 ray is in center of the cylinder

        result = cylinder.findIntersections(new Ray(new Point(2, 0, 0), new Vector(0, 0, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(2, 0, 2)), result, "Bad intersection points");
    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestBVA4() {
        //TC12 ray is perpendicular to cylinder's ray and starts from outside the tube

        result = cylinder.findIntersections(new Ray(new Point(-2, 0, 0.5), new Vector(1, 0, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(1, 0, 0.5), new Point(3, 0, 0.5)), result, "Bad intersection points");
    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestBVA5() {
        //TC13 ray is perpendicular to cylinder's ray and starts from inside cylinder (not center)

        result = cylinder.findIntersections(new Ray(new Point(1.5, 0, 0.5), new Vector(1, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(3, 0, 0.5)), result, "Bad intersection points");
    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestBVA6() {
        //TC14 ray is perpendicular to cylinder's ray and starts from the center of cylinder

        result = cylinder.findIntersections(new Ray(new Point(2, 0, 0.5), new Vector(1, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(3, 0, 0.5)), result, "Bad intersection points");
    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestBVA7() {
        //TC15 ray is perpendicular to cylinder's ray and starts from the surface of cylinder to inside

        result = cylinder.findIntersections(new Ray(new Point(1, 0, 0.5), new Vector(1, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(3, 0, 0.5)), result, "Bad intersection points");
    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestBVA8() {
        //TC16 ray is perpendicular to cylinder's ray and starts from the surface of cylinder to outside

        result = cylinder.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0)));
        assertNull(result, "Wrong number of points");
    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestBVA9() {

        //TC17 ray starts from the surface to outside

        result = cylinder.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 1, 1)));
        assertNull(result, "Wrong number of points");

    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestBVA10() {
        //TC18 ray starts from the surface to inside

        result = cylinder.findIntersections(new Ray(new Point(3, 0, 0.5), new Vector(-1, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(1, 0, 0.5)), result, "Bad intersection point");

    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestBVA11() {
        //TC19 ray starts from the center

        result = cylinder.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(3, 0, 1)), result, "Bad intersection point");

    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestBVA12() {
        //TC20 prolongation of ray crosses cylinder

        result = cylinder.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0)));
        assertNull(result, "Wrong number of points");

    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestBVA13() {
        //TC21 ray is on the surface starts before cylinder

        result = cylinder.findIntersections(new Ray(new Point(3, 0, -1), new Vector(0, 0, 1)));
        assertNull(result, "Wrong number of points");

    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestBVA14() {

        //TC22 ray is on the surface starts at bottom's base

        result = cylinder.findIntersections(new Ray(new Point(3, 0, 0), new Vector(0, 0, 1)));
        assertNull(result, "Wrong number of points");

    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestBVA15() {
        //TC23 ray is on the surface starts on the surface

        result = cylinder.findIntersections(new Ray(new Point(3, 0, 1), new Vector(0, 0, 1)));
        assertNull(result, "Wrong number of points");
    }

    /**
     * Test method for {@link Cylinder#findIntersections(Ray)}.
     */
    @Test
    void findIntersectionsTestBVA16() {
        //TC24 ray is on the surface starts at top's base

        result = cylinder.findIntersections(new Ray(new Point(3, 0, 2), new Vector(0, 0, 1)));
        assertNull(result, "Wrong number of points");
    }


}


//
//
//
//package geometries;
//
//        import org.junit.jupiter.api.Test;
//        import primitives.Point;
//        import primitives.Ray;
//        import primitives.Vector;
//
//        import java.util.List;
//
//        import static org.junit.jupiter.api.Assertions.*;
//
///**
// * test for {@link Cylinder} class functionalities
// */
//class CylinderTest {
//
//    Vector direction= new Vector(1,0,0);
//    Ray ray=new Ray(new Point(0,0,0),direction);
//    Cylinder cylinder1 = new Cylinder(ray,1,4);
//
//    /**
//     * Test method for {@link geometries.Cylinder#getNormal(Point)}.
//     */
//    @Test
//    void testGetNormalEP1() {
//        assertEquals(new Vector(0,0,1), cylinder1.getNormal(new Point(3,0,1)),
//                "returned normal vector is incorrect");
//
//    }
//
//    /**
//     * Test method for {@link geometries.Cylinder#getNormal(Point)}.
//     */
//    @Test
//    void testGetNormalEP2() {
//        assertEquals(direction.normalize(), cylinder1.getNormal(new Point(0,0.5,0)),
//                "returned normal vector is incorrect");
//    }
//
//    /**
//     * Test method for {@link geometries.Cylinder#getNormal(Point)}.
//     */
//    @Test
//    void testGetNormalEP3() {
//        assertEquals(direction.normalize(), cylinder1.getNormal(new Point(4,0.5,0)),
//                "returned normal vector is incorrect");
//    }
//
//    /**
//     * Test method for {@link geometries.Cylinder#getNormal(Point)}.
//     */
//    @Test
//    void testGetNormalBVE1(){
//        assertEquals(direction.normalize(), cylinder1.getNormal(new Point(4,0,0)),
//                "returned normal vector is incorrect");
//    }
//
//    /**
//     * Test method for {@link geometries.Cylinder#getNormal(Point)}.
//     */
//    @Test
//    void testGetNormalBVE2(){
//        assertEquals(direction.normalize(), cylinder1.getNormal(new Point(0,0,0)),
//                "returned normal vector is incorrect");
//    }
//
//
//    // ============ Equivalence Partitions Tests ==============
//
//
//
//    Cylinder cylinder = new Cylinder(new Ray(new Point(2,0,0), new Vector(0,0,1)), 1d,2d);
//    List<Point> result=null;
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestEP1() {
//        //TC01 ray is outside and parallel to the cylinder's ray
//
//        result = cylinder.findIntersections(new Ray(new Point(5, 0, 0), new Vector(0, 0, 1)));
//        assertNull(result, "Wrong number of points");
//    }
//
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestEP2() {
//        //TC02 ray starts inside and parallel to the cylinder's ray
//
//        result = cylinder.findIntersections(new Ray(new Point(2.5, 0, 1), new Vector(0, 0, 1)));
//        assertEquals(1, result.size(), "Wrong number of points");
//        assertEquals(List.of(new Point(2.5, 0, 2)), result, "Bad intersection point");
//    }
//
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestEP3() {
//        //TC03 ray starts outside and parallel to the cylinder's ray and crosses the cylinder
//
//        result = cylinder.findIntersections(new Ray(new Point(2.5, 0, -1), new Vector(0, 0, 1)));
//        assertEquals(2, result.size(), "Wrong number of points");
//        assertEquals(List.of(new Point(2.5, 0, 0), new Point(2.5, 0, 2)), result, "Bad intersection point");
//    }
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestEP4() {
//        //TC04 ray starts from outside and crosses the cylinder
//
//        result = cylinder.findIntersections(new Ray(new Point(-2, 0, 0.5), new Vector(1, 0, 0)));
//        assertEquals(2, result.size(), "Wrong number of points");
//        assertEquals(List.of(new Point(1, 0, 0.5), new Point(3, 0, 0.5)), result, "Bad intersection points");
//    }
//
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestEP5() {
//        //TC05 ray starts from inside and crosses the cylinder
//
//        result = cylinder.findIntersections(new Ray(new Point(1.5, 0, 0.5), new Vector(1, 0, 0)));
//        assertEquals(1, result.size(), "Wrong number of points");
//        assertEquals(List.of(new Point(3, 0, 0.5)), result, "Bad intersection points");
//    }
//
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestEP6() {
//        //TC06 ray starts from outside the cylinder and doesn't cross the cylinder
//
//        result = cylinder.findIntersections(new Ray(new Point(5, 0, 0), new Vector(1, 0, 0)));
//        assertNull(result, "Wrong number of points");
//    }
//
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestEP7() {
//        //TC07 ray starts from outside and crosses base and surface
//
//        result = cylinder.findIntersections(new Ray(new Point(1, 0, -1), new Vector(1, 0, 1)));
//        assertEquals(2, result.size(), "Wrong number of points");
//        assertEquals(List.of(new Point(2, 0, 0), new Point(3, 0, 1)), result, "Bad intersection points");
//    }
//
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestEP8() {
//        //TC08 ray starts from outside and crosses surface and base
//
//        result = cylinder.findIntersections(new Ray(new Point(4, 0, 2), new Vector(-1, 0, -1)));
//        assertEquals(2, result.size(), "Wrong number of points");
//        assertEquals(List.of(new Point(2, 0, 0), new Point(3, 0, 1)), result, "Bad intersection points");
//
//    }
//    // =============== Boundary Values Tests ==================
//
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestBVA1() {
//        //TC09 ray is on the surface of the cylinder (not bases)
//
//        result = cylinder.findIntersections(new Ray(new Point(3, 0, 0), new Vector(0, 0, 1)));
//        assertNull(result, "Wrong number of points");
//    }
//
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestBVA2() {
//        //TC10 ray is on the base of the cylinder and crosses 2 times
//
//        result = cylinder.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 0, 0)));
//        assertNull(result, "Wrong number of points");
//    }
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestBVA3() {
//        //TC11 ray is in center of the cylinder
//
//        result = cylinder.findIntersections(new Ray(new Point(2, 0, 0), new Vector(0, 0, 1)));
//        assertEquals(1, result.size(), "Wrong number of points");
//        assertEquals(List.of(new Point(2, 0, 2)), result, "Bad intersection points");
//    }
//
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestBVA4() {
//        //TC12 ray is perpendicular to cylinder's ray and starts from outside the tube
//
//        result = cylinder.findIntersections(new Ray(new Point(-2, 0, 0.5), new Vector(1, 0, 0)));
//        assertEquals(2, result.size(), "Wrong number of points");
//        assertEquals(List.of(new Point(1, 0, 0.5), new Point(3, 0, 0.5)), result, "Bad intersection points");
//    }
//
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestBVA5() {
//        //TC13 ray is perpendicular to cylinder's ray and starts from inside cylinder (not center)
//
//        result = cylinder.findIntersections(new Ray(new Point(1.5, 0, 0.5), new Vector(1, 0, 0)));
//        assertEquals(1, result.size(), "Wrong number of points");
//        assertEquals(List.of(new Point(3, 0, 0.5)), result, "Bad intersection points");
//    }
//
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestBVA6() {
//        //TC14 ray is perpendicular to cylinder's ray and starts from the center of cylinder
//
//        result = cylinder.findIntersections(new Ray(new Point(2, 0, 0.5), new Vector(1, 0, 0)));
//        assertEquals(1, result.size(), "Wrong number of points");
//        assertEquals(List.of(new Point(3, 0, 0.5)), result, "Bad intersection points");
//    }
//
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestBVA7() {
//        //TC15 ray is perpendicular to cylinder's ray and starts from the surface of cylinder to inside
//
//        result = cylinder.findIntersections(new Ray(new Point(1, 0, 0.5), new Vector(1, 0, 0)));
//        assertEquals(1, result.size(), "Wrong number of points");
//        assertEquals(List.of(new Point(3, 0, 0.5)), result, "Bad intersection points");
//    }
//
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestBVA8() {
//        //TC16 ray is perpendicular to cylinder's ray and starts from the surface of cylinder to outside
//
//        result = cylinder.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0)));
//        assertNull(result, "Wrong number of points");
//    }
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestBVA9() {
//
//        //TC17 ray starts from the surface to outside
//
//        result = cylinder.findIntersections(new Ray(new Point(3,0,0), new Vector(1,1,1)));
//        assertNull(result, "Wrong number of points");
//
//    }
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestBVA10() {
//        //TC18 ray starts from the surface to inside
//
//        result = cylinder.findIntersections(new Ray(new Point(3,0,0.5), new Vector(-1,0,0)));
//        assertEquals(1, result.size(), "Wrong number of points");
//        assertEquals(List.of(new Point(1,0,0.5)), result, "Bad intersection point");
//
//    }
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestBVA11() {
//        //TC19 ray starts from the center
//
//        result = cylinder.findIntersections(new Ray(new Point(2,0,0), new Vector(1,0,1)));
//        assertEquals(1, result.size(), "Wrong number of points");
//        assertEquals(List.of(new Point(3,0,1)), result, "Bad intersection point");
//
//    }
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestBVA12() {
//        //TC20 prolongation of ray crosses cylinder
//
//        result = cylinder.findIntersections(new Ray(new Point(3,0,0), new Vector(1,0,0)));
//        assertNull(result, "Wrong number of points");
//
//    }
//
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestBVA13() {
//        //TC21 ray is on the surface starts before cylinder
//
//        result = cylinder.findIntersections(new Ray(new Point(3,0,-1), new Vector(0,0,1)));
//        assertNull(result, "Wrong number of points");
//
//    }
//
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestBVA14() {
//
//        //TC22 ray is on the surface starts at bottom's base
//
//        result = cylinder.findIntersections(new Ray(new Point(3,0,0), new Vector(0,0,1)));
//        assertNull(result, "Wrong number of points");
//
//    }
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestBVA15() {
//        //TC23 ray is on the surface starts on the surface
//
//        result = cylinder.findIntersections(new Ray(new Point(3,0,1), new Vector(0,0,1)));
//        assertNull(result, "Wrong number of points");
//    }
//    /**
//     * Test method for {@link Cylinder#findIntersections(Ray)}.
//     */
//    @Test
//    void findIntersectionsTestBVA16() {
//        //TC24 ray is on the surface starts at top's base
//
//        result = cylinder.findIntersections(new Ray(new Point(3,0,2), new Vector(0,0,1)));
//        assertNull(result, "Wrong number of points");
//    }
//
//    List<Intersectable.GeoPoint> res=null;
//    /**
//     * Test method for {@link Cylinder#findGeoIntersectionsHelper(Ray, double)}.
//     */
//    @Test
//    void findGeoIntersectionsHelperTest1(){
//        res=cylinder.findGeoIntersectionsHelper(new Ray(new Point(-1,0,1),new Vector(1,0,0)),1d);
//        assertNull(res,"wrong zero intersections");
//    }
//
//    /**
//     * Test method for {@link Cylinder#findGeoIntersectionsHelper(Ray, double)}.
//     */
//    @Test
//    void findGeoIntersectionsHelperTest2(){
//        res=cylinder.findGeoIntersectionsHelper(new Ray(new Point(-1,0,1),new Vector(1,0,0)),2.5d);
//        assertEquals(1,res.size(),"wrong one point intersections");
//    }
//
//    /**
//     * Test method for {@link Cylinder#findGeoIntersectionsHelper(Ray, double)}.
//     */
//    @Test
//    void findGeoIntersectionsHelperTest3(){
//        res=cylinder.findGeoIntersectionsHelper(new Ray(new Point(-1,0,1),new Vector(1,0,0)),5d);
//        assertEquals(2,res.size(),"wrong two point intersections");
//    }
//
//
//
//}