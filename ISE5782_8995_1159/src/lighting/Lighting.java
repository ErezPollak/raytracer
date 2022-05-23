package lighting;

import geometries.Intersectable;
import primitives.Color;
import primitives.Point;
import scene.Scene;

/**
 * the abstract information about a light source.
 */
abstract class Lighting {
    /**
     * the property every light source has: the color.
     */
    private Color intensity;

    /**
     * ctor for initializing the intensity.
     * @param intensity the initialized intensity.
     */
    protected Lighting(Color intensity){
        this.intensity = intensity;
    }

    /**
     * @return the intensity of the object.
     */
    public Color getIntensity() {
        return this.intensity;
    }

}
