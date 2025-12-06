package generalTests;


import geometries.Cylinder;
import geometries.Plane;
import geometries.Sphere;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

public class generalTest {


    /**
     * a test that combines few light sources on the sphere.
     */
    @Test
    public void generalTest() {

        Point spotLocation = new Point(300, 0, 0);
        Point pointLocation = new Point(0, -200, 0);

        Scene generalScene = new Scene("Test scene")
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        Camera generalCamera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150) //
                .setVPDistance(250)
                .cameraRoll(-5)
                .cameraTransform(0);

        generalScene.geometries.add(

                new Cylinder(new Ray(new Point(0, 0, -50), new Vector(1, -1, -1)), 10, 1000)
                        .setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.3).setKs(0.3).setShininess(300).setKr(0.4)),


                new Sphere(new Point(-70, 70, -100), 60)
                        .setEmission(new Color(RED).reduce(2)) //
                        .setMaterial(new Material().setKd(0.3).setKs(0.3).setShininess(300).setKr(0.4)),

                new Sphere(new Point(0, 0, -50), 50) //
                        .setEmission(new Color(BLUE).reduce(2)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)),

                new Sphere(new Point(30, -30, 50), 30)
                        .setEmission(new Color(GREEN).reduce(4)) //
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.8)),

                new Sphere(new Point(40, -40, 100), 20)
                        .setEmission(new Color(CYAN).reduce(3)) //
                        .setMaterial(new Material().setKd(0.1).setKs(0.1).setShininess(300).setKt(0.8)),

                //the mirrors:

//                new Sphere(new Point(800, 800, -1200), 1000)
//                        .setEmission(new Color(Double3.ZERO))
//                        .setMaterial(new Material().setKr(1).setKs(0)),
////
                new Plane(new Point(300, 300, -300), new Vector(2, 1, -6))
                        .setEmission(new Color(Double3.ZERO))
                        .setMaterial(new Material().setKr(1)),

                new Plane(new Point(-300, -300, -300), new Vector(1, 2, 6))
                        .setEmission(new Color(new Double3(5)))
                        .setMaterial(new Material().setKr(1)),


                //spheres for the light sources:

                new Sphere(spotLocation, 10)
                        .setEmission(new Color(WHITE).reduce(2)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300).setKt(1)),
                new Sphere(pointLocation, 10)
                        .setEmission(new Color(WHITE).reduce(2)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300).setKt(1))

        );

        generalScene.lights.add(
                new DirectionalLight(new Color(YELLOW), new Vector(1, -1, -0.5)));
        generalScene.lights.add(
                new SpotLight(new Color(WHITE), spotLocation, new Vector(-1, 0, 0)).setNarrowBeam(NarrowBeamArchitecture.DAN_ARCHITECTURE, 30).setKl(0.0000001).setKq(0.0000002));
        generalScene.lights.add(
                new PointLight(new Color(WHITE), pointLocation).setKl(0.00001).setKq(0.00002));


        ImageWriter imageWriter = new ImageWriter("lightSphereGeneralTest", 1000, 1000);
        generalCamera.setImageWriter(imageWriter) //
                .setAlias(true)
                .setThreadsCount(4)
                .setPrintInterval(0.1)
                .setRayTracer(new RayTracerBasic(generalScene)) //
                .renderImage() //
                .writeToImage(); //

    }

}
