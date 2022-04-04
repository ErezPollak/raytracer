package lighting;

import primitives.Double3;
import primitives.Color;

/**
 * Class for Ambient light.
 */
public class AmbientLight {
    Color intensity;

    /**
     * Constructor that gets the color power and get the Discount factor
     * and calculate the intensicty.
     * @param Ia
     * @param kA
     */
    public AmbientLight(Color Ia, Double3 kA){
        intensity = Ia.scale(kA);
    }

    /**
     * Defaulte construction - restart the intensity to black.
     */
    public AmbientLight(){
        intensity =  Color.BLACK;
    }

    /**
     * return the intensity of the ambient light.
     * @return
     */
    public Color getIntensity() {
        return intensity;
    }
}
