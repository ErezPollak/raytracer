package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

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

        if(ray.getPoint().equals(this.center)){
            return List.of(ray.getPoint(this.raduis));
        }

        Point p0 = ray.getPoint();
        Vector v = ray.getVector();
        Vector u = center.subtract(p0);
        double tm = u.dotProduct(v);
        double d = alignZero(Math.sqrt(u.lengthSquared()-(tm*tm)));
        if(d>raduis)
            return null; //there are no intersection points
        double th = alignZero(Math.sqrt((raduis*raduis)-(d*d)));
        double t1 = alignZero(tm-th);
        double t2 = alignZero(tm + th);
        if(t1 >0 && t2 >0){
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            return List.of(p1,p2);
        }
        if(t1>0){
            return List.of(ray.getPoint(t1));
        }
        if(t2>0){
            return List.of(ray.getPoint(t2));
        }
        return null; // 0 points
    }

}
