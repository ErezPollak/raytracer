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

    public Sphere(double raduis) {
        this.raduis = raduis;
    }

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
