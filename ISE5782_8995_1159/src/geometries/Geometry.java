package geometries;

import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

import static json.Utils.validate;


/**
 * the class that represent a geometry.
 */
public abstract class Geometry extends Intersectable {

    public Geometry() {
    }

    public Geometry(JSONObject jsonObject) {
        super(jsonObject);
        initFromJson(jsonObject);
    }

    /**
     * the light that every geometry should emit.
     * initialized to black.
     */
    protected Color emission = Color.BLACK;

    /**
     * holds the properties of the geometry.
     */
    private Material material = new Material();

    /**
     * Get normal of the geometry in a given point.
     *
     * @param p the point to look for the normal in.
     * @return the normal vector of the Geometry in the given point.
     */
    public abstract Vector getNormal(Point p);

    /**
     * @return the emitted light property.
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * setting the color and returning the object itself for more initializations.
     *
     * @param emission the new Emission color of the geometry.
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * returns the material propertied.
     *
     * @return the material.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * setting the material to a new one.
     *
     * @param material the new material.
     * @return the object with the material updated.
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public void initFromJson(JSONObject jsonObject) {
        validate(SchemaLoader.load(new JSONObject(
                "{" +
                        "   \"$schema\": \"Sphere\"," +
                        "   \"type\": \"object\"," +
                        "   \"properties\": {" +
                        "       \"geometry\": {" +
                        "           \"type\": \"object\"," +
                        "           \"minProperties\": 1," +
                        "           \"properties\": {" +
                        "                \"emission\": {" +
                        "                    \"type\": \"object\"" +
                        "                }," +
                        "                \"material\": {" +
                        "                    \"type\": \"object\"" +
                        "                }," +
                        "           }, additionalProperties: false" +
                        "      }" +
                        "   }" +
                        "}")), jsonObject);
        if (jsonObject.has("geometry")) {
            JSONObject geometryJson = jsonObject.getJSONObject("geometry");
            if (geometryJson.has("material")) {
                this.setMaterial(new Material(geometryJson.optJSONObject("material")));
            }
            if (geometryJson.has("emission")) {
                this.setEmission(new Color(geometryJson.optJSONObject("emission")));
            }
        }
    }

}
