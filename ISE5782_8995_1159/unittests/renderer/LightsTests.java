package renderer;

import org.junit.jupiter.api.Test;

import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

import static java.awt.Color.*;

/**
 * Test rendering a basic image
 *
 * @author Dan
 */
public class LightsTests {
    private Scene scene1 = new Scene("Test scene");
    private Scene scene2 = new Scene("Test scene") //
            .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));
    private Camera camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVPSize(150, 150) //
            .setVPDistance(1000);
    private Camera camera2 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVPSize(200, 200) //
            .setVPDistance(1000);

    private Point[] p = { // The Triangles' vertices:
            new Point(-110, -110, -150), // the shared left-bottom
            new Point(95, 100, -150), // the shared right-top
            new Point(110, -110, -150), // the right-bottom
            new Point(-75, 78, 100)}; // the left-top
    private Point trPL = new Point(30, 10, -100); // Triangles test Position of Light
    private Point spPL = new Point(-50, -50, 25); // Sphere test Position of Light
    private Color trCL = new Color(800, 500, 250); // Triangles test Color of Light
    private Color spCL = new Color(800, 500, 0); // Sphere test Color of Light
    private Vector trDL = new Vector(-2, -2, -2); // Triangles test Direction of Light
    private Material material = new Material().setKd(0.5).setKs(0.5).setShininess(300);
    private Geometry triangle1 = new Triangle(p[0], p[1], p[2]).setMaterial(material);
    private Geometry triangle2 = new Triangle(p[0], p[1], p[3]).setMaterial(material);
    private Geometry sphere = new Sphere(new Point(0, 0, -50), 50d) //
            .setEmission(new Color(BLUE).reduce(2)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

    /**
     * Produce a picture of a sphere lighted by a directional light
     */
    @Test
    public void sphereDirectional() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new DirectionalLight(spCL, new Vector(1, 1, -0.5)));

        ImageWriter imageWriter = new ImageWriter("lightSphereDirectional", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of a sphere lighted by a point light
     */
    @Test
    public void spherePoint() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new PointLight(spCL, spPL).setKl(0.001).setKq(0.0002));

        ImageWriter imageWriter = new ImageWriter("lightSpherePoint", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void sphereSpot() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5)).setKl(0.001).setKq(0.0002));

        ImageWriter imageWriter = new ImageWriter("lightSphereSpot", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of a sphere lighted by a narrow spotlight
     */
    @Test
    public void sphereSpotSharp() {
        scene1.geometries.add(sphere);
        scene1.lights
                .add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5)).setNarrowBeam(10).setKl(0.001).setKq(0.0002));

        ImageWriter imageWriter = new ImageWriter("lightSphereSpotSharp", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }


    /**
     * Produce a picture of two triangles lighted by a directional light
     */
    @Test
    public void trianglesDirectional() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new DirectionalLight(trCL, trDL));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesDirectional", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of two triangles lighted by a point light
     */
    @Test
    public void trianglesPoint() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new PointLight(trCL, trPL).setKl(0.001).setKq(0.0002));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesPoint", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of two triangles lighted by a spotlight
     */
    @Test
    public void trianglesSpot() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new SpotLight(trCL, trPL, trDL).setKl(0.001).setKq(0.0001));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesSpot", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of a two triangles lighted by a narrow spot light
     */
    @Test
    public void trianglesSpotSharp() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new SpotLight(trCL, trPL, trDL).setNarrowBeam(40).setKl(0.001).setKq(0.00004));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesSpotSharp", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage() //
                .writeToImage(); //
    }


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
                //.cameraMove(new Point(250, 0, 0), new Point(0, 0, 0))
                //.cameraTransform(0)
                .cameraRoll(-5)
                ;

        generalScene.geometries.add(

//                new Plane(new Point(-1000,0,0),new Vector(-1,-0.001,0))
//                        .setEmission(new Color(BLACK).reduce(2)) //
//                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300).setKr(1)),

                new Sphere(new Point(-70, 70, -100), 60)
                        .setEmission(new Color(RED).reduce(2)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)),

                new Sphere(new Point(0, 0, -50), 50) //
                        .setEmission(new Color(BLUE).reduce(2)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)),

                new Sphere(new Point(30, -30, 50), 30)
                        .setEmission(new Color(GREEN).reduce(2)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)),

                new Sphere(new Point(40, -40, 100), 20)
                        .setEmission(new Color(CYAN).reduce(2)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300)),

                //the mirrors:


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
                new SpotLight(new Color(WHITE), spotLocation, new Vector(-1, 0, 0)).setNarrowBeam(30).setKl(0.0000001).setKq(0.0000002));
        generalScene.lights.add(
                new PointLight(new Color(WHITE), pointLocation).setKl(0.00001).setKq(0.00002));


        ImageWriter imageWriter = new ImageWriter("lightSphereGeneralTest", 1000, 1000);
        generalCamera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(generalScene)) //
                .renderImage() //
                .writeToImage(); //

    }


}
