package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Triangle class
 *
 * @author Erez Polak and Eliran Salama.
 */
class TriangleTest {


//    /**
//     * Test method for {@link geometries.Triangle#Triangle(Point, Point, Point)}
//     */
//    @Test
//    void Constructor(){
//
//    }

    /**
     * Test method for {@link geometries.Intersectable#findIntersections(primitives.Ray)} ()}.
     */
    @Test
    void testFindIntersections() {

        Triangle triangle = new Triangle(new Point(0, 0, 1), new Point(1, 0, 1), new Point(0, 1, 1));
        Point point = new Point(0, 1, 0);
        Vector vector;

        // ============ Equivalence Partitions Tests ==============

        //TC01: the point is inside the triangle.
        vector = new Vector(0.25, -0.75, 1);
        assertEquals(triangle.findIntersections(new Ray(point, vector)), List.of(new Point(0.25,0.25,1)),
                "TC01 failed: expected one intersection point.");

        //TC02: the point is outside the triangle but in front of an edge.
        vector = new Vector(1, 0, 1);
        assertNull(triangle.findIntersections(new Ray(point, vector)),
                "TC02 failed: expected no intersection points.");

        //TC03: the point is outside the triangle but not in front of an edge.
        vector = new Vector(-1, -2, 1);
        assertNull(triangle.findIntersections(new Ray(point, vector)),
                "TC03 failed: expected no intersection points.");


        // =============== Boundary Values Tests ==================

        //TC04: the point is on edge.
        vector = new Vector(0.5, -0.5, 1);
        assertEquals(triangle.findIntersections(new Ray(point, vector)), List.of(new Point(0.5,0.5,1)),
                "TC04 failed: expected one intersection point, only the point (0.5 , 0.5 , 1).");

        //TC05: the point is on a vertex.
        vector = new Vector(0, -1, 1);
        assertEquals(triangle.findIntersections(new Ray(point, vector)), List.of(new Point(0,0,1)),
                "TC05 failed: expected no intersection points,  only the point (0,0,1).");

        //TC06: the point is on the continuation of an edge.
        vector = new Vector(0, 1, 1);
        assertNull(triangle.findIntersections(new Ray(point, vector)),
                "TC06 failed: expected no intersection points.");

    }

}