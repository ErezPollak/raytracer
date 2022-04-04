 /**
 * the Point class
 *
 * written by Erez Polak
 * and Eliran Salama
 */


package primitives;

import java.util.List;
import java.util.Objects;

public class Ray {

    private final Point point;
    private final Vector vector;

    /**
     * Ray Constructor
     * @param v
     * @param p
     */
    public Ray(Point p , Vector v){
        point = p;
        vector = v.normalize();
    }

    /**
     * Get ray's point
     * @return
     */
    public Point getPoint() {
        return point;
    }


    /**
     * Get ray's vector
     * @return
     */
    public Vector getVector() {
        return vector;
    }

    /**
     * returns the Point on the ray that is given by the scaling of the vector by the parameter t.
     * @param t the parameter that needed for scaling the vector.
     * @return the result point.
     */
    public Point getPoint(double t){
        return this.point.add(this.vector.normalize().scale(t));
    }

    /**
     * return the closest point from the given list the point that starts the ray.
     * @param pointList the given list
     * @return the point
     */
    public Point findClosestPoint(List<Point> pointList){

        if(pointList == null || pointList.size() == 0){
            return null;
        }

        //initializing the first values for comperation.
        Point closestPoint = pointList.get(0);
        double distance = pointList.get(0).distance(this.point);

        //iterating over the list.
        for(Point p : pointList){
            if(p.distance(this.point) < distance){
                closestPoint = p;
            }
        }

        //returning the result
        return closestPoint;
    }


    /**
     * return a string with the status of the object.
     * @return
     */
    @Override
    public String toString() {
        return "Ray{" +
                "point=" + point +
                ", vector=" + vector +
                '}';
    }

    /**
     * returns the equal of the point and the vector.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return point.equals(ray.point) && vector.equals(ray.vector);
    }

}
