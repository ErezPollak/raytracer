package geometries;

import primitives.Point;
import primitives.Vector;

public interface Geometry extends  Intersectable {
   /**
    * Get normal of the point
    * @param p
    * @return
    */
   public Vector getNormal(Point p);
}
