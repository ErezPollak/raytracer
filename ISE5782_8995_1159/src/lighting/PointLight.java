package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 *
 */
public class PointLight extends Lighting implements LightSource{

    /**
     * the location of the lightSource.
     */
    private Point position;

    /**
     * the coefficients for the point light.
     * kC: the general coefficient to know what part of the light ot take.
     * kL: the linear coefficient for the linear weakening  of the light
     * kQ: the squared coefficient for the squared weakening of the light.
     */
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     * ctor for initializing the intensity.
     *
     * @param intensity the initialized intensity.
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * the impact of the point light on a point on an object.
     * @param p the point on the geometry.
     * @return the light of the point from the light source.
     */
    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity().reduce(this.kC + this.kL * p.distance(this.position) + this.kQ * p.distanceSquared(this.position));
    }

    /**
     * returns the direction to the lightened point from the light source.
     * @param p the point on the geometry.
     * @return the direction.
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(this.position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return this.position.distance(point);
    }

}
