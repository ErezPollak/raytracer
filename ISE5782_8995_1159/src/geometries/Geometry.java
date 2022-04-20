package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * the class that represent a geometry.
 */
public abstract class Geometry extends Intersectable {

   /**
    * the light that every geometry should emit.
    * initialized to black.
    */
   protected Color emission = Color.BLACK;


   /**
    * Get normal of the geometry in a given point.
    * @param p the point to look for the normal in.
    * @return the normal vector of the Geometry in the given point.
    */
   public abstract Vector getNormal(Point p);

   /**
    * @return the emitted light property.
    */
   public Color getEmission() {
      return emission;
   }

   /**
    * setting the color and returning the object itself for more initializations.
    * @param emission the new Emission color of the geometry.
    */
   public Geometry setEmission(Color emission) {
      this.emission = emission;
      return this;
   }
}
