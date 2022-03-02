package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry {
    protected  double raduis;
    protected Ray ray;

    public Vector getNormal() {
        return null;
    }

    /**
     * Tube Constructor
     * @param raduis
     * @param ray
     */
    public Tube(double raduis, Ray ray) {
        this.raduis = raduis;
        this.ray = ray;
    }

    /**
     * Get raduis of tube.
     * @return
     */
    public double getRaduis() {
        return raduis;
    }

    /**
     * Get ray of tube.
     * @return
     */
    public Ray getRay() {
        return ray;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "raduis=" + raduis +
                ", ray=" + ray +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
