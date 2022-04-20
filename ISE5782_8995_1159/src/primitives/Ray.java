 /**
 * the Point class
 *
 * written by Erez Polak
 * and Eliran Salama
 */


package primitives;

 import geometries.Intersectable.GeoPoint;
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
     * @param points the given list
     * @return the point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }


    /**
     * return the closest point from the given list the point that starts the ray.
     * @param pointList the given list of geoPoints.
     * @return the closest point to the head of the ray.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> pointList){

        if(pointList == null || pointList.size() == 0){
            return null;
        }

        //initializing the first values for comperation.
        GeoPoint closestGeoPoint = pointList.get(0);
        double distance = pointList.get(0).point.distance(this.point);

        //iterating over the list.
        for(GeoPoint p : pointList){
            if(p.point.distance(this.point) < distance){
                distance = p.point.distance(this.point);
                closestGeoPoint = p;
            }
        }

        //returning the result
        return closestGeoPoint;
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
