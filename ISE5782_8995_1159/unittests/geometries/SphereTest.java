//package geometries;
//
//import org.junit.jupiter.api.Test;
//import primitives.Point;
//import primitives.Vector;
//import primitives.Ray;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
///**
// * Unit tests for geometries.Sphere class
// * @author Erez Polak and Eliran Salama.
// */
//class SphereTest {
//
//    /**
//     * Test method for {@link geometries.Sphere#getNormal(Point)}.
//     */
//    @Test
//    void testGetNormal() {
//        // ============ Equivalence Partitions Tests ==============
//        // TC01: Test that the function returns the actual normal of the sphere.
//        Point p = new Point(0,0,0);
//        Sphere s = new Sphere(1,p);
//        Point testPoint = new Point(1,1,1);
//        Vector expectedVector = new Vector(1,1,1);
//        assertEquals(s.getNormal(testPoint), expectedVector.normalize(),"ERROR: getNormal() wrong value");
//    }
//
//    /**
//     * Test method for {@link geometries.Sphere#getRaduis()}.
//     */
//    @Test
//    void testGetRaduis() {
//        // ============ Equivalence Partitions Tests ==============
//        // TC01: Test that the function returns the actual radius of the sphere for the raduis value 5.
//        Point p = new Point(1,2,3);
//        Sphere s = new Sphere(5,p);
//        assertEquals(s.getRaduis(),5,"ERROR: getRaduis() wrong value");
//    }
//
//    /**
//     * Test method for {@link geometries.Sphere#getPoint()}.
//     */
//    @Test
//    void testGetPoint() {
//        // ============ Equivalence Partitions Tests ==============
//        // TC01: Test that the function returns the actual radius of the sphere for the raduis value 5.
//        Point p = new Point(1,2,3);
//        Sphere s = new Sphere(5,p);
//        assertEquals(s.getPoint(),p,"ERROR: getPoint() wrong value");
//    }
//
//    /**
//     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
//     */
//    @Test
//    public void testFindIntersections() {
//
//        Sphere sphere = new Sphere(1d, new Point (1, 0, 0));
//
//        // ============ Equivalence Partitions Tests ==============
//
//        // TC01: Ray's line is outside the sphere (0 points)
//        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
//                "Ray's line out of sphere");
//
//        // TC02: Ray starts before and crosses the sphere (2 points)
//        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
//        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
//        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
//                new Vector(3, 1, 0)));
//        assertEquals(2, result.size(), "Wrong number of points");
//        if (result.get(0).getX() > result.get(1).getX())
//            result = List.of(result.get(1), result.get(0));
//        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");
//
//        // TC03: Ray starts inside the sphere (1 point)
//        Point p1 = new Point(2,0,0);
//        List<Point> result = sphere.findIntersections(new(Ray(new Point(0.5,0,0),
//                new Vector(1.5,0,0))));
//        assertEquals(1,result.size(),"Wrong number of points");
//        assertEquals(List.of(p1),result,"Ray crosses sphere");
//
//        // TC04: Ray starts after the sphere (0 points)
//        assertNull(sphere.findIntersections(new Ray(new Point (3,0,0), new Vector(1,0,0))),
//                "Ray's line out of sphere");
//
//        // =============== Boundary Values Tests ==================
//
//        // **** Group: Ray's line crosses the sphere (but not the center)
//        // TC11: Ray starts at sphere and goes inside (1 points)
//        Point p1 = new Point(2,0,0);
//        List<Point> result = sphere.findIntersections(new(Ray(new Point(2,0,0),
//                new Vector (-2,0,1.18))));
//        assertEquals(1,result.size(),"Wrong number of points");
//        assertEquals(List.of(p1),result,"Ray crosses sphere");
//
//        // TC12: Ray starts at sphere and goes outside (0 points)
//        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1.28, 1.48, 0))),
//                "Ray's line out of sphere");
//
//        // **** Group: Ray's line goes through the center
//        // TC13: Ray starts before the sphere (2 points)
//        Point p1 = new Point(1,-1,0);
//        Point p2 = new Point(1,1,0);
//        List<Point> result = sphere.findIntersections(new(Ray(new Point(1,-2,0),
//                new Vector (0,6,0))));
//        assertEquals(2,result.size(),"Wrong number of points");
//        assertEquals(List.of(p1,p2),result,"Ray crosses sphere");
//
//        // TC14: Ray starts at sphere and goes inside (1 points)
//        Point p1 = new Point(1,1,0);
//        List<Point> result = sphere.findIntersections(new(Ray(new Point(1,-1,0),
//                new Vector (0,6,0))));
//        assertEquals(1,result.size(),"Wrong number of points");
//        assertEquals(List.of(p1),result,"Ray crosses sphere");
//
//        // TC15: Ray starts inside (1 points)
//        Point p1 = new Point(1,1,0);
//        List<Point> result = sphere.findIntersections(new(Ray(new Point(1,-0.5,0),
//                new Vector (0,6,0))));
//        assertEquals(1,result.size(),"Wrong number of points");
//        assertEquals(List.of(p1),result,"Ray crosses sphere");
//
//        // TC16: Ray starts at the center (1 points)
//        Point p1 = new Point(1,1,0);
//        List<Point> result = sphere.findIntersections(new(Ray(new Point(1,0,0),
//                new Vector (0,6,0))));
//        assertEquals(1,result.size(),"Wrong number of points");
//        assertEquals(List.of(p1),result,"Ray crosses sphere");
//
//        // TC17: Ray starts at sphere and goes outside (0 points)
//        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0))),
//                "Ray's line out of sphere");
//        // TC18: Ray starts after sphere (0 points)
//        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0))),
//                "Ray's line out of sphere");
//
//        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
//        // TC19: Ray starts before the tangent point
//        assertNull(sphere.findIntersections(new Ray(new Point(2, 1, 1), new Vector(-1, -1, 0))),
//                "Ray's line tangent to sphere");
//
//        // TC20: Ray starts at the tangent point
//        assertNull(sphere.findIntersections(new Ray(new Point(1, 0, 1), new Vector(1, 1, 0))),
//                "Ray's line tangent to sphere");
//        // TC21: Ray starts after the tangent point
//        assertNull(sphere.findIntersections(new Ray(new Point(2, 1, 1), new Vector(4, 4, 0))),
//                "Ray's line tangent to sphere");
//
//        // **** Group: Special cases
//        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
//        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(0, 0, 1))),
//                "Ray's line orthogonal to ray start to sphere's center line");
//
//
//    }
//
//
//
//}