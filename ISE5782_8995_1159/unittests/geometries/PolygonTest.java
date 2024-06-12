/**
 *
 */
package geometries;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Polygons
 *
 * @author Dan
 */
class PolygonTests {

    /**
     * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                "Constructed a polygon with wrong order of vertices");

        // TC03: Not in the same plane
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                "Constructed a polygon with vertices that are not in the same plane");

        // TC04: Concave quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0.5, 0.25, 0.5)), //
                "Constructed a concave polygon");

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0.5, 0.5)),
                "Constructed a polygon with vertix on a side");

        // TC11: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "Constructed a polygon with vertice on a side");

        // TC12: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                "Constructed a polygon with vertice on a side");

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0, 0, 1)), "Bad normal to trinagle");
    }

    /**
     * Test method for {@link geometries.Polygon#findIntersections(Ray)}
     */
    @Test
    void findIntersections() {

        // ============ Equivalence Partitions Tests ==============

        //TC01: ray in inside the polygon
        Vector v = new Vector(0, 0, -1);
        Polygon t1 = new Polygon(new Point(2, 0, 1), new Point(2, 2, 1), new Point(0, 2, 1), new Point(0, 0, 1));
        Ray r1 = new Ray(new Point(0.5, 0.5, 2), v);
        assertEquals(t1.findIntersections(r1), List.of(new Point(0.5, 0.5, 1)), "Not expected point");

        //TC02: ray is outside: against edge
        Ray r2 = new Ray(new Point(3, 3, 1), v);
        assertNull(t1.findIntersections(r2), "Intersects outside");

        //TC03: ray is outside: against vertex
        Ray r3 = new Ray(new Point(0.5, 0.5, 1), v);
        assertNull(t1.findIntersections(r3), "Intersects outside");

        // =============== Boundary Values Tests ==================

        //TC11: ray is On the Polygon edge
        Ray r4 = new Ray(new Point(2, 2, 1), v);
        assertNull(t1.findIntersections(r4), "Intersects on the edge");

        //TC12: ray is in vertex
        Ray r5 = new Ray(new Point(2, 1, 1), v);
        assertNull(t1.findIntersections(r5), "Intersects in vertex");

        //TC13: ray is on edge's continuation
        Ray r6 = new Ray(new Point(2, 3, 1), v);
        assertNull(t1.findIntersections(r6), "Intersects on edge continuation");

    }

    @Test
    void testJson() {
        Polygon polygon = new Polygon(new JSONObject(
                "{" +
                "   \"vertices\": " +
                "   [" +
                "      {" +
                "           \"x\":0," +
                "           \"y\":0," +
                "           \"z\":1" +
                "      }, " +
                "      {" +
                "           \"d\":{" +
                "               \"value\":0" +
                "            }" +
                "      }, " +
                "      {" +
                "           \"x\":1," +
                "           \"y\":1," +
                "           \"z\":0" +
                "      }, " +
                "      {" +
                "           \"d\":{" +
                "               \"value\":1" +
                "           }" +
                "      }" +
                "   ]" +
                "}"));
        assertEquals(4, polygon.vertices.size());
        assertEquals(0, polygon.vertices.get(0).getX());
        assertEquals(0, polygon.vertices.get(1).getX());
        assertEquals(1, polygon.vertices.get(2).getX());



        polygon = new Polygon(new JSONObject(
                "{" +
                        "   \"vertices\": " +
                        "   [" +
                        "      {" +
                        "           \"x\":6.7," +
                        "           \"y\":5," +
                        "           \"z\":4" +
                        "      }, " +
                        "      {" +
                        "           \"d\":{" +
                        "               \"value\":5" +
                        "            }" +
                        "      }, " +
                        "      {" +
                        "           \"d\":{" +
                        "               \"value\":4" +
                        "           }" +
                        "      }" +
                        "   ]" +
                        "}"));
        assertEquals(3, polygon.vertices.size());
        assertEquals(6.7, polygon.vertices.get(0).getX());
        assertEquals(5, polygon.vertices.get(1).getX());
        assertEquals(4, polygon.vertices.get(2).getX());
    }

}
