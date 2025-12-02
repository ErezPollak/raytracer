package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * A class that represents all kinds of geometries
 * that implement the interface of finding points of intersections with a ray
 * using the composite design pattern
 */
public class Geometries extends Intersectable {
    private List<Intersectable> listOfGeometries;

    /**
     * Default constructor for Geometries
     */
    public Geometries() {
        listOfGeometries = new LinkedList();
    }

    /**
     * Constructor for Geometries
     *
     * @param geometries
     */
    public Geometries(Intersectable... geometries) {
        this();
        add(geometries);
    }

    /**
     * Add geometry to the collection
     *
     * @param geometries the list that hte function receive.
     */
    public void add(Intersectable... geometries) {
        //adding all the given geos to the list.
        for (var item : geometries) {
            listOfGeometries.add(item);
        }
    }

    public void addAll(List<Intersectable>... lists) {
        //adding all the given geos to the list.
        for (var list : lists) {
            for (var item : list) {
                listOfGeometries.add(item);
            }
        }
    }

    /**
     * Finds all the intersections of the given ray with
     * the geometries ,and return a list of those points of intersection.
     *
     * @param ray
     * @return
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = null; // Create empty list of the points
        for (var item : listOfGeometries) {
            List<GeoPoint> itemPoints = item.findGeoIntersections(ray); // For each geometry, find it intersection with the given ray,
            if (itemPoints != null) {                               // and create a list of them.
                if (result == null) {
                    result = new LinkedList(); // If the list of all intersections with all geometries is empty, create the list.
                }
                result.addAll(itemPoints); // If there are intersections, add all the intersections of the geometrie to the list
            }                              // of all intersections with all geometries.
        }
        return result;
    }
}
