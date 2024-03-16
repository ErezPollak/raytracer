package parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SceneDescriptor {
    Map<String, String> sceneAttributes;
    Map<String, String> ambientLightAttributes;
    List<Map<String, String>> spheres;
    List<Map<String, String>> triangles;
    List<Map<String, String>> planes;
    List<Map<String, String>> cylinders;
    List<Map<String, String>> polygons;
    List<Map<String, String>> tubes;


    public SceneDescriptor() {
        this.sceneAttributes = new HashMap<String, String>();
        this.ambientLightAttributes = new HashMap<String, String>();
        this.spheres = new LinkedList<>();
        this.triangles = new LinkedList<>();
        this.planes = new LinkedList<>();
        this.cylinders = new LinkedList<>();
        this.polygons = new LinkedList<>();
        this.tubes = new LinkedList<>();
    }

    public void initializeFromJsonObject(String jsonString) {

        try {

            JSONObject jsonScene = (JSONObject) new JSONParser().parse(jsonString);

            //initializing the scene attributes;
            this.sceneAttributes =
                    SceneJsonParser.setSceneAttributes(jsonScene);

            //initializing the ambientLight:
            JSONObject ambientLight = (JSONObject)jsonScene.get("ambientLight");
            this.ambientLightAttributes =
                    SceneJsonParser.setSceneAmbientLight(ambientLight);

            //extracting the geometries json object.
            JSONObject geometriesJson = (JSONObject) jsonScene.get("geometries");

            //extraction the triangles.
            JSONArray triangles = (JSONArray) geometriesJson.get("triangles");
            this.triangles = SceneJsonParser.setSceneTriangles(triangles);

            //extracting the triangles.
            JSONArray spheres = (JSONArray) geometriesJson.get("spheres");
            this.spheres = SceneJsonParser.setSceneSpheres(spheres);



        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public Map<String, String> getSceneAttributes() {
        return sceneAttributes;
    }

    public Map<String, String> getAmbientLightAttributes() {
        return ambientLightAttributes;
    }

    public List<Map<String, String>> getSpheres() {
        return spheres;
    }

    public List<Map<String, String>> getTriangles() {
        return triangles;
    }

    public List<Map<String, String>> getPlanes() {
        return planes;
    }

    public List<Map<String, String>> getCylinders() {
        return cylinders;
    }

    public List<Map<String, String>> getPolygons() {
        return polygons;
    }

    public List<Map<String, String>> getTubes() {
        return tubes;
    }
}
