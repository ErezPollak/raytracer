package more_tests;

import geometries.*;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;


public class general_tests {

    @Test
    void GeneralTest() {
        Scene scene = new Scene("TestScene");

        scene.geometries.add(
                new Tube(1d, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)))
                        .setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Tube(1d, new Ray(new Point(0, 0, 0), new Vector(0, 1, 0)))
                        .setEmission(new Color(GREEN)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Tube(1d, new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)))
                        .setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
        );


        scene.geometries.add(


                new Sphere(new Point(0, 0, 0), 60d) //
                        .setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKt(0.5).setKr(0.5)),
                new Sphere(new Point(50, 0, 100), 10d) //
                        .setEmission(new Color(RED)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),
                new Triangle(new Point(0, 0, 50), new Point(100, 0, 0), new Point(100, 100, 0))
                        .setEmission(new Color(GRAY)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
        );
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point(-90, -90, 200), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7).setRadius(5));


        new Camera()
                .cameraMove(new Point(100, 100, 100), new Point(0, 0, 0), new Vector(0, 0, 1))
                .setVPSize(200, 200).setVPDistance(100)

                .setRayTracer(new RayTracerBasic(scene))
                //.toAlias(false)
                //.setFPDistance(500).setApertureSize(5);


                .setImageWriter(new ImageWriter("general_test", 500, 500)) //
                .renderImage()
                .writeToImage();


    }

}
