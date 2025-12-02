package renderer;

import geometries.*;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static java.awt.Color.*;

public class SoftShadowTest {

    Scene scene = new Scene("TestScene");


    @Test
    void SoftShadowTest() {
        Intersectable sphere = new Sphere(new Point(0, 0, -200), 60d) //
                .setEmission(new Color(BLUE)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
        Material trMaterial = new Material().setKd(0.2).setKs(0.3).setShininess(30);
        Geometry triangle = new Triangle(new Point(-70, -40, 0), new Point(-40, -70, 0), new Point(-68, -68, -4))
                .setEmission(new Color(BLUE)).setMaterial(trMaterial);

        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000) //
                .setRayTracer(new RayTracerBasic(scene));

        scene.geometries.add(sphere, triangle.setEmission(new Color(BLUE)).setMaterial(trMaterial.setKt(0.5)));
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point(-90, -90, 200), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7).setRadius(10));
        camera.setImageWriter(new ImageWriter("softShadowTriangleAndSparerTest", 500, 500)) //
                .setAlias(true)
                //.setFPDistance(500).setApertureSize(10)
                .renderImage() //
                .writeToImage();

    }


    @Test
    public void trianglesPointWithShadow() {

        Geometry polygon = new Polygon(new Point(-100, -100, -100),
                new Point(100, -100, -100),
                new Point(100, 100, -100),
                new Point(-100, 100, -100))
                .setEmission(new Color(GRAY))
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

        Geometry sphere = new Sphere(new Point(0, 0, -100), 30)
                .setMaterial(new Material().setKs(0.5).setKd(0.5).setShininess(300))
                .setEmission(new Color(RED));


        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200) //
                .setVPDistance(100)
                //.cameraMove(new Point(-100,-100,100) , new Point(50, 50, -100),new Vector(0,0,1));//side
                .cameraMove(new Point(50, 50, 100), new Point(50, 50, 0), new Vector(0, 0, 1)); /// up

        scene.geometries.add(polygon, sphere);
        scene.lights.add(new SpotLight(new Color(800, 500, 250), new Point(-100, 100, 50), new Point(0, 0, -100).subtract(new Point(-100, 100, 50)))
                .setKl(0.001).setKq(0.0002).setRadius(20));
        scene.lights.add(new PointLight(new Color(800, 500, 250), new Point(100, 100, 50))
                .setKl(0.001).setKq(0.0002).setRadius(20));

        ImageWriter imageWriter = new ImageWriter("softShadowPointAndSpotWithShadow", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .setAlias(true)
                .renderImage() //
                .writeToImage(); //
    }

}
