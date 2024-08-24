package lighting;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.Map;
import java.util.Random;
import java.util.function.Function;

import static json.Utils.copyProps;

/**
 *
 */
public class PointLight extends Lighting implements LightSource {

    /**
     * the location of the lightSource.
     */
    protected Point position;

    /**
     * the coefficients for the point light.
     * kC: the general coefficient to know what part of the light ot take.
     * kL: the linear coefficient for the linear weakening  of the light
     * kQ: the squared coefficient for the squared weakening of the light.
     */
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;


    ///////SOFT SHADOW PROPERTIES////////
    protected double radius = 0;
    private Point[] points;
    private final int NUMBER_OF_POINTS = 100;
    private final Random rand = new Random();

    public PointLight(JSONObject jsonObject) {
        super(jsonObject);
        this.initObject(this);
    }


    /**
     * ctor for initializing the intensity.
     *
     * @param intensity the initialized intensity.
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    public Point getPosition() {
        return position;
    }

    public double getkC() {
        return kC;
    }

    public double getkL() {
        return kL;
    }

    public double getkQ() {
        return kQ;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * the impact of the point light on a point on an object.
     *
     * @param p the point on the geometry.
     * @return the light of the point from the light source.
     */
    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity().reduce(this.kC + this.kL * p.distance(this.position) + this.kQ * p.distanceSquared(this.position));
    }

    /**
     * returns the direction to the lightened point from the light source.
     *
     * @param p the point on the geometry.
     * @return the direction.
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(this.position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return this.position.distance(point);
    }


    /////////SOFT SHADOW FUNCTIONS/////////////

    /**
     * Sets the size of the array of points
     *
     * @param radius Size of the
     * @return the Point light with array of point
     */
    public PointLight setRadius(double radius) {
        this.radius = radius;
        return this;
    }

    /**
     * the function that calculates the array of points.
     *
     * @param p the point to creat the points on the plain that is orthogonal to
     *          the vector between it and the light location.
     */
    public void initializePoints(Point p) {
        if (radius == 0)
            return;
        this.points = new Point[this.NUMBER_OF_POINTS];
        Vector n = p.subtract(position).normalize();
        Vector vX = n.getOrthogonal().normalize();
        Vector vY = vX.crossProduct(n).normalize();
        double x, y, radius;
        for (int i = 0; i < NUMBER_OF_POINTS; i += 4) {
            radius = rand.nextDouble(this.radius);
            x = rand.nextDouble(radius);
            y = getCircleScale(x, radius);
            for (int j = 0; j < 4; j++) {
                points[i + j] = position.add(vX.scale(j % 2 == 0 ? x : -x)).add(vY.scale((j <= 1 ? -y : y)));//TODO debug
            }
        }
    }


    /**
     * Pythagoras function.
     *
     * @param x      the x value of the triengle.
     * @param radius the radios of the circle
     * @return the y value of the poit
     */
    private double getCircleScale(double x, double radius) {
        return Math.sqrt(radius * radius - x * x);
    }

    public Point[] getPoints() {
        return this.points;
    }

    public int getNUMBER_OF_POINTS() {
        return this.NUMBER_OF_POINTS;
    }


    @Override
    public Map<Schema, Function<JSONObject, ? extends Object>> getCreationMap() {
        return Map.of(
                SchemaLoader.load(new JSONObject(
                        "{" +
                                "   \"$schema\": \"Sphere\"," +
                                "   \"type\": \"object\"," +
                                "   \"properties\": {" +
                                "       \"intensity\": {" +
                                "           \"type\": \"object\"," +
                                "       }," +
                                "       \"position\": {" +
                                "           \"type\": \"object\"," +
                                "       }," +
                                "       \"kC\": {" +
                                "           \"type\": \"number\"," +
                                "       }," +
                                "       \"kL\": {" +
                                "           \"type\": \"number\"," +
                                "       }," +
                                "       \"kQ\": {" +
                                "           \"type\": \"number\"," +
                                "       }," +
                                "       \"radius\": {" +
                                "           \"type\": \"number\"," +
                                "       }," +
                                "   }," +
                                "   \"required\": [" +
                                "      \"intensity\",\"position\"" +
                                "   ], additionalProperties: false" +
                                "}")),
                json -> {
                    PointLight pointLight = new PointLight(new Color(json.getJSONObject("intensity")), new Point(json.getJSONObject("position")));
                    if (json.has("kC")) {
                        pointLight.setKc(json.getDouble("kC"));
                    }
                    if (json.has("kL")) {
                        pointLight.setKl(json.getDouble("kL"));
                    }
                    if (json.has("kQ")) {
                        pointLight.setKq(json.getDouble("kQ"));
                    }
                    if (json.has("radius")) {
                        pointLight.setRadius(json.getDouble("radius"));
                    }
                    return pointLight;

                }


        );
    }
}
