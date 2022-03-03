package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Cylinder extends Tube implements Geometry {
    double high;

    /**
     * Cylinder Constructor
     * @param high
     */
    public Cylinder(double raduis, Ray ray, double high) {
        super(raduis, ray);
        this.high = high;
    }

    /**
     * Get high of cylinder
     * @return
     */
    public double getHigh() {
        return high;
    }

    @Override
    public Vector getNormal(Point p) {

        //the points that define the bottom and top of the cylinder.
        Point bottom = ray.getPoint();
        Point top = ray.getPoint().add(this.ray.getVector().normalize().scale(this.getHigh()));

        //the vectors from them to the given point.
        Vector toBottrm = p.subtract(bottom);
        Vector toTop = p.subtract(top);

        //checks if one of the vectors is in 90 degrees with the ray vector.
        if (toBottrm.dotProduct(ray.getVector()) == 0 || toTop.dotProduct(ray.getVector()) == 0) {

            //check that the distance is not the radius
            if (!isZero(toTop.length() - this.raduis) && !isZero(toBottrm.length() - this.raduis)) {

                //then this is the normal of the cylinder in that point.
                return ray.getVector().normalize();

            }else{
                //the point is on the edge  of the cylinder.
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
                "high=" + high +
                '}';
    }
}
