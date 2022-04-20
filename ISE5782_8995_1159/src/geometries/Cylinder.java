package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

public class Cylinder extends Tube{
    double height;

    /**
     * Cylinder Constructor
     * @param high
     */
    public Cylinder(double raduis, Ray ray, double high) {
        super(raduis, ray);
        this.height = high;
    }

    /**
     * Get high of cylinder
     * @return
     */
    public double getHeight() {
        return height;
    }

    /**
     * returns the normal of the cylinder in the given point.
     * @param p the point to find the normal from.
     * @return the normal vector.
     */
    @Override
    public Vector getNormal(Point p) {

        //the points that define the bottom and top of the cylinder.
        Point bottom = ray.getPoint();
        Point top = ray.getPoint().add(this.ray.getVector().normalize().scale(this.getHeight()));

        //the vectors from them to the given point.
        Vector toBottom = p.subtract(bottom);
        Vector toTop = p.subtract(top);

        //checks if one of the vectors is in 90 degrees with the ray vector.
        if (toBottom.dotProduct(ray.getVector()) == 0 || toTop.dotProduct(ray.getVector()) == 0) {

            //check that the distance is not the radius
            if (!isZero(toTop.length() - this.raduis) && !isZero(toBottom.length() - this.raduis)) {

                //then this is the normal of the cylinder in that point.
                return ray.getVector().normalize();

            }else{
                //the point is on the edge of the cylinder.
                throw new IllegalArgumentException();
            }
        }else{
            //the point is on the tube.
            return super.getNormal(p);
        }
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "high=" + height +
                '}';
    }

    /**
     * finding the Geo intersections with the geometry.
     * @param ray the ray to check intersections with.
     * @return the list of intersections.
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return null;
    }
}
