package geometries;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import primitives.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Torus extends Geometry {

    Vector DIRECTION = new Vector(0, 0, 1);

    Point center;
    double radius;
    double width;
    Plane plane;

    public Torus(JSONObject jsonObject) {
        super(jsonObject);
        Torus torus = this.getJsonCreatedInstance(this.getClass());
        this.center = torus.center;
        this.radius = torus.radius;
        this.width = torus.width;
        this.plane = torus.plane;
    }

    public Torus(Point center, double radius, double width) {
        this.center = center;
        this.radius = radius;
        this.width = width;

        assert this.width < this.radius;

        this.plane = new Plane(center, DIRECTION);
    }

    @Override
    public Vector getNormal(Point p) {
        Plane pointCenterPlain = new Plane(center, p, center.add(DIRECTION));
        Vector toCircle = this.plane.getNormal().crossProduct(pointCenterPlain.getNormal()).normalize();
        Point onCircle = this.center.add(toCircle.scale(radius));
        return p.subtract(onCircle).normalize();
    }

    /*
    http://cosinekitty.com/raytrace/chapter13_torus.html
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        double A = this.radius;
        double B = this.width;

        double Ex = ray.getVector().getX();
        double Ey = ray.getVector().getY();
        double Ez = ray.getVector().getZ();

        double Dx = ray.getPoint().getX();
        double Dy = ray.getPoint().getY();
        double Dz = ray.getPoint().getZ();

        double G = 4 * A * A * (Ex * Ex + Ey * Ey);
        double H = 8 * A * A * (Dx * Ex + Dy * Ey);
        double I = 4 * A * A * (Dx * Dx + Dy * Dy);
        double J = ray.getVector().lengthSquared();
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
                .map(number -> new GeoPoint(this, ray.getPoint().add(ray.getVector().scale(number.getReal()))))
                .collect(Collectors.toList());

        if (points.size() == 0) {
            return null;
        }

        return points;
    }

    @Override
    public String toString() {
        return "Torus{" +
                "radius=" + this.radius +
                ", center=" + this.center +
                ", width=" + this.width +
                '}';
    }


    @Override
    public Map<Schema, Function<JSONObject, ? extends Object>> getCreationMap() {
        return Map.of(
                SchemaLoader.load(new JSONObject(
                        "{" +
                                "   \"$schema\": \"Sphere\"," +
                                "   \"type\": \"object\"," +
                                "   \"properties\": {" +
                                "      \"center\": {" +
                                "          \"type\": \"object\"" +
                                "      }," +
                                "      \"radius\": {" +
                                "          \"type\": \"number\"" +
                                "      }," +
                                "      \"width\": {" +
                                "          \"type\": \"number\"" +
                                "      }," +
                                "   }," +
                                "   \"required\": [" +
                                "      \"radius\", \"center\", \"width\"" +
                                "   ], additionalProperties: false" +
                                "}")),
                json -> new Object[]{new Point(json.getJSONObject("center")), json.getDouble("radius"), json.getDouble("width")});
    }
}
