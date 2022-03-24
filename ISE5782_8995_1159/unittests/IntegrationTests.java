import static org.junit.jupiter.api.Assertions.*;

import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;

import renderer.Camera;
import primitives.*;

/**
 * integration test for the combination of the camera and view plain functions.
 *
 * @author Erez Polak
 */
public class IntegrationTests {

    static final double HEIGHT = 3.0, WIDTH = 3.0, DISTANCE = 1.0;
    static final int NX = 3, NY = 3;
    static final Point ZERO_POINT = new Point(0, 0, 0);

    /**
     * check the integration for the Plain integration for Plain to the view plane.
     */
    @Test
    void testPlainIntegration() {

        Camera camera = new Camera(ZERO_POINT, new Vector(0, 1, 0), new Vector(0, 0, -1))
                .setVPDistance(DISTANCE)
                .setVPSize(HEIGHT, WIDTH);

        Plane plane;

        //Test01: camara in front of a plane 9 interactions expected.
        plane = new Plane(new Point(0,0,-3), new Vector(0,0,-1));
        assertEquals(9 , countingFunction(camera , plane , NX , NY) ,
                "Test01 failed: 9 intersections expected.");

        //Test02: camara in front of a plane 9 interactions expected.
        plane = new Plane(new Point(0,0,-5), new Vector(0,-1,2));
        assertEquals(9 , countingFunction(camera , plane , NX , NY) ,
                "Test01 failed: 9 intersections expected.");

        //Test03: camara in front of a sloping plain plane 6 interactions expected.
        plane = new Plane(new Point(0,0,-5), new Vector(0,-1,1));
        assertEquals(6 , countingFunction(camera , plane , NX , NY) ,
                "Test01 failed: 9 intersections expected.");

        //Test04: camara is behind the plane, 0 interactions expected.
        plane = new Plane(new Point(0,0,5), new Vector(0,0,1));
        assertEquals(0 , countingFunction(camera , plane , NX , NY) ,
                "Test01 failed: 9 intersections expected.");

    }

    /**
     * check the integration for the Triangle integration for Plain to the view plane.
     */
    @Test
    void testTriangleIntegration() {

        Camera camera = new Camera(ZERO_POINT, new Vector(0, 1, 0), new Vector(0, 0, -1))
                .setVPDistance(DISTANCE)
                .setVPSize(HEIGHT, WIDTH);
        Triangle triangle;

        //Test01: camara in front of a plane 9 interactions expected.
        triangle = new Triangle(new Point(1,-1,-2), new Point(-1,-1,-2),new Point(0,1,-2));
        assertEquals(1 , countingFunction(camera , triangle , NX , NY) ,
                "Test01 failed: 1 intersections expected.");

        //Test02: camara in front of a plane 9 interactions expected.
        triangle = new Triangle(new Point(1,-1,-2), new Point(-1,-1,-2),new Point(0,20,-2));
        assertEquals(2 , countingFunction(camera , triangle , NX , NY) ,
                "Test01 failed: 2 intersections expected.");

    }

    /**
     * check the integration for the Sphere integration for Plain to the view plane.
     */
    @Test
    void testSphereIntegration() {

        Camera camera;
        Sphere sphere;

        //Test01: camara outside a small sphere only two intersections expected.
        camera = new Camera(ZERO_POINT, new Vector(0, 1, 0), new Vector(0, 0, -1))
                .setVPDistance(DISTANCE)
                .setVPSize(HEIGHT, WIDTH);
        sphere = new Sphere(1, new Point(0, 0, -3));
        assertEquals(2, countingFunction(camera, sphere, NX, NY),
                "Test01 failed: expected only two points.");

        //Test02: camara outside a big sphere, 18 intersections expected.
        camera = new Camera(new Point(0, 0, 0.5), new Vector(0, 1, 0), new Vector(0, 0, -1))
                .setVPDistance(DISTANCE)
                .setVPSize(HEIGHT, WIDTH);
        sphere = new Sphere(2.5, new Point(0, 0, -2.5));
        assertEquals(18, countingFunction(camera, sphere, NX, NY),
                "Test02 failed: expected 18 points.");

        //Test03: camara outside a midsize sphere only two intersections expected.
        sphere = new Sphere(2, new Point(0, 0, -2));
        assertEquals(10, countingFunction(camera, sphere, NX, NY),
                "Test03 failed: expected 10 points.");

        //Test04: camara inside a big sphere, 9 intersections expected.
        sphere = new Sphere(5, new Point(0, 0, -2));
        assertEquals(10, countingFunction(camera, sphere, NX, NY),
                "Test04 failed: expected 9 points.");

        //Test05: camara behind a sphere, no intersections expected.
        sphere = new Sphere(0.5, new Point(0, 0, 1));
        assertEquals(10, countingFunction(camera, sphere, NX, NY),
                "Test05 failed: expected 0 points.");

    }

    /**
     * go through all the points in the veiw plane, creates all the rays, and calculates all the intersection points.
     * @param camera the camera that sees the picture.
     * @param geometry the shape to find intersections with.
     * @param nx the resolution of the view plane.
     * @param ny
     * @return the number of intersection points of the camera with the geometry, through the view plain.
     */
    private int countingFunction(Camera camera, Geometry geometry, int nx, int ny) {
        int sumOfRays = 0;
        for (int i = 0; i < nx; i++) {
            for (int j = 0; j < ny; j++) {
                sumOfRays += geometry.findIntersections(camera.constructRay(nx, ny, j, i)).size();
            }
        }
        return sumOfRays;
    }

}
