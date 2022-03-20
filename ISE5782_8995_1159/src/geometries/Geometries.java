package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * A class that represents all kinds of geometries
 * that implement the interface of finding points of intersections with a ray
 * using the composite design pattern
 */
public class Geometries implements Intersectable {
    private List<Intersectable> listOfIntersectable;

    /**
     * Default constructor for Geometries
     */
    public Geometries() {
        listOfIntersectable = new LinkedList();
    }

    /**
     *
     * @param geometries
     */
    public Geometries(Intersectable... geometries)
    {

    }

    /**
     *
     * @param geometries
     */
    public void add(Intersectable... geometries)
    {

    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
