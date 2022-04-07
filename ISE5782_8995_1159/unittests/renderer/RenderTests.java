package renderer;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;

import lighting.AmbientLight;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

import java.io.FileReader;

/**
 * Test rendering a basic image
 *
 * @author Dan
 */
public class RenderTests {

    /**
     * Produce a scene with basic 3D model and render it into a png image with a
     * grid
     */
    @Test
    public void basicRenderTwoColorTest() {
        Scene scene = new Scene("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(255, 191, 191), //
                        new Double3(1, 1, 1))) //
                .setBackground(new Color(75, 127, 90));

        scene.geometries.add(new Sphere(50, new Point(0, 0, -100)),
                new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
                // left
                new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)), // down
                // left
                new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
        // right
        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPDistance(100) //
                .setVPSize(500, 500) //
                .setImageWriter(new ImageWriter("base render test", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));

        camera.renderImage();
        camera.printGrid(100, new Color(java.awt.Color.YELLOW));
        camera.writeToImage();
    }

    /**
     * Test for XML based scene - for bonus
     */
    @Test
    public void basicRenderXml() {
        Scene scene = new Scene("XML Test scene");

        // enter XML file name and parse from XML file into scene object
        // ...
        String path = System.getProperty("user.dir");

        JSONParser parser = new JSONParser();
        try {

            JSONObject jsonScene = (JSONObject) parser.parse(new FileReader(path + "\\Json\\stam.json"));

            String ambientLight = (String) jsonScene.get("ambient-light");
            Double3 dAmbientLight = new Double3(ambientLight);
            scene.setAmbientLight(new AmbientLight(new Color(dAmbientLight),new Double3(1,1,1)));

            String background = (String) jsonScene.get("background");
            Double3 dBackground = new Double3(background);
            scene.setBackground(new Color(dBackground));

            JSONObject geometries = (JSONObject) jsonScene.get("geometries");
            for (Object keyStr : geometries.keySet()) {
                JSONObject keyValue = (JSONObject) geometries.get(keyStr);
                if(keyStr.toString().startsWith("triangle")){

                    String p0 = (String) keyValue.get("p0");
                    String p1 = (String) keyValue.get("p1");
                    String p2 = (String) keyValue.get("p2");

                    Double3 d0 = new Double3(p0);
                    Double3 d1 = new Double3(p1);
                    Double3 d2 = new Double3(p2);

                    scene.geometries.add(new Triangle(new Point(d0),new Point(d1),new Point(d2)));

                }else if(keyStr.toString().startsWith("sphere")){
                    int radius = Integer.parseInt((String) keyValue.get("radius"));
                    String center = (String) keyValue.get("center");

                    Double3 dCenter = new Double3(center);

                    scene.geometries.add(new Sphere(radius ,new Point(dCenter)));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPDistance(100) //
                .setVPSize(500, 500)
                .setImageWriter(new ImageWriter("xml render test", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));
        camera.renderImage();
        camera.printGrid(100, new Color(java.awt.Color.YELLOW));
        camera.writeToImage();
    }
}
