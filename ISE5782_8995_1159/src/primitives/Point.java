
package primitives;

import java.util.Objects;

public class Point {

   protected Double3 xyz;

    /**
     *  Point Constructor
     * @param x
     * @param y
     * @param z
     */
   public Point(double x,double y,double z){
      xyz = new Double3(x,y,z);
   }

    /**
     * Get point and  make vector subtraction
     * @param p
     * @return
     */
    public Vector subtract(Point p){
     Double3 D3 = this.xyz.subtract(p.xyz);
      return new Vector(D3.d1,D3.d2,D3.d3);
   }

    /**
     * Add vector to point
     * @param v
     * @return
     */
    public Point add(Vector v){
       Double3 D3 = this.xyz.add(v.xyz);
       return new Point(D3.d1,D3.d2,D3.d3);
   }

    /**
     * Calculates the distance between two points squared
     * @param p
     * @return
     */
    public double distanceSquared(Point p){
       return ((this.xyz.d1 - p.xyz.d1)*(this.xyz.d1 - p.xyz.d1) + (this.xyz.d2 - p.xyz.d2)*(this.xyz.d2 - p.xyz.d2) + (this.xyz.d3 - p.xyz.d3)*(this.xyz.d3 - p.xyz.d3));
   }

    /**
     * Calculates the distance between two points
     * @param p
     * @return
     */
    public double distance(Point p){
       return Math.sqrt(this.distanceSquared(p));
   }

    /**
     * Get point coordinates
     * @return
     */
    public Double3 getXyz() {
        return xyz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(xyz, point.xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }
}
