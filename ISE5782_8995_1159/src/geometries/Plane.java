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
        //if two points are the same
        if(p1.equals(p2) || p1.equals(p3) || p2.equals(p3)){
            throw new IllegalArgumentException();
        }

        //if the three points on the same line.
        if(p1.subtract(p2).normalize().equals(p1.subtract(p3)) || p1.subtract(p2).normalize().equals(p1.subtract(p3).scale(-1))){
            throw new IllegalArgumentException();
        }
        
        this.q0 = p1;
        //normal calculation cross product between two vectors created by the three given point.
        this.normal = (p1.subtract(p2)).crossProduct(p1.subtract(p3));
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
        return this.normal.normalize();
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
     * not required as a part of the requirements
     * it is written only because of the implementation in the Polygon class.
     * @return
     */
    public Vector getNormal() {
        return this.normal;
    }


}

