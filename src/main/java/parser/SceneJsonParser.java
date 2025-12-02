package parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * static class that holds in it the functionality for parsing a json file.
 */
public class SceneJsonParser {

    /**
     * return a map out of the scene attributes.
     *
     * @param scene the scene.
     * @return the
     */
    public static Map<String, String> setSceneAttributes(JSONObject scene) {

        Map<String, String> attributes = new HashMap<>();

        attributes.put("name", (String) scene.get("name"));
        attributes.put("background", (String) scene.get("background"));

        return attributes;

    }

    /**
     * return a map with the ambient light attributes.
     *
     * @param ambientLightJson the json object.
     * @return the map.
     */
    public static Map<String, String> setSceneAmbientLight(JSONObject ambientLightJson) {

        Map<String, String> ambientLight = new HashMap<>();

        ambientLight.put("color", (String) ambientLightJson.get("color"));
        ambientLight.put("Ka", (String) ambientLightJson.get("Ka"));

        return ambientLight;

    }


    /**
     * return a map with the ambient light attributes.
     *
     * @param trianglesJson the json array of triangles.
     * @return the map.
     */
    public static List<Map<String, String>> setSceneTriangles(JSONArray trianglesJson) {

        List<Map<String, String>> triangles = new LinkedList<>();

        for (Object t : trianglesJson.toArray()) {
            Map<String, String> triangleMap = new HashMap<>();
            JSONObject triangle = (JSONObject) t;
            triangleMap.put("p0", (String) triangle.get("p0"));
            triangleMap.put("p1", (String) triangle.get("p1"));
            triangleMap.put("p2", (String) triangle.get("p2"));

            JSONObject material = (JSONObject) triangle.get("material");
            triangleMap.putAll(getMaterialMap(material));

            JSONObject emission = (JSONObject) triangle.get("emission");
            triangleMap.putAll(getEmissionMap(emission));

            triangles.add(triangleMap);
        }

        return triangles;

    }

    /**
     * returns a list of maps of all the spheres in the scene.
     *
     * @param spheresJson the json array of all the spheres.
     * @return the list of all the spheres
     */
    public static List<Map<String, String>> setSceneSpheres(JSONArray spheresJson) {

        List<Map<String, String>> speres = new LinkedList<>();

        for (Object s : spheresJson.toArray()) {
            Map<String, String> sphereMap = new HashMap<>();
            JSONObject sphere = (JSONObject) s;
            sphereMap.put("radius", (String) sphere.get("radius"));
            sphereMap.put("center", (String) sphere.get("center"));

            JSONObject material = (JSONObject) sphere.get("material");
            sphereMap.putAll(getMaterialMap(material));

            JSONObject emission = (JSONObject) sphere.get("emission");
            sphereMap.putAll(getEmissionMap(emission));

            speres.add(sphereMap);
        }

        return speres;
    }

    private static Map<String, String> getEmissionMap(JSONObject emission) {
        Map<String, String> emissionMap = new HashMap<>();
        emissionMap.put("color", (String) emission.get("color"));
        return emissionMap;
    }

    private static Map<String, String> getMaterialMap(JSONObject material) {
        Map<String, String> materialMap = new HashMap<>();
        materialMap.put("kD", (String) material.get("kD"));
        materialMap.put("kS", (String) material.get("kS"));
        materialMap.put("nShininess", (String) material.get("nShininess"));
        materialMap.put("kT", (String) material.get("kT"));
        materialMap.put("kR", (String) material.get("kR"));
        return materialMap;
    }

}
