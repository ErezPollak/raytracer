package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere implements Geometry {
    double raduis;
    Point point;

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    /**
     * Sphere Constructor
     * @param raduis
     */
    public Sphere(double raduis) {
        this.raduis = raduis;
    }

    /**
     * Get raduis of sphere
     * @return
     */
    public double getRaduis() {
        return raduis;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "raduis=" + raduis +
                ", point=" + point +
                '}';
    }
}
