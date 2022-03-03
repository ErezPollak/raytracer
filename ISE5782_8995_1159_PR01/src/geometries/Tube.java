package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry {
    double raduis;
    Ray ray;

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    public Tube(double raduis) {
        this.raduis = raduis;
    }

    public double getRaduis() {
        return raduis;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "raduis=" + raduis +
                ", ray=" + ray +
                '}';
    }
}
