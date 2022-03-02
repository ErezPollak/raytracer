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
     * @param point
     */
    public Sphere(double raduis ,Point point) {
        this.raduis = raduis;
        this.point = point;
    }

    /**
     * Get raduis of sphere
     * @return
     */
    public double getRaduis() {
        return raduis;
    }

    /**
     * Get point of sphere
     * @return
     */
    public Point getPoint() {
        return point;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "raduis=" + raduis +
                ", point=" + point +
                '}';
    }
}
