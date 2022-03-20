package geometries;

import primitives.Point;
import primitives.Ray;
import java.util.*;

/**
 * Interface for the intersections
 */
public interface Intersectable {
    /**
     *  finds the intersection for each shape
     * @param ray
     * @return
     */
    public List<Point> findIntersections(Ray ray);
}
