package geometries;


import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import primitives.Double3;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GeometryTest {
    @Test
    void testjson() {
        Sphere sphere = new Sphere(new JSONObject("{\"center\": {\"d\" : {\"value\": 6}}, \"radius\": 5, \"geometry\": {\"emission\":{\"rgb\": {\"value\": 4}},\"material\" :{\"kD\": {\"d1\": 4, \"d2\": 5.5, \"d3\": 6},\"kS\":5, \"nShininess\": 5}}}"));
        assertEquals(4, sphere.getEmission().getColor().getBlue());
        assertEquals(sphere.getMaterial().kD, new Double3(4, 5.5, 6));
        assertEquals(5, sphere.getMaterial().nShininess);

        sphere = new Sphere(new JSONObject("{\"center\": {\"x\":1, \"y\":2, \"z\":3 }, \"radius\": 4, \"geometry\": {\"emission\": {\"rgb\": {\"value\": 10, }}}}"));
        assertEquals(10, sphere.getEmission().getColor().getBlue());
        assertEquals(sphere.getMaterial().kD, new Double3(0));
    }

}
