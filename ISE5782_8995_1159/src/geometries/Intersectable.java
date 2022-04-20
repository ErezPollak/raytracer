package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.*;

/**
 * Interface for the intersections
 */
public abstract class Intersectable {

    /**
     * a PDS that used to set the color of the object.
     */
    public static class GeoPoint {

        /**
         * the geometry that the intersected point come from.
         */
        public Geometry geometry;

        /**
         * the point itself.
         */
        public Point point;

        /**
         * ctor for initializing the properties.
         * @param geometry the geometry.
         * @param point the point on the geometry.
         */
        public GeoPoint(Geometry geometry , Point point){
            this.geometry = geometry;
            this.point = point;
        }

        /**
         * making sure that the points have the same values, and the geometries have the same address in memory.
         * @param o the geoPoint to compare to.
         * @return true if the objects are the same.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(this.geometry, geoPoint.geometry) && this.point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }


    /**
     *  finds the points of the intersection.
     * @param ray the ray to check intersection with.
     * @return the list of intersections with.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * find the list of intersections with the Geometry, in order to find the color.
     * @param ray the ray to check intersections with.
     * @return the list of intersections.
     */
    public List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * the private mathod that will be overriden by all the geomrtey classes.
     * @param ray the ray to check intersections with.
     * @return the list of intersections.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

}
