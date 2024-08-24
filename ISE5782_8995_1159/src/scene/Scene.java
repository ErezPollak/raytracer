package scene;

import geometries.Geometries;
import json.JSONable;
import lighting.AmbientLight;
import lighting.LightSource;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import primitives.Color;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static common.Utils.getSubclassesOf;
import static json.Utils.copyProps;
import static json.Utils.getJsonObjects;


/**
 * Class for scene
 */
public class Scene extends JSONable {
    public String name;
    public Color background;
    public AmbientLight ambientLight;
    public Geometries geometries;
    public List<LightSource> lights;


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

    public Scene(JSONObject jsonObject) {
        super(jsonObject);
        this.initObject(this);
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


    @Override
    public Map<Schema, Function<JSONObject, ? extends Object>> getCreationMap() {
        List<Class<?>> allSubClasses = getSubclassesOf(LightSource.class);
        return Map.of(
                SchemaLoader.load(new JSONObject(
                        "{" +
                                "   \"$schema\": \"Sphere\"," +
                                "   \"type\": \"object\"," +
                                "   \"properties\": {" +
                                "       \"name\": {" +
                                "           \"type\": \"string\"," +
                                "       }," +
                                "       \"background\": {" +
                                "           \"type\": \"object\"," +
                                "       }," +
                                "       \"ambientLight\": {" +
                                "           \"type\": \"object\"," +
                                "       }," +
                                "       \"geometries\": {" +
                                "           \"type\": \"object\"," +
                                "       }," +
                                "       \"lights\": {" +
                                "           \"type\": \"object\"," +
                                "           \"minProperties\": 1," +
                                "           \"properties\": {" + allSubClasses.stream().map(cls ->
                                "               \"" + cls.getSimpleName().toLowerCase() + "\": {" +
                                        "                   \"type\": \"array\"," +
                                        "                   \"minItems\": 1," +
                                        "                   \"items\": { " +
                                        "                       \"type\": \"object\"" +
                                        "                   }," +
                                        "               },").collect(Collectors.joining()) +
                                "           }," +
                                "           additionalProperties: false" +
                                "       }," +
                                "   }," +
                                "   \"required\": [" +
                                "      \"name\"" +
                                "   ], additionalProperties: false" +
                                "}")),
                json -> {
                    Scene scene = new Scene(json.getString("name"));
                    if (json.has("background")) {
                        scene.setBackground(new Color(json.getJSONObject("background")));
                    }
                    if (json.has("ambientLight")) {
                        scene.setAmbientLight(new AmbientLight(json.getJSONObject("ambientLight")));
                    }
                    if (json.has("geometries")) {
                        scene.setGeometries(new Geometries(json.getJSONObject("geometries")));
                    }
                    if (json.has("lights")) {
                        JSONObject lights = json.getJSONObject("lights");
                        scene.setLights(
                                allSubClasses.stream()
                                        .filter(cls -> lights.has(cls.getSimpleName().toLowerCase()))
                                        .map(cls -> getJsonObjects(lights.getJSONArray(cls.getSimpleName().toLowerCase()))
                                                .stream()
                                                .map(obj -> {
                                                    try {
                                                        return (LightSource) cls.getConstructor(JSONObject.class).newInstance(obj);
                                                    } catch (InstantiationException | InvocationTargetException |
                                                             IllegalAccessException | NoSuchMethodException _) {
                                                        //TODO log did not success
                                                        return null;
                                                    }
                                                })
                                                .filter(lightSource -> lightSource != null))
                                        .flatMap(stream -> stream).toList()
                        );
                    }
                    return scene;
                }

        );
    }
}
