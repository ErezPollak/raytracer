package geometries;

import primitives.Point;
import primitives.Vector;

public class Cylinder implements Geometry {
    double high;

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    public Cylinder(double high) {
        this.high = high;
    }

    public double getHigh() {
        return high;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "high=" + high +
                '}';
    }
}
