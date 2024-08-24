package scene;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SceneTests {

    @Test
    public void SceneJsonTests() throws ParseException, IOException, NotImplementedException {
        JSONObject sceneJsonObject = new org.json.JSONObject(Files.readString(Paths.get(System.getProperty("user.dir") + "\\ISE5782_8995_1159\\unittests\\scene\\sceneTest.json")));
        Scene scene = new Scene(sceneJsonObject);
        assertEquals("jsonScene", scene.name, "ERROR: scene name is incorrect");
        assertEquals(new Color(75, 127, 190), scene.background, "ERROR: scene background color is incorrect");
        assertEquals(new Color(0, 191, 200), scene.ambientLight.getIntensity(), "ERROR: scene background color is incorrect");

    }

    @Test
    void SceneJsonPictureTests() throws IOException {
        new Camera()
                .cameraMove(new Point(1000, 1000, 1000), new Point(0, 0, 0), new Vector(0,0,1))
                .setVPSize(150, 150) //
                .setVPDistance(250)
                .setImageWriter(new ImageWriter("jsonSceneTest", 1000, 1000)) //
                .setThreadsCount(4)
                .setPrintInterval(1)
                .toAlias()
                .setRayTracer(new RayTracerBasic(new Scene(new org.json.JSONObject(Files.readString(Paths.get(System.getProperty("user.dir") + "\\ISE5782_8995_1159\\unittests\\scene\\sceneTestPic.json"))))))
                .renderImage()
                .writeToImage();

    }

}
