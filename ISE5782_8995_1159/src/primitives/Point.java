
package primitives;

public class Point {

   protected Double3 xyz;

   public Point(double x,double y,double z){
      xyz = new Double3(x,y,z);
   }

    public Vector subtract(Point p){
     Double3 D3 = this.xyz.subtract(p.xyz);
      return new Vector(D3.d1,D3.d2,D3.d3);
   }

    public Point add(Vector v){
       Double3 D3 = this.xyz.add(v.xyz);
       return new Point(D3.d1,D3.d2,D3.d3);
   }

    public double distanceSquared(Point p){
       return ((this.xyz.d1 - p.xyz.d1)*(this.xyz.d1 - p.xyz.d1) + (this.xyz.d2 - p.xyz.d2)*(this.xyz.d2 - p.xyz.d2) + (this.xyz.d3 - p.xyz.d3)*(this.xyz.d3 - p.xyz.d3));
   }

    public double distance(Point p){
       return Math.sqrt(this.distanceSquared(p));
   }
}
