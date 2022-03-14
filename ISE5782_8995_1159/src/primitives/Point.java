/**
 * the Point class
 *
 * written by Erez Polak
 * and Eliran Salama
 */


package primitives;


public class Point {

   protected final Double3 xyz;

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
     * ctor that initialise the Point with the Double3 object.
     * @param d
     */
   public Point(Double3 d){
       this.xyz = d;
   }

    /**
     * Get point and  make vector subtraction
     * @param p
     * @return
     */
    public Vector subtract(Point p){
        return new Vector(this.xyz.subtract(p.xyz));
   }

    /**
     * Add vector to point
     * @param v
     * @return
     */
    public Point add(Vector v){
         return new Point(this.xyz.add(v.xyz));
   }

    /**
     * Calculates the distance between two points squared
     * @param p
     * @return
     */
    public double distanceSquared(Point p){
       return ((this.xyz.d1 - p.xyz.d1)*(this.xyz.d1 - p.xyz.d1) +
               (this.xyz.d2 - p.xyz.d2)*(this.xyz.d2 - p.xyz.d2) +
               (this.xyz.d3 - p.xyz.d3)*(this.xyz.d3 - p.xyz.d3));
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

    /**
     * equals function based on the equals function of Double3 object.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }

    /**
     * tostring function returns the status of the object.
     * @return
     */
    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }
}
