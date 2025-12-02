package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Triangle extends Polygon {

    /**
     * takes three points and makes a triangle out of them.
     *
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }
//
//    /**
//     * Finds the intersections of the ray with the Triangle
//     * @param ray the ray that suppose to go through the triangle.
//     * @return A list with the intersection point.
//     */
//    @Override
//    public List<Point> findIntersections(Ray ray)
//    {
//
//        //implementation in the algebraic way.
//
//        List<Point> planeIntersection = this.plane.findIntersections(ray);
//
//        //if there is no crossing with the plane of course there is no crossing of the triangle.
//        if(planeIntersection == null){
//            return null;
//        }
//
//        Point intersectionPoint = planeIntersection.get(0);
//
//        //the main vector from the point of the ray to the intersection point.
//        Vector v = ray.getPoint().subtract(intersectionPoint);
//
//        //teh vectors from the point of the ray to all the vertexes of the triangle.
//        // creating a pyramid with the base of the triangle and the ray.
//        Vector v1 = this.vertices.get(0).subtract(ray.getPoint());
//        Vector v2 = this.vertices.get(1).subtract(ray.getPoint());
//        Vector v3 = this.vertices.get(2).subtract(ray.getPoint());
//
//        //the normals of the pyramid planes.
//        Vector n1 = v1.crossProduct(v2);
//        Vector n2 = v2.crossProduct(v3);
//        Vector n3 = v3.crossProduct(v1);
//
//        //the values of the dot product between the normals of the pyramids planes.
//        //represents the place of the point relative to them (above or under).
//        double d1 = v.dotProduct(n1);
//        double d2 = v.dotProduct(n2);
//        double d3 = v.dotProduct(n3);
//
//        //checks if the point is above all the planes, it means that the point is inside the triangle
//        //otherwise it is not.
//        if((d1 <= 0 && d2 <= 0 && d3 <= 0)||(d1 >= 0 && d2 >= 0 && d3 >= 0)){
//            return planeIntersection;
//        }
//
//        //else returns null.
//        return null;
//    }

    /**
     * @param ray the ray that crosses the polygon.
     * @return
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {

        //implementation in the algebraic way.
        List<GeoPoint> planeIntersection = this.plane.findGeoIntersections(ray);

        //if there is no crossing with the plane of course there is no crossing of the triangle.
        if (planeIntersection == null) {
            return null;
        }

        Point intersectionPoint = planeIntersection.get(0).point;

        if (intersectionPoint.equals(ray.getPoint()))
            return null;

        //the main vector from the point of the ray to the intersection point.
        Vector v = ray.getPoint().subtract(intersectionPoint);

        //teh vectors from the point of the ray to all the vertexes of the triangle.
        // creating a pyramid with the base of the triangle and the ray.
        Vector v1 = this.vertices.get(0).subtract(ray.getPoint());
        Vector v2 = this.vertices.get(1).subtract(ray.getPoint());
        Vector v3 = this.vertices.get(2).subtract(ray.getPoint());

        //the normals of the pyramid planes.
        Vector n1 = v1.crossProduct(v2);
        Vector n2 = v2.crossProduct(v3);
        Vector n3 = v3.crossProduct(v1);

        //the values of the dot product between the normals of the pyramids planes.
        //represents the place of the point relative to them (above or under).
        double d1 = v.dotProduct(n1);
        double d2 = v.dotProduct(n2);
        double d3 = v.dotProduct(n3);

        //checks if the point is above all the planes, it means that the point is inside the triangle
        //otherwise it is not.
        if ((d1 <= 0 && d2 <= 0 && d3 <= 0) || (d1 >= 0 && d2 >= 0 && d3 >= 0)) {
            return List.of(new GeoPoint(this, planeIntersection.get(0).point));
        }

        //else returns null.
        return null;
    }
}
