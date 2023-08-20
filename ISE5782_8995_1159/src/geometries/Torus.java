package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import primitives.Complex;
import primitives.Util;


public class Torus extends Geometry {

    private Point center;
    private Vector direction;
    private double radius;
    private double width;
    private Plane plane;
    Sphere outerTube;
    Sphere innerTube;

    public Torus(Point center, Vector direction, double radius, double width) {
        this.center = center;
        this.direction = direction;
        this.radius = radius;
        this.width = width;

        assert this.width < this.radius;

        this.plane = new Plane(center, direction);
    }

    @Override
    public Vector getNormal(Point p) {
        Plane pointCenterPlain = new Plane(center, p, center.add(direction));
        Vector toCircle = this.plane.getNormal().crossProduct(pointCenterPlain.getNormal()).normalize();
        Point onCircle = this.center.add(toCircle.scale(radius));
        return p.subtract(onCircle).normalize();
    }


    private Ray canonizingRay(Ray ray) {
//        Point p = ray.getPoint();
//        Vector v = ray.getVector();
//        return new Ray(
//                new Point(ray.getPoint().getXyz().add(this.center.getXyz())),
//                new Vector(v.getX() - 0.1, v.getY(), v.getZ() - 0.5)
//        );
        return ray;
    }

    /*
    http://cosinekitty.com/raytrace/chapter13_torus.html
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        double A = this.radius;
        double B = this.width;

        Ray canonizedRay = canonizingRay(ray);

        double Ex = canonizedRay.getVector().getX();
        double Ey = canonizedRay.getVector().getY();
        double Ez = canonizedRay.getVector().getZ();

        double Dx = canonizedRay.getPoint().getX();
        double Dy = canonizedRay.getPoint().getY();
        double Dz = canonizedRay.getPoint().getZ();

        double G = 4 * A * A * (Ex * Ex + Ey * Ey);
        double H = 8 * A * A * (Dx * Ex + Dy * Ey);
        double I = 4 * A * A * (Dx * Dx + Dy * Dy);
        double J = canonizedRay.getVector().lengthSquared();
        double K = 2 * (Dx * Ex + Dy * Ey + Dz * Ez);
        double L = Dx * Dx + Dy * Dy + Dz * Dz + A * A - B * B;

        double a = J * J;
        double b = 2 * J * K;
        double c = 2 * J * L + K * K - G;
        double d = 2 * K * L - H;
        double e = L * L - I;


        List<Complex> uValues = Util.solve4degreeQuarticFunction(a, b, c, d, e);

        var points = uValues.stream()
                .filter(number -> number.getReal() > 0 && number.getImaginary() == 0)
                .distinct()
                .map(number -> new GeoPoint(this, canonizedRay.getPoint().add(canonizedRay.getVector().scale(number.getReal()))))
                .collect(Collectors.toList());

        if (points.size() == 0) {
            return null;
        }

        return points;
    }


}
