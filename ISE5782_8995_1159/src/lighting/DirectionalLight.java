package lighting;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.Map;
import java.util.function.Function;

/**
 *
 */
public class DirectionalLight extends Lighting implements LightSource {

    /**
     * the direction of the light.
     */
    private Vector direction;

    public DirectionalLight(JSONObject jsonObject) {
        super(jsonObject);
        DirectionalLight directionalLight = this.getJsonCreatedInstance(this.getClass());
        this.direction = directionalLight.direction;
        this.intensity = directionalLight.intensity;
    }

    /**
     * ctor for initializing the intensity.
     *
     * @param intensity the initialized intensity.
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    public Vector getDirection() {
        return direction;
    }

    /**
     * returns the light of the directional light on a point on an object.
     *
     * @param p the point on the geometry.
     * @return the light on the object.
     */
    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    /**
     * the direction of the light.
     *
     * @param p the point on the geometry.
     * @return the direction of the light.
     */
    @Override
    public Vector getL(Point p) {
        return this.direction.normalize();
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public Map<Schema, Function<JSONObject, ? extends Object>> getCreationMap() {
        return Map.of(
                SchemaLoader.load(new JSONObject(
                        "{" +
                                "   \"$schema\": \"Sphere\"," +
                                "   \"type\": \"object\"," +
                                "   \"properties\": {" +
                                "       \"intensity\": {" +
                                "           \"type\": \"object\"," +
                                "       }," +
                                "       \"direction\": {" +
                                "           \"type\": \"object\"," +
                                "       }" +
                                "   }," +
                                "   \"required\": [" +
                                "      \"intensity\",\"direction\"" +
                                "   ], additionalProperties: false" +
                                "}")),
                json -> new Object[]{new Color(json.optJSONObject("intensity")),
                        new Vector(json.getJSONObject("direction"))}
        );
    }
}
