package more_tests;

import geometries.*;
import lighting.LightSource;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import java.util.Arrays;
import java.util.List;

import static java.awt.Color.*;


public class general_tests {

    @Test
    void GeneralTest() {
        Scene scene = new Scene("TestScene");

        scene.geometries.add(
                new Tube(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 1d)
                        .setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Tube(new Ray(new Point(0, 0, 0), new Vector(0, 1, 0)), 1d)
                        .setEmission(new Color(GREEN)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Tube(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)), 1d)
                        .setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
        );
        Point sourceListLocation = new Point(100, 70, 90);
        Point sourceListLocation2 = new Point(-20, 80, 90);

        scene.geometries.add(

                new Triangle(new Point(0, 0, -100), new Point(100, 0, 0), new Point(50, 50, 0))
                        .setEmission(new Color(GRAY)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Plane(new Point(0, 0, -1000), new Point(-1000, 0, 0), new Point(0, -1000, 0))
                        .setEmission(new Color(GRAY)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
        );


        scene.geometries.add(
                new Sphere(sourceListLocation, 3d) //
                        .setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKt(0.8)),
                new Sphere(sourceListLocation2, 3d) //
                        .setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKt(0.8))
        );
        List<LightSource> sources = Arrays.asList(
                new PointLight(new Color(YELLOW).scale(0.5), sourceListLocation) //
                        .setKl(1E-5).setKq(1.5E-7),
                new PointLight(new Color(YELLOW).scale(0.5), sourceListLocation2) //
                        .setKl(1E-5).setKq(1.5E-7)//.setRadius(3)

        );

        scene.lights.addAll(sources);


        new Camera()
                .cameraMove(new Point(100, 100, 100), new Point(0, 0, 0), new Vector(0, 0, 1))
                .setVPSize(200, 200).setVPDistance(100)

                .setRayTracer(new RayTracerBasic(scene))
                .toAlias()
                //.setFPDistance(500).setApertureSize(5);


                .setImageWriter(new ImageWriter("general_test", 500, 500)) //
                .renderImage()
                .writeToImage();


    }


    @Test
    void AntiAliasingTest() {

        Scene scene = new Scene("TestScene");

        Intersectable torus = new Torus(Point.ZERO, 5, 3) //
                .setEmission(new Color(BLUE)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));

        scene.geometries.add(torus);
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point(-90, -90, 200), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7).setRadius(5));

        Camera camera = new Camera() //
                .cameraMove(new Point(10, 10, 10), new Point(0, 0, 0), new Vector(0, 0, 1))
                .setVPSize(200, 200).setVPDistance(100) //
                .setRayTracer(new RayTracerBasic(scene))
                //.toAlias(false)
                //.setFPDistance(500).setApertureSize(5);
                ;

        camera.setImageWriter(new ImageWriter("torusTest", 500, 500)) //
                .renderImage() //
                .writeToImage();
    }

}
