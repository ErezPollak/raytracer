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

public class Cylinder extends Tube {
    double height;


    public Cylinder(JSONObject jsonObject) {
        super(jsonObject);
        Cylinder cylinder = this.getJsonCreatedInstance(this.getClass());
        height = cylinder.getHeight();
    }

    /**
     * Cylinder Constructor
     *
     * @param height
     */
    public Cylinder(Ray ray, double radius, double height) {
        super(ray, radius);
        this.height = height;
    }

    public Cylinder(Tube tube, double height) {
        super(tube.ray, tube.radius);
        this.height = height;
    }


    /**
     * Get high of cylinder
     *
     * @return
     */
    public double getHeight() {
        return height;
    }

    /**
     * returns the normal of the cylinder in the given point.
     *
     * @param p the point to find the normal from.
     * @return the normal vector.
     */
    @Override
    public Vector getNormal(Point p) {

        //the points that define the bottom and top of the cylinder.
        Point bottom = ray.getPoint();
        Point top = ray.getPoint().add(this.ray.getVector().normalize().scale(this.getHeight()));

        //the vectors from them to the given point.
        Vector toBottom = p.subtract(bottom);
        Vector toTop = p.subtract(top);

        //checks if one of the vectors is in 90 degrees with the ray vector.
        if (toBottom.dotProduct(ray.getVector()) == 0 || toTop.dotProduct(ray.getVector()) == 0) {

            //check that the distance is not the radius
            if (!isZero(toTop.length() - this.radius) && !isZero(toBottom.length() - this.radius)) {

                //then this is the normal of the cylinder in that point.
                return ray.getVector().normalize();

            } else {
                //the point is on the edge of the cylinder.
                throw new IllegalArgumentException();
            }
        } else {
            //the point is on the tube.
            return super.getNormal(p);
        }
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "high=" + height +
                '}';
    }

//    /**
//     * finding the Geo intersections with the geometry.
//     * @param ray the ray to check intersections with.
//     * @return the list of intersections.
//     */
//    @Override
//    public List<GeoPoint> findGeoIntersections(Ray ray) {
//        return null;
//    }


    /**
     * find intersection points between ray and 3D cylinder
     *
     * @param ray ray towards the sphere
     * @return immutable list containing 0/1/2 intersection points as {@link GeoPoint}s objects
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // origin point of cylinder (on bottom base)
        Point basePoint = super.ray.getPoint();
        // point across base point on top base
        Point topPoint = super.ray.getPoint(height);
        // direction vector of cylinder (orthogonal to base point)
        Vector vC = super.ray.getVector();

        // find intersection points of ray with bottom base of cylinder
        List<GeoPoint> result = new LinkedList<>();
        // crate plane that contains base point in it
        Plane basePlane = new Plane(basePoint, vC);
        // find intersection between ray and plane
        List<GeoPoint> intersectionsBase = basePlane.findGeoIntersections(ray);

        // if intersections were found, check that point are actually on the base of the cylinder
        //if distance from base point to intersection point holds the equation ->  distance² < from radius²
        if (intersectionsBase != null) {
            for (GeoPoint p : intersectionsBase) {
                Point pt = p.point;
                // intersection point is the base point itself
                if (pt.equals(basePoint))
                    result.add(new GeoPoint(this, basePoint));
                    // intersection point is different to base point but is on the bottom base
                else if (pt.subtract(basePoint).dotProduct(pt.subtract(basePoint)) < super.radius * super.radius)
                    result.add(new GeoPoint(this, pt));
            }
        }

        // find intersection points of ray with bottom base of cylinder
        // crate plane that contains top point in it
        Plane topPlane = new Plane(topPoint, vC);
        // find intersection between ray and plane
        List<GeoPoint> intersectionsTop = topPlane.findGeoIntersections(ray);
        // if intersections were found, check that point are actually on the base of the cylinder
        //if distance from top point to intersection point holds the equation ->  distance² < from radius²
        if (intersectionsTop != null) {
            for (var p : intersectionsTop) {
                Point pt = p.point;
                // intersection point is the top point itself
                if (pt.equals(topPoint))
                    result.add(new GeoPoint(this, topPoint));
                    // intersection point is different to base point but is on the bottom base
                else if (pt.subtract(topPoint).dotProduct(pt.subtract(topPoint)) < super.radius * super.radius)
                    result.add(new GeoPoint(this, pt));
            }
        }

        // if rsy intersects both bases , no other intersections possible - return the result list
        if (result.size() == 2)
            return result;

        // use tube parent class function to find intersections with the cylinder represented
        // as an infinite tube
        List<GeoPoint> intersectionsTube = super.findGeoIntersectionsHelper(ray);

        // if intersection points were found check that they are within the finite cylinder's boundary
        // by checking if  scalar product fo direction vector with a vector from intersection point
        // to bottom base point is positive, and scalar product of direction vector with a
        // vector from intersection point to top base point is negative
        if (intersectionsTube != null) {
            for (var p : intersectionsTube) {
                Point pt = p.point;
                if (vC.dotProduct(pt.subtract(basePoint)) > 0 && vC.dotProduct(pt.subtract(topPoint)) < 0)
                    result.add(new GeoPoint(this, pt));
            }
        }

        // return an immutable list
        int len = result.size();
        if (len > 0)
            if (len == 1)
                return List.of(result.get(0));
            else
                return List.of(result.get(0), result.get(1));

        // no intersections
        return null;
    }


    @Override
    public Map<Schema, Function<JSONObject, ? extends Object>> getCreationMap() {
        return Map.of(
                SchemaLoader.load(new JSONObject(
                        "{" +
                                "   \"$schema\": \"Sphere\"," +
                                "   \"type\": \"object\"," +
                                "   \"properties\": {" +
                                "      \"ray\": {" +
                                "          \"type\": \"object\"" +
                                "      }," +
                                "      \"radius\": {" +
                                "          \"type\": \"number\"" +
                                "      }," +
                                "      \"height\": {" +
                                "          \"type\": \"number\"" +
                                "      }," +
                                "   }," +
                                "   \"required\": [" +
                                "      \"radius\", \"ray\", \"height\"" +
                                "   ], additionalProperties: false" +
                                "}")),
                json -> new Object[]{new Ray(json.getJSONObject("ray")), json.getDouble("radius"), json.getDouble("height")},
                SchemaLoader.load(new JSONObject(
                        "{" +
                                "   \"$schema\": \"Sphere\"," +
                                "   \"type\": \"object\"," +
                                "   \"properties\": {" +
                                "      \"tube\": {" +
                                "          \"type\": \"object\"" +
                                "      }," +
                                "      \"height\": {" +
                                "          \"type\": \"number\"" +
                                "      }," +
                                "      \"geometry\": {" +
                                "          \"type\": \"object\"" +
                                "      }," +
                                "   }," +
                                "   \"required\": [" +
                                "      \"tube\", \"height\"" +
                                "   ], additionalProperties: false" +
                                "}")),
                json -> new Object[]{new Tube(json.getJSONObject("tube")), json.getDouble("height")}

        );

    }

}

