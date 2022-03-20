package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Sphere implements Geometry {
    double raduis;
    Point center;

    @Override
    public Vector getNormal(Point p) {
        // the subtraction of the point from the center, returns the normal to the point.
        return p.subtract(center).normalize();
    }

    /**
     * Sphere Constructor
     * @param raduis
     * @param point
     */
    public Sphere(double raduis ,Point point) {
        this.raduis = raduis;
        this.center = point;
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
        return center;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "raduis=" + raduis +
                ", point=" + center +
                '}';
    }

    /**
     * Finds the intersections of the ray with the sphere
     * @param ray
     * @return
     */
    public List<Point> findIntersections(Ray ray)
    {
        return null;
    }
}
