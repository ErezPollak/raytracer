/**
 * the Point class
 * <p>
 * written by Erez Polak
 * and Eliran Salama
 */


package primitives;


import json.JSONable;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class Point extends JSONable {

    public static final Point ZERO = new Point(0, 0, 0);
    protected final Double3 xyz;

    public Point(JSONObject jsonObject) {
        super(jsonObject);
        Point instance = this.getJsonCreatedInstance(this.getClass());
        this.xyz = instance.xyz;
    }

    /**
     * Point Constructor
     *
     * @param x
     * @param y
     * @param z
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * ctor that initialise the Point with the Double3 object.
     *
     * @param d
     */
    public Point(Double3 d) {
        this.xyz = d;
    }

    /**
     * Get point and  make vector subtraction
     *
     * @param p
     * @return
     */
    public Vector subtract(Point p) {
        return new Vector(this.xyz.subtract(p.xyz));
    }

    /**
     * Add vector to point
     *
     * @param v
     * @return
     */
    public Point add(Vector v) {
        return new Point(this.xyz.add(v.xyz));
    }

    /**
     * Calculates the distance between two points squared
     *
     * @param p
     * @return
     */
    public double distanceSquared(Point p) {
        return ((this.xyz.d1 - p.xyz.d1) * (this.xyz.d1 - p.xyz.d1) +
                (this.xyz.d2 - p.xyz.d2) * (this.xyz.d2 - p.xyz.d2) +
                (this.xyz.d3 - p.xyz.d3) * (this.xyz.d3 - p.xyz.d3));
    }

    /**
     * Calculates the distance between two points
     *
     * @param p
     * @return
     */
    public double distance(Point p) {
        return Math.sqrt(this.distanceSquared(p));
    }

    /**
     * Get point coordinates
     *
     * @return
     */
    public Double3 getXyz() {
        return xyz;
    }

    /**
     * equals function based on the equals function of Double3 object.
     *
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
     * toString function returns the status of the object.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    /**
     * returns the x value of the point.
     *
     * @return
     */
    public double getX() {
        return this.xyz.d1;
    }

    /**
     * returns the Y value of the point.
     *
     * @return the second value of the point.
     */
    public double getY() {
        return this.xyz.d2;
    }

    /**
     * returns the Z value of the point.
     *
     * @return the third value of th point
     */
    public double getZ() {
        return this.xyz.d3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    @Override
    public Map<Schema, Function<JSONObject, ? extends Object>> getCreationMap() {
        return Map.of(
                SchemaLoader.load(new JSONObject(
                        "{" +
                                "   \"$schema\": \"Double3\"," +
                                "   \"type\": \"object\"," +
                                "   \"properties\": {" +
                                "      \"x\": {" +
                                "          \"type\": \"number\"" +
                                "      }," +
                                "      \"y\": {" +
                                "          \"type\": \"number\"" +
                                "      }," +
                                "      \"z\": {" +
                                "          \"type\": \"number\"" +
                                "      }" +
                                "   }," +
                                "   \"required\": [" +
                                "      \"x\", \"y\", \"z\" " +
                                "   ], additionalProperties: false" +
                                "}")),
                json -> new Object[]{json.get("x"), json.get("y"), json.get("z")},
                SchemaLoader.load(new JSONObject(
                        "{" +
                                "   \"$schema\": \"Double3\"," +
                                "   \"type\": \"object\"," +
                                "   \"properties\": {" +
                                "      \"d\": {" +
                                "          \"type\": \"object\"" +
                                "      }," +
                                "   }," +
                                "   \"required\": [" +
                                "      \"d\" " +
                                "   ], additionalProperties: false" +
                                "}")),
                json -> new Object[]{new Double3((JSONObject) json.get("d"))}
        );
    }
}
