package geometries;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TorusTest {


    @Test
    void testGetNormal() {
        Torus donut = new Torus(Point.ZERO, 1, 0.5);
        assertTrue(donut.getNormal(new Point(0, 1, 1)).equals(new Vector(0, 0, 1)), "Error: not correct nermal");
        assertEquals(new Vector(0, 1, 0), donut.getNormal(new Point(0, 1.5, 0)), "Error: not correct nermal");
        assertEquals(new Vector(0, 0, -1), donut.getNormal(new Point(0, 1, -1)), "Error: not correct nermal");
        assertEquals(new Vector(0, 1, 1).normalize(), donut.getNormal(new Point(0, 2, 1)), "Error: not correct nermal");
        assertEquals(new Vector(0, 1, -1).normalize(), donut.getNormal(new Point(0, 2, -1)), "Error: not correct nermal");
        assertEquals(new Vector(0, 1, 0).normalize(), donut.getNormal(new Point(0, -0.5, 0)), "Error: not correct nermal");

    }

    @Test
    void testFindGeoIntersectionsHelper() {
        Torus donut = new Torus(Point.ZERO, 1, 0.5);
        assertEquals(null, donut.findGeoIntersectionsHelper(new Ray(new Point(0, 0, -1), new Vector(0, 0, 1))),"Error: not correct array");
        assertEquals(null, donut.findGeoIntersectionsHelper(new Ray(new Point(0, 0, -1), new Vector(0, 1, 10))), "Error: not correct array");
        assertEquals(null, donut.findGeoIntersectionsHelper(new Ray(new Point(10, 0, -1), new Vector(0, 0, 1))), "Error: not correct array");
        assertEquals(null, donut.findGeoIntersectionsHelper(new Ray(new Point(0, 2, 1), new Vector(1, 0, 0))), "Error: not correct array");
        assertEquals(null, donut.findGeoIntersectionsHelper(new Ray(new Point(1, 1, 1), new Vector(1, 1, 0))), "Error: not correct array");


        assertEquals(1, donut.findGeoIntersectionsHelper(new Ray(new Point(1, 0, 0), new Vector(1, 0, 0))).size(), "Error: not correct array");
        assertEquals(1, donut.findGeoIntersectionsHelper(new Ray(new Point(0.5, 0, -1), new Vector(0, 0, 1))).size(), "Error: not correct array");
        assertEquals(1, donut.findGeoIntersectionsHelper(new Ray(new Point(0, 1.5, -1), new Vector(0, 0, 1))).size(), "Error: not correct array");
        assertEquals(1, donut.findGeoIntersectionsHelper(new Ray(new Point(0, 0.5, -1), new Vector(0, 0, 1))).size(), "Error: not correct array");

        assertEquals(2, donut.findGeoIntersectionsHelper(new Ray(new Point(1, 2, 0), new Vector(0, -1, 0))).size(), "Error: not correct array");
        assertEquals(2, donut.findGeoIntersectionsHelper(new Ray(new Point(2, 2, 0.5), new Vector(-1, -1, 0))).size(), "Error: not correct array");
        assertEquals(2, donut.findGeoIntersectionsHelper(new Ray(Point.ZERO, new Vector(0, 1, 0))).size(), "Error: not correct array");
        assertEquals(2, donut.findGeoIntersectionsHelper(new Ray(new Point(0, 2, 0.5), new Vector(0, -1, 0))).size(), "Error: not correct array");


        assertEquals(3, donut.findGeoIntersectionsHelper(new Ray(new Point(0, -1, 0), new Vector(0, 1, 0))).size(), "Error: not correct array");

        assertEquals(4, donut.findGeoIntersectionsHelper(new Ray(new Point(0, 2, 0), new Vector(0, -1, 0))).size(), "Error: not correct array");

    }



    @Test
    void testjson() {

        Torus torus = new Torus(new JSONObject(
                "{\"radius\": 10, \"center\": {\"x\":1, \"y\":2, \"z\":3 }, \"width\":5}"
        ));
        assertEquals(10, torus.radius);
        assertEquals(3, torus.center.getZ());
        assertEquals(5, torus.width);
    }

}
