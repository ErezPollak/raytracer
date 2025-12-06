package renderer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import geometries.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;
import com.flipkart.zjsonpatch.JsonDiff;


import static java.awt.Color.*;
import static java.awt.Color.WHITE;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Camera Class
 *
 * @author Dan
 */
class CameraTest {
    static final Point ZERO_POINT = new Point(0, 0, 0);

    /**
     * Test method for
     * {@link renderer.Camera#constructRay(int, int, int, int)}.
     */
    @Test
    void testConstructRay() {
        Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, -1, 0)).setVPDistance(10);
        String badRay = "Bad ray";

        // ============ Equivalence Partitions Tests ==============
        // EP01: 4X4 Inside (1,1)
        assertEquals(new Ray(ZERO_POINT, new Vector(1, -1, -10)),
                camera.setVPSize(8, 8).constructRay(4, 4, 1, 1), badRay);

        // =============== Boundary Values Tests ==================
        // BV01: 3X3 Center (1,1)
        assertEquals(new Ray(ZERO_POINT, new Vector(0, 0, -10)),
                camera.setVPSize(6, 6).constructRay(3, 3, 1, 1), badRay);

        // BV02: 3X3 Center of Upper Side (0,1)
        assertEquals(new Ray(ZERO_POINT, new Vector(0, -2, -10)),
                camera.setVPSize(6, 6).constructRay(3, 3, 1, 0), badRay);

        // BV03: 3X3 Center of Left Side (1,0)
        assertEquals(new Ray(ZERO_POINT, new Vector(2, 0, -10)),
                camera.setVPSize(6, 6).constructRay(3, 3, 0, 1), badRay);

        // BV04: 3X3 Corner (0,0)
        assertEquals(new Ray(ZERO_POINT, new Vector(2, -2, -10)),
                camera.setVPSize(6, 6).constructRay(3, 3, 0, 0), badRay);

        // BV05: 4X4 Corner (0,0)
        assertEquals(new Ray(ZERO_POINT, new Vector(3, -3, -10)),
                camera.setVPSize(8, 8).constructRay(4, 4, 0, 0), badRay);

        // BV06: 4X4 Side (0,1)
        assertEquals(new Ray(ZERO_POINT, new Vector(1, -3, -10)),
                camera.setVPSize(8, 8).constructRay(4, 4, 1, 0), badRay);

    }

    /**
     * Test method for
     * {@link renderer.Camera#cameraRoll(double)}.
     */
    @Test
    void testCameraRoll() {

        Point location = new Point(0, 0, 0);
        Vector up = new Vector(0, 0, 1);
        Vector to = new Vector(1, 0, 0);
        Camera camera = new Camera(location, to, up);

        // ============ Equivalence Partitions Tests ==============
        //angel is 45 degrees.
        assertTrue(camera.cameraRoll(45).sameCameraVectorsAndLocation(new Camera(location, new Vector(1, 1, 0), up)),
                "TC01 failed, camera rolled by 45 degrees.");

        //angel is 135 degrees.
        camera = new Camera(location, to, up);
        assertTrue(camera.cameraRoll(135).sameCameraVectorsAndLocation(new Camera(location, new Vector(-1, 1, 0), up)),
                "TC02 failed, camera rolled by 135 degrees.");

        //angel is 225 degrees.
        camera = new Camera(location, to, up);
        assertTrue(camera.cameraRoll(225).sameCameraVectorsAndLocation(new Camera(location, new Vector(-1, -1, 0), up)),
                "TC03 failed, camera rolled by 225 degrees.");

        //angel is 315 degrees.
        camera = new Camera(location, to, up);
        assertTrue(camera.cameraRoll(315).sameCameraVectorsAndLocation(new Camera(location, new Vector(1, -1, 0), up)),
                "TC04 failed, camera rolled by 315 degrees.");

        // =============== Boundary Values Tests ==================
        //angel is zero.
        camera = new Camera(location, to, up);
        assertTrue(camera.cameraRoll(0).sameCameraVectorsAndLocation(camera),
                "TC05 failed, camera rolled by 0 degrees.");

        //angel is 90
        camera = new Camera(location, to, up);
        assertTrue(camera.cameraRoll(90).sameCameraVectorsAndLocation(new Camera(location, new Vector(0, 1, 0), up)),
                "TC06 failed, camera rolled by 90 degrees.");

        //angel is 90
        camera = new Camera(location, to, up);
        assertTrue(camera.cameraRoll(180).sameCameraVectorsAndLocation(new Camera(location, new Vector(-1, 0, 0), up)),
                "TC07 failed, camera rolled by 180 degrees.");

        //angle is 270.
        camera = new Camera(location, to, up);
        assertTrue(camera.cameraRoll(270).sameCameraVectorsAndLocation(new Camera(location, new Vector(0, -1, 0), up)),
                "TC08 failed, camera rolled by 270 degrees.");

        //angle is 360.
        camera = new Camera(location, to, up);
        assertTrue(camera.cameraRoll(360).sameCameraVectorsAndLocation(camera),
                "TC01 failed, camera rolled by 360 degrees.");
    }

    /**
     * Test method for
     * {@link renderer.Camera#cameraTransform(double)}.
     */
    @Test
    void testCameraTransform() {
        Point location = new Point(0, 0, 0);
        Vector up = new Vector(1, 0, 0);
        Vector to = new Vector(0, 0, 1);
        Camera camera = new Camera(location, to, up);

        // ============ Equivalence Partitions Tests ==============
        //angel is 45 degrees.
        assertTrue(camera.cameraTransform(45).sameCameraVectorsAndLocation(new Camera(location, to, new Vector(1, 1, 0))),
                "TC01 failed, camera rolled by 45 degrees.");

        //angel is 135 degrees.
        camera = new Camera(location, to, up);
        assertTrue(camera.cameraTransform(135).sameCameraVectorsAndLocation(new Camera(location, to, new Vector(-1, 1, 0))),
                "TC02 failed, camera rolled by 135 degrees.");

        //angel is 225 degrees.
        camera = new Camera(location, to, up);
        assertTrue(camera.cameraTransform(225).sameCameraVectorsAndLocation(new Camera(location, to, new Vector(-1, -1, 0))),
                "TC03 failed, camera rolled by 225 degrees.");

        //angel is 315 degrees.
        camera = new Camera(location, to, up);
        assertTrue(camera.cameraTransform(315).sameCameraVectorsAndLocation(new Camera(location, to, new Vector(1, -1, 0))),
                "TC04 failed, camera rolled by 315 degrees.");

        // =============== Boundary Values Tests ==================
        //angel is zero.
        camera = new Camera(location, to, up);
        assertTrue(camera.cameraTransform(0).sameCameraVectorsAndLocation(camera),
                "TC05 failed, camera rolled by 0 degrees.");

        //angel is 90
        camera = new Camera(location, to, up);
        assertTrue(camera.cameraTransform(90).sameCameraVectorsAndLocation(new Camera(location, to, new Vector(0, 1, 0))),
                "TC06 failed, camera rolled by 90 degrees.");

        //angel is 90
        camera = new Camera(location, to, up);
        assertTrue(camera.cameraTransform(180).sameCameraVectorsAndLocation(new Camera(location, to, new Vector(-1, 0, 0))),
                "TC07 failed, camera rolled by 180 degrees.");

        //angle is 270.
        camera = new Camera(location, to, up);
        assertTrue(camera.cameraTransform(270).sameCameraVectorsAndLocation(new Camera(location, to, new Vector(0, -1, 0))),
                "TC08 failed, camera rolled by 270 degrees.");

        //angle is 360.
        camera = new Camera(location, to, up);
        assertTrue(camera.cameraTransform(360).sameCameraVectorsAndLocation(camera),
                "TC01 failed, camera rolled by 360 degrees.");
    }

    @Test
    void testCameraMove() {
        Point location = new Point(0, 0, 0);
        Vector up = new Vector(0, 0, 1);
        Vector to = new Vector(1, 0, 0);
        Camera camera = new Camera(location, to, up);

        // ============ Equivalence Partitions Tests ==============
        //TC01 the classic case.
        Camera movedCamera = new Camera(new Point(0, 0, 0), new Point(1, -1, 0), new Vector(0, 1, 0));
        Camera expectedCamera = new Camera(new Point(0, 0, 0), new Vector(1, -1, 0), new Vector(1, 1, 0));

        assertTrue(movedCamera.sameCameraVectorsAndLocation(expectedCamera), "camera is not in the expected place.");

        // =============== Boundary Values Tests ==================
        //TC02 the new line is on the y.
        movedCamera = new Camera(new Point(0, 1, 0), new Point(0, -1, 0), new Vector(0, 1, 0));
        expectedCamera = new Camera(new Point(0, 1, 0), new Vector(0, -1, 0), new Vector(1, 0, 0));

        assertTrue(movedCamera.sameCameraVectorsAndLocation(expectedCamera), "camera is not in the expected place.");


        //TC03: assert throwing an exception when the points are teh same;
        assertThrows(IllegalArgumentException.class, () -> new Camera(new Point(1, 1, 1), new Point(1, 1, 1), new Vector(0, 1, 0)),
                "if the points are the same needs to throw an exception.");

    }

    @Test
    void TestCameraSerialization(){

        Double3 xAxis = new Double3(300, 0, 0);
        Double3 yAxis = new Double3(0, 300, 0);
        Double3 zAxis = new Double3(0, 0, 300);


        Scene scene = new Scene("all features scene")
                .setAmbientLight(new AmbientLight(new Color(BLACK), new Double3(0.15)));

        scene.geometries.add(
                new Tube(1, new Ray(Point.ZERO, new Vector(xAxis))).setEmission(new Color(RED)),
                new Tube(1, new Ray(Point.ZERO, new Vector(yAxis))).setEmission(new Color(GREEN)),
                new Tube(1, new Ray(Point.ZERO, new Vector(zAxis))).setEmission(new Color(BLUE)),
                new Sphere(new Point(xAxis), 10).setEmission(new Color(RED)),
                new Sphere(new Point(yAxis), 10).setEmission(new Color(GREEN)),
                new Sphere(new Point(zAxis), 10).setEmission(new Color(BLUE)),
                new Plane(new Point(0,0,-700) , new Vector(-1,-1,2)).setEmission(new Color(YELLOW)),
                new Polygon(new Point(100, -100, -300), new Point(100, 100, -300), new Point(-100, 100, -300), new Point(-100, -100, -300)).setEmission(new Color(ORANGE)),
                new Triangle(new Point(0, 200, 100), new Point(100, 0, 100), new Point(0, 100, 100)).setEmission(new Color(GREEN))
                //   new Cylinder(new Ray(Point.ZERO, new Vector(0, 0, -1)), 100d, 200d).setEmission(new Color(YELLOW))
        );

        scene.lights.add(new DirectionalLight(new Color(WHITE).reduce(2), new Vector(-1, 1, 1)));
        scene.lights.add(new PointLight(new Color(800, 500, 0), new Point(-50, -50, 25)).setKl(0.001).setKq(0.0002));//.setRadius(4));
        scene.lights.add(new SpotLight(new Color(WHITE), new Point(0, 0, 100), new Vector(0, 0, -1)).setNarrowBeam(NarrowBeamArchitecture.DAN_ARCHITECTURE, 10).setKl(0.00001).setKq(0.00002));//.setRadius(5));


        ImageWriter imageWriter = new ImageWriter("SerializationImage", 1000, 1000);
        Camera camera = new Camera(new Point(1000, 1000, 700), Point.ZERO, new Vector(0, 0, 1)) //
                .setVPSize(150, 150) //
                .setVPDistance(250)
                .cameraRoll(-5)
                .cameraTransform(0)

                .setApertureSize(10)
                .setFPDistance(1000)

                .setImageWriter(imageWriter) //
                .setAlias(true)
                .setThreadsCount(4)
                .setPrintInterval(0.1)
                .setRayTracer(new RayTracerBasic(scene));


        //camera.renderImage().writeToImage();
        ObjectMapper mapperBefore = new ObjectMapper();
        ObjectMapper mapperAfter = new ObjectMapper();
        try {
            String jsonBefore, jsonAfter = null;
            jsonBefore = mapperBefore.writerWithDefaultPrettyPrinter().writeValueAsString(camera);

            //System.out.println(json);

            Camera cameraFromJson = mapperBefore.readValue(jsonBefore, Camera.class);

            jsonAfter = mapperAfter.writerWithDefaultPrettyPrinter().writeValueAsString(cameraFromJson);

            JsonNode diff = JsonDiff.asJson(mapperBefore.readTree(jsonBefore), mapperAfter.readTree(jsonAfter));
            System.out.println(jsonBefore);
            System.out.println(jsonAfter);
            assertTrue(diff.isArray());
            assertTrue(diff.isEmpty());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
