package renderer;

import static java.awt.Color.BLUE;

import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import lighting.DirectionalLight;
import lighting.NarrowBeamArchitecture;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;

import primitives.*;
import scene.Scene;

/**
 * Testing Depth of field Class
 *
 * @author Erez Polak and Eliran Salama.
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


        Scene scene = new Scene("Test scene");

//        Geometry sphere1 = new Sphere(new Point(0, 0, -50), 50d) //
//                .setEmission(new Color(BLUE).reduce(2)) //
//                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
//
//        Geometry sphere2 = new Sphere(new Point(10, 0, 800), 4) //
//                .setEmission(new Color(BLUE).reduce(2)) //
//                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));


        Geometry[] spheres = new Sphere[10];

        for (int i = 0; i < 10; i++) {
            spheres[i] = new Sphere(new Point(10 - i * 8 , 0, 800 - 100 * i), 3) //
                    .setEmission(new Color(BLUE).reduce(2)) //
                    .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));
        }
        Geometry plane = new Plane(new Point(0,-20,0), new Vector(0,1,0))
                .setEmission(new Color(255,0,0))
                .setMaterial(new Material().setKd(0.2).setKs(0.3).setShininess(300).setKr(0.5));

        Color spCL = new Color(800, 500, 0); // Sphere test Color of Light
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150) //
                .setVPDistance(1000)
                .setApertureSize(1);


        //scene.geometries.add(sphere1, sphere2);
        scene.geometries.add(spheres);
        scene.geometries.add(plane);
        scene.lights.add( new DirectionalLight(spCL, new Vector(1, -1, -0.5)));
        scene.lights.add(new SpotLight(new Color(0,255,0),new Point(100,100,800),new Vector(-1,-1,0)).setNarrowBeam(NarrowBeamArchitecture.MY_ARCHITECTURE,10));

        ImageWriter imageWriter = new ImageWriter("lightSphereDirectionalDepthOfFieldTesting", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage(); //

    }


}
