package lighting;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import primitives.Color;
import primitives.Double3;
import primitives.Vector;

import java.util.Map;
import java.util.function.Function;

/**
 * Class for Ambient light.
 */
public class AmbientLight extends Lighting {

    public AmbientLight(JSONObject jsonObject) {
        super(jsonObject);
        AmbientLight ambientLight = this.getJsonCreatedInstance(this.getClass());
        this.intensity = ambientLight.intensity;
    }

    /**
     * Constructor that gets the color power and get the Discount factor
     * and calculate the intensity.
     *
     * @param Ia the intensity of the light.
     * @param kA the officiant of the light.
     */
    public AmbientLight(Color Ia, Double3 kA) {
        super(Ia.scale(kA));
    }

    /**
     * Constructor that gets the color power and get the Discount factor
     * and calculate the intensity.
     *
     * @param Ia the intensity of the light.
     * @param kA the officiant of the light.
     */
    public AmbientLight(Color Ia, double kA) {
        super(Ia.scale(kA));
    }

    /**
     * Default construction - restart the intensity to black.
     */
    public AmbientLight() {
        super(Color.BLACK);
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
                                "       \"officiant\": {" +
                                "           \"type\": \"object\"," +
                                "       }" +
                                "   }," +
                                "   \"required\": [" +
                                "      \"intensity\",\"officiant\"" +
                                "   ], additionalProperties: false" +
                                "}")),
                json -> new Object[]{new Color(json.optJSONObject("intensity")), new Double3(json.getJSONObject("officiant"))},

                SchemaLoader.load(new JSONObject(
                        "{" +
                                "   \"$schema\": \"Sphere\"," +
                                "   \"type\": \"object\"," +
                                "   \"properties\": {" +
                                "       \"intensity\": {" +
                                "           \"type\": \"object\"," +
                                "       }," +
                                "       \"officiant\": {" +
                                "           \"type\": \"number\"," +
                                "       }" +
                                "   }," +
                                "   \"required\": [" +
                                "      \"intensity\",\"officiant\"" +
                                "   ], additionalProperties: false" +
                                "}")),
                json -> new Object[]{new Color(json.optJSONObject("intensity")), json.getDouble("officiant")}


        );
    }

}
