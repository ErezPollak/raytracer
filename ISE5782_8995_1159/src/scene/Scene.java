package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Class for scene
 */
public class Scene {
    public String name;
    public Color background;
    public AmbientLight ambientLight;
    public Geometries geometries;
    public List<LightSource> lights;

    private static final Logger logger = Logger.getLogger(Scene.class.getName());


    /**
     * Scene constructor that gets the scene name and restart the scene fields to be default.
     *
     * @param sceneName
     */
    public Scene(String sceneName) {
        name = sceneName;
        background = Color.BLACK;
        ambientLight = new AmbientLight();
        geometries = new Geometries();
        lights = new LinkedList<>();
    }

    /**
     * set for ambient light, and return the object itself.
     *
     * @param ambientLight
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {

        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * set for background, and return the object itself.
     *
     * @param background
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * set for geometries, and return the object itself.
     *
     * @param geometries
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * set name, and return the object itself.
     *
     * @param name
     */
    public Scene setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * add another light source to the list.
     *
     * @param lights the light source to add.
     * @return the updated scene.
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

//
//    public static Scene fromJson(JSONObject jsonObject) {
//
//        logger.info("got scene to parse");
//
//        String name = (String) jsonObject.get("name");
//        Color background = (Color) Color.fromJson((JSONObject) jsonObject.get("background"));
//        AmbientLight ambientLight = AmbientLight.fromJson((JSONObject) jsonObject.get("ambientLight"));
//        //Geometries geometries = Geometries.fromJson((JSONObject) jsonObject.get("geometries"));
//
//
//        Scene scene = new Scene(name).setBackground(background).setAmbientLight(ambientLight);
//        return scene;
//    }


}
