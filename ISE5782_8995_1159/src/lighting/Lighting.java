package lighting;

import json.JSONable;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import primitives.Color;

import static json.Utils.validate;

/**
 * the abstract information about a light source.
 */
abstract class Lighting extends JSONable {


    /**
     * the property every light source has: the color.
     */
    protected Color intensity;


    public Lighting(JSONObject jsonObject) {
        super(jsonObject);
        //initFromJson(jsonObject);
    }

    /**
     * ctor for initializing the intensity.
     *
     * @param intensity the initialized intensity.
     */
    protected Lighting(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * @return the intensity of the object.
     */
    public Color getIntensity() {
        return this.intensity;
    }
//
//    public void initFromJson(JSONObject jsonObject) {
//        validate(SchemaLoader.load(new JSONObject(
//                "{" +
//                        "   \"$schema\": \"Sphere\"," +
//                        "   \"type\": \"object\"," +
//                        "   \"properties\": {" +
//                        "       \"intensity\": {" +
//                        "           \"type\": \"object\"," +
//                        "       }" +
//                        "   }," +
//                        "   \"required\": [" +
//                        "      \"intensity\"" +
//                        "   ]" +
//                        "}")), jsonObject);
//        this.intensity = new Color(jsonObject.optJSONObject("intensity"));
//    }
}
