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

public class templateTest {



    @Test
    public void templateTest() {

        Double3 x = new Double3(300, 0, 0);
        Double3 y = new Double3(0, 300, 0);
        Double3 z = new Double3(0, 0, 300);


        Scene scene = new Scene("Templeta scene")
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        scene.geometries.add(
                new Tube(1, new Ray(Point.ZERO, new Vector(x))).setEmission(new Color(RED)),
                new Tube(1, new Ray(Point.ZERO, new Vector(y))).setEmission(new Color(GREEN)),
                new Tube(1, new Ray(Point.ZERO, new Vector(z))).setEmission(new Color(BLUE)),
                new Sphere(new Point(x), 10).setEmission(new Color(RED)),
                new Sphere(new Point(y), 10).setEmission(new Color(GREEN)),
                new Sphere(new Point(z), 10).setEmission(new Color(BLUE))


        );

        scene.lights.add(new DirectionalLight(new Color(WHITE).reduce(2), new Vector(-1, 1, 1)));


        ImageWriter imageWriter = new ImageWriter("TemplateImage", 1000, 1000);
        new Camera(new Point(1000, 1000, 1000), new Vector(-1, -1, -1), new Vector(-1, -1, 2)) //
                .setVPSize(150, 150) //
                .setVPDistance(250)
                .setImageWriter(imageWriter) //
                .setThreadsCount(4)
                .setPrintInterval(0.1)
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage(); //
    }
}
