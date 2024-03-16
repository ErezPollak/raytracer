package scene;

import geometries.Geometries;
import geometries.Intersectable;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import parser.SceneDescriptor;
import primitives.Color;
import primitives.Double3;
import primitives.Material;
import primitives.Point;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * the class is parsing a scene from files.
 * the jason or xml files must be in the specified format in order for the parser to work.
 *
 * @author Erez Polak
 */
public class SceneBuilder {

    private final String PATH = System.getProperty("user.dir");

    private SceneDescriptor sceneDescriptor;
    private Scene scene;
    private String sceneFilePath;

    /**
     * ctor
     */
    public SceneBuilder(String filePath, String sceneName) {
        this.sceneDescriptor = new SceneDescriptor();
        this.scene = new Scene(sceneName);
        this.sceneFilePath = filePath;
    }

    /**
     * @return
     */
    public Scene build() {
        //loads the scene from the file and initialize the describer with its properties.
        try {
            String sceneDescription = Files.readString(Path.of(PATH + this.sceneFilePath));
            this.sceneDescriptor.initializeFromJsonObject(sceneDescription);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //inserts the props to the scene object.
        scene.setName(this.sceneDescriptor.getSceneAttributes().get("name"))
                .setBackground(getColorFromMap(this.sceneDescriptor.getSceneAttributes(), "background"))
                .setAmbientLight(
                        new AmbientLight(getColorFromMap(this.sceneDescriptor.getAmbientLightAttributes(), "color"),
                                stringToDouble3(this.sceneDescriptor.getAmbientLightAttributes().get("Ka"))))
                .setGeometries(getGeometriesFormDesc());

        return scene;
    }





    private Geometries getGeometriesFormDesc() {
        Geometries geometries = new Geometries();
        geometries.addAll(getTriangles(), getSpheres());
        return geometries;
    }

    private List<Intersectable> getSpheres() {
        List<Intersectable> spheres = new LinkedList<>();
        for (var sphereMap : this.sceneDescriptor.getSpheres()) {
            Sphere sphere = getSphereFromMap(sphereMap);
            sphere.setMaterial(getMaterialFromMap(sphereMap));
            sphere.setEmission(getEmissionFromMap(sphereMap));
            spheres.add(sphere);
        }
        return spheres;
    }

    /**
     * gets the triangle from the map of hte sphere.
     * @param sphereMap the sphere map
     * @return the sphere object.
     */
    private Sphere getSphereFromMap(Map<String, String> sphereMap) {
        return new Sphere(getPointFromMap(sphereMap, "center"),
                            getDoubleFromMap(sphereMap,"radius"));
    }

    /**
     * gets a list of map containing the props of triangles
     *
     * @return the list object.
     */
    private List<Intersectable> getTriangles() {
        List<Intersectable> triangles = new LinkedList<>();
        for (var triangleMap : this.sceneDescriptor.getTriangles()) {
            Triangle triangle = getTriangleFromMap(triangleMap);
            triangle.setMaterial(getMaterialFromMap(triangleMap));
            triangle.setEmission(getEmissionFromMap(triangleMap));
            triangles.add(triangle);
        }
        return triangles;
    }

    /**
     * gets the triangle object form the map.
     *
     * @param triangleMap the map containing the properties of the triangles
     * @return the triangle object.
     */
    private Triangle getTriangleFromMap(Map<String, String> triangleMap) {
        return new Triangle(
                getPointFromMap(triangleMap, "p0"),
                getPointFromMap(triangleMap, "p1"),
                getPointFromMap(triangleMap, "p2")
        );
    }

    /**
     * returns material from a map with its properties.
     *
     * @param emissionMap the map containing the properties of the emission,
     * @return the emission color.
     */
    private Color getEmissionFromMap(Map<String, String> emissionMap) {
        return new Color(stringToDouble3(emissionMap.get("color")));
    }

    /**
     * returns material from a map with its properties.
     *
     * @param materialMap the map containing the properties of a material.
     * @return the material object.
     */
    private Material getMaterialFromMap(Map<String, String> materialMap) {
        return new Material()
                .setKs(stringToDouble3(materialMap.get("kS")))
                .setKd(stringToDouble3(materialMap.get("kD")))
                .setShininess(getIntFromMap(materialMap, "nShininess"))
                .setKr(stringToDouble3(materialMap.get("kR")))
                .setKt(stringToDouble3(materialMap.get("kT")));

    }

    /**
     * returns the background from the scene.
     *
     * @param map the map to extract the
     * @return the background of the scene.
     */
    private Color getColorFromMap(Map<String, String> map, String key) {
        Double3 color = stringToDouble3(map.get(key));
        return new Color(color);
    }

    /**
     * returns the background from the scene.
     *
     * @param map the map to extract the
     * @return the background of the scene.
     */
    private Point getPointFromMap(Map<String, String> map, String key) {
        Double3 point = stringToDouble3(map.get(key));
        return new Point(point);
    }

    /**
     * returns the double value from a map.
     *
     * @param map the map to extract the
     * @return the double value from the map.
     */
    private double getDoubleFromMap(Map<String, String> map, String key) {
        return Double.parseDouble(map.get(key));
    }

    /**
     * returns the int value from the scene.
     *
     * @param map the map to extract the
     * @return the int from the map.
     */
    private int getIntFromMap(Map<String, String> map, String key) {
        return Integer.parseInt(map.get(key));
    }

    /**
     * retuens a double3 from a string in a double3 format.
     *
     * @param double3 the String
     * @return the double3 it represents.
     */
    private Double3 stringToDouble3(String double3) {
        String numbers[] = double3.split(" ");
        return new Double3(
                Double.parseDouble(numbers[0]),
                Double.parseDouble(numbers[1]),
                Double.parseDouble(numbers[2]));
    }

}







