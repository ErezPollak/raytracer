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
   Point add()
}
