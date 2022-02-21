package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{

    private Point q0;
    private Vector normal;

    /**
     * Plane Constructor
     * @param q0
     * @param v
     */
    public Plane(Point q0 , Vector v) {
        this.q0 = q0;
        this.normal = v.normalize();
    }

    /**
     * Plane Constructor
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1 , Point p2 , Point p3) {
        this.q0 = p1;
        this.normal = null;
    }

    /**
     * Get point of plane
     * @return
     */
    public Point getQ0() {
        return q0;
    }

    public Vector getNormalVector() {
        return normal;
    }

    @Override
    public Vector getNormal(Point p) {
        return this.normal;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }

    public Vector getNormal() {
        return this.normal;
    }


}

