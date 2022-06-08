package renderer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import renderer.Camera;
import primitives.*;

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


    /**
     * Test method for
     * {@link renderer.Camera#cameraMove(Point, Point,Vector)}.
     */
    @Test
    void testCameraMove() {
        Point location = new Point(0, 0, 0);
        Vector up = new Vector(0, 0, 1);
        Vector to = new Vector(1, 0, 0);
        Camera camera = new Camera(location, to, up);

        // ============ Equivalence Partitions Tests ==============
        //TC01 the classic case.
        Camera movedCamera = camera.cameraMove(new Point(0, 0, 0), new Point(1, -1, 0), new Vector(0,1,0));
        Camera expectedCamera = new Camera(new Point(0, 0, 0), new Vector(1, -1, 0), new Vector(1, 1, 0));

        assertTrue(movedCamera.sameCameraVectorsAndLocation(expectedCamera), "camera is not in the expected place.");

        // =============== Boundary Values Tests ==================
        //TC02 the new line is on the y.
        movedCamera = camera.cameraMove(new Point(0, 1, 0), new Point(0, -1, 0), new Vector(0,1,0));
        expectedCamera = new Camera(new Point(0, 1, 0), new Vector(0, -1, 0), new Vector(1, 0, 0));

        assertTrue(movedCamera.sameCameraVectorsAndLocation(expectedCamera), "camera is not in the expected place.");


        //TC03: assert throwing an exception when the points are teh same;
        assertThrows(IllegalArgumentException.class, () -> camera.cameraMove(new Point(1, 1, 1), new Point(1, 1, 1), new Vector(0,1,0)),
                "if the points are the same needs to throw an exception.");

    }
}
