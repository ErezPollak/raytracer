package renderer;

import static java.awt.Color.BLUE;

import geometries.Geometry;
import geometries.Sphere;
import lighting.DirectionalLight;
import org.junit.jupiter.api.Test;

import primitives.*;
import scene.Scene;

/**
 *
 * Testing Depth of field Class
 * @author Erez Polak and Eliran Salama.
 *
 */
public class DepthOfFieldTest {

    private Scene scene = new Scene("Test scene");
    private Geometry sphere1 = new Sphere(new Point(0, 0, -50), 50d) //
            .setEmission(new Color(BLUE).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    private Geometry sphere2 = new Sphere(new Point(10, 0, 800), 4) //
            .setEmission(new Color(BLUE).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


    private Color spCL = new Color(800, 500, 0); // Sphere test Color of Light
    private Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVPSize(150, 150) //
            .setVPDistance(1000)
            .setApertureSize(1);

    @Test
    void testConstructRay() {

        scene.geometries.add(sphere1,sphere2);
        scene.lights.add(new DirectionalLight(spCL, new Vector(1, 1, -0.5)));

        ImageWriter imageWriter = new ImageWriter("lightSphereDirectionalDepthOfFieldTesting", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage(); //

    }


}
