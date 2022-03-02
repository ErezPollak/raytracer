package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube implements Geometry {
    double high;

    /**
     * Cylinder Constructor
     * @param high
     */
    public Cylinder(double raduis, Ray ray, double high) {
        super(raduis, ray);
        this.high = high;
    }

    /**
     * Get high of cylinder
     * @return
     */
    public double getHigh() {
        return high;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "high=" + high +
                '}';
    }
}
