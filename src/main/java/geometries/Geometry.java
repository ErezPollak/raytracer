package geometries;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import primitives.*;


/**
 * the class that represent a geometry.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Plane.class, name = "plain"),
        @JsonSubTypes.Type(value = Polygon.class, name = "polygon"),
        @JsonSubTypes.Type(value = Sphere.class, name = "sphere"),
        @JsonSubTypes.Type(value = Tube.class, name = "tube"),
        @JsonSubTypes.Type(value = Triangle.class, name = "triangle"),
        @JsonSubTypes.Type(value = Cylinder.class, name = "cylinder")
})
public abstract class Geometry extends Intersectable {

   /**
    * the light that every geometry should emit.
    * initialized to black.
    */

   protected Color emission = Color.BLACK;

   /**
    * holds the properties of the geometry.
    */
   private Material material = new Material();

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

   /**
    * returns the material propertied.
    * @return the material.
    */
   public Material getMaterial() {
      return material;
   }

   /**
    * setting the material to a new one.
    * @param material the new material.
    * @return the object with the material updated.
    */
   public Geometry setMaterial(Material material) {
      this.material = material;
      return this;
   }
}
