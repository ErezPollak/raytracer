package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 *
 */
public class DirectionalLight extends Lighting implements LightSource{

    /**
     * the direction of the light.
     */
    private Vector direction;

    /**
     * ctor for initializing the intensity.
     *
     * @param intensity the initialized intensity.
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * returns the light of the directional light on a point on an object.
     * @param p the point on the geometry.
     * @return the light on the object.
     */
    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    /**
     * the direction of the light.
     * @param p the point on the geometry.
     * @return the direction of the light.
     */
    @Override
    public Vector getL(Point p) {
        return this.direction.normalize();
    }
}
