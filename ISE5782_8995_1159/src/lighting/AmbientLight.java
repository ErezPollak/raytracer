package lighting;

import primitives.Color;
import primitives.Double3;

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


}
