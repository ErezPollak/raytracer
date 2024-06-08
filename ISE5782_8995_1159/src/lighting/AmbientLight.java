package lighting;

import org.json.JSONObject;
import primitives.Double3;
import primitives.Color;

/**
 * Class for Ambient light.
 */
public class AmbientLight extends Lighting {

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


//    public static AmbientLight fromJson(JSONObject jsonObject) {
//        Color color = (Color) Color.fromJson((JSONObject) jsonObject.get("color"));
//        Object ka = jsonObject.get("Ka");
//        AmbientLight ambientLight;
//        if (ka instanceof Double) {
//            ambientLight = new AmbientLight(color, (double) ka);
//        } else if (ka instanceof JSONObject) {
//            Double3 KaDouble3 = (Double3) Double3.fromJson((JSONObject) ka);
//            ambientLight = new AmbientLight(color, KaDouble3);
//        }else{
//            System.out.println("didnot find Ka puting 1");
//            ambientLight = new AmbientLight(color, 1);
//        }
//        return ambientLight;
//
//    }

}
