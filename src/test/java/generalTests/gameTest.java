package generalTests;


import geometries.*;
import lighting.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

public class gameTest {


    @Test
    public void gameTest() {

        Camera generalCamera = new Camera(new Point(1000, -1000, 1000), Point.ZERO, new Vector(0,0,1)) //
                .setVPSize(150, 150) //
                .setVPDistance(250)
                //.cameraRoll(-5)
                ///.cameraTransform(0)
                ;


        Scene generalScene = new Scene("Test scene")
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        Double3 x = new Double3(500, 0, 0);
        Double3 y = new Double3(0, 500, 0);
        Double3 z = new Double3(0, 0, 500);

        generalScene.geometries.add(
                new Tube(1, new Ray(Point.ZERO, new Vector(1,0,0)))
                        .setEmission(new Color(RED))
                //  .setMaterial(new Material().setKd(0.3).setKs(0.3).setShininess(300).setKr(0.4))
                ,
                new Tube(1, new Ray(Point.ZERO, new Vector(0,1,0)))
                        .setEmission(new Color(GREEN))
                //    .setMaterial(new Material().setKd(0.3).setKs(0.3).setShininess(300).setKr(0.4))
                ,
                new Tube(1, new Ray(Point.ZERO, new Vector(0,0,1)))
                        .setEmission(new Color(BLUE))
                //.setMaterial(new Material().setKd(0.3).setKs(0.3).setShininess(300).setKr(0.4))
                ,
                new Sphere(new Point(x), 10).setEmission(new Color(RED)),
                new Sphere(new Point(y), 10).setEmission(new Color(GREEN)),
                new Sphere(new Point(z), 10).setEmission(new Color(BLUE)),


                new Sphere(Point.ZERO, 100)
                        .setEmission(new Color(CYAN).reduce(3))
                        .setMaterial(new Material().setKd(0.9).setKs(0.1).setShininess(300).setKt(0).setKr(0))
                ,
                new Sphere(new Point(-200, 200, 200), 100)
                        .setEmission(new Color(RED).reduce(3))
                        .setMaterial(new Material().setKd(0.3).setKs(0.3).setShininess(300))
                ,
                new Triangle(new Point(100,100,0), new Point(100, 0, 100), new Point(0, 100, 100))
                        .setEmission(new Color(YELLOW))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300).setKt(0).setKr(0))

        );

        generalScene.lights.add(
                new DirectionalLight(new Color(WHITE).reduce(2), new Vector(-1, -1 , -1))
        );

//        generalScene.lights.add(
//                new DirectionalLight(new Color(BLUE).scale(3), new Vector(-1, 0 , -1))
//        );

        generalScene.lights.add(
                new SpotLight(new Color(BLUE).scale(2), new Point(200, 200, 200) ,  new Vector(-1, -1 , -1)).setNarrowBeam(NarrowBeamArchitecture.DAN_ARCHITECTURE, 30).setKl(0.0000001).setKq(0.0000002)
        );

        ImageWriter imageWriter = new ImageWriter("gameTest", 1000, 1000);
        generalCamera.setImageWriter(imageWriter) //
                //.setAlias(true)
                .setThreadsCount(4)
                .setPrintInterval(0.1)
                .setRayTracer(new RayTracerBasic(generalScene)) //
                .renderImage() //
                .writeToImage(); //
    }

}
