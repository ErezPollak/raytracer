import math;
package primitives;

public class Point {
   Double3 xyz;
   Point(double x,double y,double z){
      xyz = new Double3(x,y,z);
   }
   Vector subtract(Point p){
     Double3 D3 = this.xyz.subtract(p.xyz);
      return new Vector(D3.d1,D3.d2,D3.d3);
   }
   Point add(Vector v){
       Double3 D3 = this.xyz.add(v.xyz);
       return new Point(D3.d1,D3.d2,D3.d3);
   }
   double distanceSquared(Point p){
       return ((this.xyz.d1 - p.xyz.d1)*(this.xyz.d1 - p.xyz.d1) + (this.xyz.d2 - p.xyz.d2)*(this.xyz.d2 - p.xyz.d2) + (this.xyz.d3 - p.xyz.d3)*(this.xyz.d3 - p.xyz.d3));
   }
   double distance(Point p){
       return sqrt.sqrt(this.distanceSquared(p));
   }
}
