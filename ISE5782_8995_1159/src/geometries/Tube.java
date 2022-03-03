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
        //calculate the scale in witch the ray suppose to be multiplied in order to get the
        // length to the ray point that is on the same line with the normal.
        double scale = ray.getVector().dotProduct(p.subtract(ray.getPoint()));

        //calculate the scaled point on the ray by adding the scaled ray to the starting point of the ray.
        Point rayPoint = ray.getPoint().add(ray.getVector().scale(scale));

        //returns the subtraction as the normal vector.
        return p.subtract(rayPoint).normalize();
    }
}
