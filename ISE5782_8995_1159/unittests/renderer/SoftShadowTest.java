package renderer;

import geometries.Geometry;
import geometries.Intersectable;
import geometries.Sphere;
import geometries.Triangle;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;

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
                        .setKl(1E-5).setKq(1.5E-7).setRadius(5));
        camera.setImageWriter(new ImageWriter("softShadowTriangleAndSparerTest", 500, 500)) //
                .renderImage() //
                .writeToImage();

    }


    @Test
    public void trianglesPointWithShadow() {

        Point[] p = { // The Triangles' vertices:
                new Point(-110, -110, -150), // the shared left-bottom
                new Point(95, 100, -150), // the shared right-top
                new Point(110, -110, -150), // the right-bottom
                new Point(-75, 78, 100)}; // the left-top
        Material material = new Material().setKd(0.5).setKs(0.5).setShininess(300);


        Geometry triangle1 = new Triangle(p[0], p[1], p[2]).setMaterial(material);
        Geometry triangle2 = new Triangle(p[0], p[1], p[3]).setMaterial(material);

        Geometry sphere = new Sphere(new Point(20,50,-100),30)
                .setMaterial(new Material().setKs(0.5).setKd(0.5).setShininess(300))
                .setEmission(new Color(RED));


        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200) //
                .setVPDistance(1000);

        scene.geometries.add(triangle1, triangle2,sphere);
        //scene.lights.add(new SpotLight(new Color(800, 500, 250), new Point(30,-50,-100), new Vector(0,1,0))
        scene.lights.add(new PointLight(new Color(800, 500, 250), new Point(30,-50,-100))
                .setKl(0.001).setKq(0.0002).setRadius(20));

        ImageWriter imageWriter = new ImageWriter("softShadowPointAndSpotWithShadow", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage(); //
    }

}
