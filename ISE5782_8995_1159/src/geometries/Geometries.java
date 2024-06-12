package geometries;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import primitives.Ray;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static common.Utils.getSubclassesOf;
import static json.Utils.getJsonObjects;

/**
 * A class that represents all kinds of geometries
 * that implement the interface of finding points of intersections with a ray
 * using the composite design pattern
 */
public class Geometries extends Intersectable {
    private List<Intersectable> listOfGeometries;

    /**
     * Default constructor for Geometries
     */
    public Geometries() {
        listOfGeometries = new LinkedList();
    }

    /**
     * Constructor for Geometries
     *
     * @param geometries
     */
    public Geometries(Intersectable... geometries) {
        this();
        add(geometries);
    }

    public Geometries(JSONObject jsonObject) {
        super(jsonObject);
        Geometries geometries = this.getJsonCreatedInstance(this.getClass());
        this.listOfGeometries = geometries.listOfGeometries;
    }

    /**
     * Add geometry to the collection
     *
     * @param geometries the list that hte function receive.
     */
    public void add(Intersectable... geometries) {
        //adding all the given geos to the list.
        for (var item : geometries) {
            listOfGeometries.add(item);
        }
    }

    public void addAll(List<Intersectable>... lists) {
        //adding all the given geos to the list.
        for (var list : lists) {
            for (var item : list) {
                listOfGeometries.add(item);
            }
        }
    }

    public List<Intersectable> getListOfGeometries() {
        return listOfGeometries;
    }

    /**
     * Finds all the intersections of the given ray with
     * the geometries ,and return a list of those points of intersection.
     *
     * @param ray
     * @return
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = null; // Create empty list of the points
        for (var item : listOfGeometries) {
            List<GeoPoint> itemPoints = item.findGeoIntersections(ray); // For each geometry, find it intersection with the given ray,
            if (itemPoints != null) {                               // and create a list of them.
                if (result == null) {
                    result = new LinkedList(); // If the list of all intersections with all geometries is empty, create the list.
                }
                result.addAll(itemPoints); // If there are intersections, add all the intersections of the geometrie to the list
            }                              // of all intersections with all geometries.
        }
        return result;
    }



    @Override
    public Map<Schema, Function<JSONObject, ? extends Object>> getCreationMap() {
        List<Class<?>> allSubClasses = getSubclassesOf(Geometry.class);
        return Map.of(
                SchemaLoader.load(new JSONObject(
                        "{" +
                                "   \"$schema\": \"Sphere\"," +
                                "   \"type\": \"object\"," +
                                "    \"minProperties\": 1," +
                                "   \"properties\": {" + allSubClasses.stream().map(cls ->
                                "      \"" + cls.getSimpleName().toLowerCase() + "\": {" +
                                "          \"type\": \"array\"," +
                                "          \"minItems\": 1," +
                                "          \"items\": { " +
                                "               \"type\": \"object\"" +
                                "           }," +
                                "      },").collect(Collectors.joining()) +
                                "   }," +
                                "  additionalProperties: false" +
                                "}")),
                json -> allSubClasses.stream()
                        .filter(cls -> json.has(cls.getSimpleName().toLowerCase()))
                        .map(cls -> getJsonObjects(json.getJSONArray(cls.getSimpleName().toLowerCase()))
                                .stream()
                                .map(obj -> {
                                    try {
                                        return (Intersectable) cls.getConstructor(JSONObject.class).newInstance(obj);
                                    } catch (InstantiationException | InvocationTargetException |
                                             IllegalAccessException | NoSuchMethodException _) {
                                        //TODO log did not success
                                        return null;
                                    }
                                })
                                .filter(intersectable -> intersectable != null))
                        .flatMap(stream -> stream)
                        .toArray(Intersectable[]::new)
        );
    }

}
