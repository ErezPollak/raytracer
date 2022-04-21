package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 *
 */
public class SpotLight extends PointLight implements LightSource {

    /**
     * the direction in witch the light is strongest.
     */
    private Vector direction;

    /**
     * the ctor to initialize the SpotLight.
     * @param intensity the color of the light.
     * @param position the position of the light.
     * @param direction the direction of the light.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * the impact of the spotlight on a point on an object.
     * @param p the point on the geometry.
     * @return the light of the point from the light source.
     */
    @Override
    public Color getIntensity(Point p) {
        //calculates the degree between the direction of the light source to the direction of the light source to the point.
        double degree = this.direction.dotProduct(super.getL(p));
        if(degree < 0) return Color.BLACK;
        return super.getIntensity(p).scale(degree);
    }

    /**
     * empty for now.
     * @param i
     * @return
     */
    public SpotLight setNarrowBeam(int i) {
        return this;
    }
}
