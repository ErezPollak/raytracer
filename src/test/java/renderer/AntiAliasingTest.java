package renderer;

import geometries.Intersectable;
import geometries.Sphere;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static java.awt.Color.BLUE;

public class AntiAliasingTest {

    @Test
    void AntiAliasingTest() {

        Scene scene = new Scene("TestScene");

        Intersectable sphere = new Sphere(new Point(0, 0, -60), 60d) //
                .setEmission(new Color(BLUE)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));

        scene.geometries.add(sphere);
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point(-90, -90, 200), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7).setRadius(5));

        Camera camera = new Camera(new Point(0, 0, 100), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(100) //
                .setRayTracer(new RayTracerBasic(scene))
                .setAlias(true);
        //.setFPDistance(500).setApertureSize(5);

        camera.setImageWriter(new ImageWriter("antiAliasingTest", 500, 500)) //
                .renderImage() //
                .writeToImage();
    }
}
