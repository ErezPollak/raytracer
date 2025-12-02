package renderer;

import geometries.Geometry;
import geometries.Polygon;
import geometries.Sphere;
import lighting.DirectionalLight;
import lighting.NarrowBeamArchitecture;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static java.awt.Color.BLUE;
import static java.awt.Color.gray;

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


    @Test
    void testDepthOfField() {

        Scene scene = new Scene("Test scene");

        Geometry[] spheres = new Sphere[10];

        for (int i = 0; i < 10; i++) {
            spheres[i] = new Sphere(new Point(10 - i * 8, 0, 800 - 100 * i), 3) //
                    .setEmission(new Color(BLUE).reduce(2)) //
                    .setMaterial(new Material().setKd(0.3).setKs(0.2).setShininess(300).setKr(0.5));
        }
        Geometry polygon = new Polygon(
                new Point(100, -50, 1000),
                new Point(-100, -50, 1000),
                new Point(-100, -50, -100),
                new Point(100, -50, -100))
                .setEmission(new Color(gray))
                .setMaterial(new Material().setKd(0.2).setKs(0.3).setShininess(300).setKr(0.5));

        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150) //
                .setVPDistance(1000)

                //moving camera.
                //.cameraMove(new Point(0, 100, -500), new Point(0, 0, 0),new Vector(0,1,0)).cameraRoll(-5)
                //.cameraMove(new Point(0, 100, -1000), new Point(0, -50, 1000),new Vector(0,1,0))
                //.setVPDistance(250)


                //set the DoF.
                .setFPDistance(500)
                .setApertureSize(1);

        //set anti aliasing
        //.setAlias(true);


        //scene.geometries.add(sphere1, sphere2);
        scene.geometries.add(spheres);
        scene.geometries.add(polygon);
        scene.lights.add(new DirectionalLight(new Color(800, 500, 0), new Vector(1, -1, -0.5)));
        scene.lights.add(new SpotLight(new Color(0, 255, 0), new Point(100, 100, 800), new Vector(-1, -1, 0)).setNarrowBeam(NarrowBeamArchitecture.MY_ARCHITECTURE, 10));

        ImageWriter imageWriter = new ImageWriter("lightSphereDirectionalDepthOfFieldTesting1", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage(); //

    }

}
