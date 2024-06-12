/**
 * the Point class
 * <p>
 * written by Erez Polak
 * and Eliran Salama
 */


package geometries;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static primitives.Util.isZero;

public class Plane extends Geometry {

    private Point q0;
    private Vector normal;

    public Plane(JSONObject jsonObject) {
        super(jsonObject);
        Plane plane = this.getJsonCreatedInstance(this.getClass());
        this.q0 = plane.q0;
        this.normal = plane.normal;
    }

    /**
     * sets the plane according to the Point and normal vector.
     *
     * @param q0
     * @param normal
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * sets the plane according to three points.
     *
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        //if two points are the same
        if (p1.equals(p2) || p1.equals(p3) || p2.equals(p3)) {
            throw new IllegalArgumentException("Two points are both equal.");
        }

        //if the three points on the same line.
        if (p1.subtract(p2).normalize().equals(p1.subtract(p3)) || p1.subtract(p2).normalize().equals(p1.subtract(p3).scale(-1))) {
            throw new IllegalArgumentException("All the points are on the same line.");
        }

        this.q0 = p1;
        //normal calculation cross product between two vectors created by the three given point.
        this.normal = (p1.subtract(p2)).crossProduct(p1.subtract(p3));
    }

    /**
     * returns the point.
     *
     * @return
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * returns the normal vector.
     *
     * @param p
     * @return
     */
    @Override
    public Vector getNormal(Point p) {
        return this.normal.normalize();
    }

    /**
     * returns a string contains the status of the plane.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }

    /**
     * not required as a part of the requirements
     * it is written only because of the implementation in the Polygon class.
     *
     * @return
     */
    public Vector getNormal() {
        return this.normal;
    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {

        //if the starting point of the ray is the same point that the plane start with, the return value is null.
        if (this.q0.equals(ray.getPoint()))
            return null;

        //calculating t according to the given formula:
        double t = (this.normal.dotProduct(this.q0.subtract(ray.getPoint()))) / (this.normal.dotProduct(ray.getVector()));

        //if t is equal to zero, the point is on the plane, and it does not count.
        if (t > 0 && !isZero(t)) {

            //creating the list only if there is a need for that.
            LinkedList<GeoPoint> intersectionPoints = new LinkedList<>();

            //adding the point to the list and returning the list.
            intersectionPoints.add(new GeoPoint(this, ray.getPoint(t)));
            return intersectionPoints;

        }

        return null;
    }


    @Override
    public Map<Schema, Function<JSONObject, ? extends Object>> getCreationMap() {
        return Map.of(
                SchemaLoader.load(new JSONObject(
                        "{" +
                                "   \"$schema\": \"Plain\"," +
                                "   \"type\": \"object\"," +
                                "   \"properties\": {" +
                                "      \"p0\": {" +
                                "          \"type\": \"object\"" +
                                "      }," +
                                "      \"normal\": {" +
                                "          \"type\": \"object\"" +
                                "      }," +
                                "   }," +
                                "   \"required\": [" +
                                "      \"p0\", \"normal\"" +
                                "   ], additionalProperties: false" +
                                "}")),
                json -> new Object[]{new Point(json.getJSONObject("p0")), new Vector(json.getJSONObject("normal"))},
                SchemaLoader.load(new JSONObject(
                        "{" +
                                "   \"$schema\": \"Plain\"," +
                                "   \"type\": \"object\"," +
                                "   \"properties\": {" +
                                "      \"p1\": {" +
                                "          \"type\": \"object\"" +
                                "      }," +
                                "      \"p2\": {" +
                                "          \"type\": \"object\"" +
                                "      }," +
                                "      \"p3\": {" +
                                "          \"type\": \"object\"" +
                                "      }" +
                                "   }," +
                                "   \"required\": [" +
                                "      \"p1\", \"p2\",\"p3\" " +
                                "   ], additionalProperties: false" +
                                "}")),
                json -> new Object[]{
                        new Point(json.getJSONObject("p1")),
                        new Point(json.getJSONObject("p2")),
                        new Point(json.getJSONObject("p3"))}


        );
    }
}

