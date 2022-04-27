package scene;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import parser.SceneDescriptor;
import primitives.Color;
import primitives.Double3;
import primitives.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * the class is parsing a scene from files.
 * the jason or xml files must be in the specified format in order for the parser to work.
 *
 * @author Erez Polak
 */
public class SceneBuilder {

    private SceneDescriptor sceneDesc;
    private Scene scene;
    private String filePath;

    /**
     * ctor
     */
    public SceneBuilder() {
        this.sceneDesc = new SceneDescriptor();
        this.scene = new Scene("");
        this.filePath = filePath;
    }

    /**
     * @return
     */
    public Scene loadSceneFromFile(String filePath, String sceneName) {
        String path = System.getProperty("user.dir");
        //loads the scene from the file and initialize the describer with its properties.
        try {
            FileReader fr = new FileReader(path + filePath);

            this.sceneDesc.initializeFromJsonObject(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //inserts the props to the scene object.
        //scene.

        return scene;
    }


//    /**
//     * receives a string in the format of the jason file: "x y z".
//     * and parse that into a double3 value.
//     * @param double3format a string in the format "x y z".
//     */
//    public Double3(String double3format){
//        String numbers[] = double3format.split(" ");
//        this.d1 = Integer.parseInt(numbers[0]);
//        this.d2 = Integer.parseInt(numbers[1]);
//        this.d3 = Integer.parseInt(numbers[2]);
//    }


//    /**
//     * parsing a scene from a Json file.
//     * the json must contain the variables in the specific order and key names.
//     *
//     * @param filePath  the path to the file.
//     * @param sceneName the name that the scene will have in the end of the operation.
//     * @return the scene that is created by the parameters given in the file.
//     */
//    public static Scene parseSceneFromJson(String filePath, String sceneName) {
//
//        Scene scene = new Scene(sceneName);
//
//        String path = System.getProperty("user.dir");
//
//        JSONParser parser = new JSONParser();
//        try {
//
//            JSONObject jsonScene = (JSONObject) parser.parse(new FileReader(path + filePath));
//
//            //extracting thr ambientLight parameter from the file, and update the scene accordingly.
//            String ambientLight = (String) jsonScene.get("ambient-light");
//            Double3 dAmbientLight = new Double3(ambientLight);
//            scene.setAmbientLight(new AmbientLight(new Color(dAmbientLight), new Double3(1, 1, 1)));
//
//            //extracting thr background parameter from the file, and update the scene accordingly.
//            String background = (String) jsonScene.get("background");
//            Double3 dBackground = new Double3(background);
//            scene.setBackground(new Color(dBackground));
//
//            //go over all the geometries in the file and
//            JSONObject geometries = (JSONObject) jsonScene.get("geometries");
//
//            JSONArray triangles = (JSONArray) geometries.get("triangles");
//            for (Object t : triangles.toArray()) {
//                JSONObject triangle = (JSONObject) t;
//                String p0 = (String) triangle.get("p0");
//                String p1 = (String) triangle.get("p1");
//                String p2 = (String) triangle.get("p2");
//
//                Double3 d0 = new Double3(p0);
//                Double3 d1 = new Double3(p1);
//                Double3 d2 = new Double3(p2);
//
//                scene.geometries.add(new Triangle(new Point(d0), new Point(d1), new Point(d2)));
//            }
//
//            JSONArray spheres = (JSONArray) geometries.get("spheres");
//            for (Object s : spheres) {
//                JSONObject sphere = (JSONObject) s;
//                int radius = Integer.parseInt((String) sphere.get("radius"));
//                String center = (String) sphere.get("center");
//                Double3 dCenter = new Double3(center);
//
//                scene.geometries.add(new Sphere(new Point(dCenter), radius));
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return scene;
//
//    }

}







