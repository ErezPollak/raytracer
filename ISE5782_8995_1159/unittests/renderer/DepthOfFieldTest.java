package renderer;

import static java.awt.Color.BLUE;
import static java.awt.Color.gray;

import geometries.*;
import lighting.DirectionalLight;
import lighting.NarrowBeamArchitecture;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;

import primitives.*;
import scene.Scene;

import java.util.Random;

/**
 * Testing Depth of field Class
 *
 * @author Erez Polak and Eliran Salama.
 */

public class DepthOfFieldTest {

//    private Scene scene = new Scene("Test scene");
//    private Geometry sphere1 = new Sphere(new Point(0, 0, -50), 50d) //
//            .setEmission(new Color(BLUE).reduce(2)) //
//            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
//
//    private Geometry sphere2 = new Sphere(new Point(10, 0, 800), 4) //
//            .setEmission(new Color(BLUE).reduce(2)) //
//            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
//
//    private Color spCL = new Color(800, 500, 0); // Sphere test Color of Light

    Random r = new Random();

    @Test
    void testDepthOfField() {

        Scene scene = new Scene("Test scene");

        Geometry[] spheres = new Sphere[11];

        for (int i = 0; i <= 10; i++) {
            spheres[i] = new Sphere(new Point(10 - i * 8, 0, 800 - 100 * i), 3) //
                    .setEmission(new Color(25 * i, -(i - 800) * (i + 200) / 1000, 250 - 25 * i)) //
                    .setMaterial(new Material().setKd(0.3).setKs(0.2).setShininess(300).setKr(0.5));
        }

        Geometry polygon = new Polygon(
                new Point(100, -50, 1000),
                new Point(-100, -50, 1000),
                new Point(-100, -50, -100),
                new Point(100, -50, -100))
                .setEmission(new Color(gray))
                .setMaterial(new Material().setKd(0.2).setKs(0.3).setShininess(300).setKr(0.5));

        scene.geometries.add(spheres);
        scene.geometries.add(polygon);
        scene.lights.add(new DirectionalLight(new Color(800, 500, 0), new Vector(1, -1, -0.5)));
        scene.lights.add(new SpotLight(new Color(0, 255, 0), new Point(100, 100, 800), new Vector(-1, -1, 0)).setNarrowBeam(NarrowBeamArchitecture.MY_ARCHITECTURE, 10));


        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150) //
                .setVPDistance(1000)
                .setThreadsCount(3);


        for (int i = 0; i <= 6; i++) {
            //set the DoF.
            camera.setFPDistance(800 - 100 * i)
                .setApertureSize(2)
                .setImageWriter(new ImageWriter("DepthOfFieldTesting" + i, 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage(); //

        }
    }

}
