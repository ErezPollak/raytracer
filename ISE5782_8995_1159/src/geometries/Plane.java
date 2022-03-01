/**
 * the Point class
 *
 * written by Erez Polak
 * and Eliran Salama
 */


package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{

    private Point q0;
    private Vector normal;

    /**
     * sets the plane according to the Point and normal vector.
     * @param q0
     * @param normal
     */
    public Plane(Point q0 , Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * sets the plane according to three points.
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1 , Point p2 , Point p3) {
        this.q0 = p1;
        this.normal = null;
    }

    /**
     * returns the point.
     * @return
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * returns the normal vector.
      * @param p
     * @return
     */
    @Override
    public Vector getNormal(Point p) {
        return this.getNormal();
    }

    /**
     * returns a string contains the status of the plane.
     * @return
     */
    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }

    /**
     * not required of the project only because of the implementation in the Main function.
     * @return
     */
    public Vector getNormal() {
        return this.normal;
    }


}

