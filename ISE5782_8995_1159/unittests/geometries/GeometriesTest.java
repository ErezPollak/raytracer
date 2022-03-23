package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
/**
 * Unit tests for geometries.Geometries class
 * @author Erez Polak and Eliran Salama.
 */
class GeometriesTest {

    // Create 3 geometries collection (1 from each geometrie) to test intersection of ray with them.
    Geometries geo = new Geometries(
            new Sphere (2, new Point(1,0.5,1)),
            new Plane(
                    new Point(-2,0,0),
                    new Point(0,0,4),
                    new Point(0,-2,0)),
            new Triangle(
                    new Point(1,0,0),
                    new Point(0.1,0.5,2.5),
                    new Point(-2,0,0)));

    // empty collection for empty test.
    Geometries emptyGeo = null;

    // ray for calculations.
    Ray ray;

    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     */
    @Test
    void testIntersections()
    {
        // =============== Boundary Values Tests ==================

        // TC01: Empty collection - should return null (0 points)
        ray = new Ray(new Point(6,2,2), new Vector(0,3,1));
        assertNull(emptyGeo.findIntersections(ray),
                "TC01 failed: expected no intersection points.");

        // TC02: There are no intersections with the ray (0 points)
        ray = new Ray(new Point(6,2,2), new Vector(0,3,1));
        assertNull(geo.findIntersections(ray),
                "TC02 failed: expected no intersection points.");

        // TC03: Only one geometrie has intersection with the ray.
        ray = new Ray(new Point(0,-10,5), new Vector(0,10,-1));
        assertEquals(1,geo.findIntersections(ray).size(),
                "TC03 failed: expected 1 intersection points.");

        // TC04: Ray intersect all the geometries.
        ray = new Ray(new Point(0,-10,5), new Vector(0,10,-4));
        assertEquals(4,geo.findIntersections(ray).size(),
                "TC04 failed: expected 4 intersection points.");

        // ============ Equivalence Partitions Tests ==============

        // TC11:Ray intersect with some geometries.
        ray = new Ray(new Point(0,-10,5), new Vector(1,10.5,-4));
        assertEquals(3,geo.findIntersections(ray).size(),
                "TC04 failed: expected 3 intersection points.");

    }

}