package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import static primitives.Util.*;

import java.util.List;

public class Sphere extends Geometry {
    double radius;
    Point center;

    @Override
    public Vector getNormal(Point p) {
        // the subtraction of the point from the center, returns the normal to the point.
        return p.subtract(center).normalize();
    }

    /**
     * Sphere Constructor
     *
     * @param radius
     * @param point
     */
    public Sphere(Point point, double radius) {
        this.radius = radius;
        this.center = point;
    }

    /**
     * Get radius of sphere
     *
     * @return
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Get point of sphere.
     * @return the center of the sphere.
     */
    public Point getPoint() {
        return center;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "radius=" + radius +
                ", point=" + center +
                '}';
    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // if ray starts at the center, return intersection point (create vector 0)
        if (ray.getPoint().equals(this.center)) {
            return List.of(new GeoPoint(this ,ray.getPoint(this.radius)));
        }

        // create p0, vector from p0 and vector from center to p0
        Point p0 = ray.getPoint();
        Vector v = ray.getVector();
        Vector u = center.subtract(p0);

        // calculate length between p0 and the point in the same line with the center and on our vector
        // calculates the distance between the center and the previous point
        double tm = u.dotProduct(v);
        double d = alignZero(Math.sqrt(u.lengthSquared() - (tm * tm)));

        // if d >= radius so the ray is outside from the sphere and there are no intersections
        if (alignZero(radius - d) <= 0.0)
            return null; //there are no intersection points

        //calculates the distance between p0 and the 2 potentials points
        double th = alignZero(Math.sqrt((radius * radius) - (d * d)));
        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);

        // if both distances are positive, it means we have 2 intersections
        if (t1 > 0 && t2 > 0) {
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            return List.of(new GeoPoint(this ,p1), new GeoPoint(this ,p2));
        }

        // if t1 only positive so there is only 1 intersection with t1 distance
        if (t1 > 0) {
            return List.of(new GeoPoint(this ,ray.getPoint(t1)));
        }

        // if t2 only positive so there is only 1 intersection with t1 distance
        if (t2 > 0) {
            return List.of(new GeoPoint(this ,ray.getPoint(t2)));
        }

        return null; // 0 points
    }

}
