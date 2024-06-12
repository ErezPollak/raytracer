package lighting;

import json.JSONable;
import org.json.JSONObject;
import primitives.Color;

/**
 * the abstract information about a light source.
 */
abstract class Lighting{


    /**
     * the property every light source has: the color.
     */
    private Color intensity;

//
//    public Lighting() {
//    }
//
//    public Lighting(JSONObject jsonObject) {
//        super(jsonObject);
//
//    }


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

}
