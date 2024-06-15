package lighting;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.Map;
import java.util.function.Function;

/**
 *
 */
public class SpotLight extends PointLight implements LightSource {

    /**
     * the direction in witch the light is strongest.
     */
    private Vector direction;


    private NarrowBeamArchitecture ARCHITECTURE = NarrowBeamArchitecture.DAN_ARCHITECTURE;
    /**
     * represents the angle of the seenable light from the spot.
     */
    private double narrowBeam = ARCHITECTURE == NarrowBeamArchitecture.DAN_ARCHITECTURE ? 1 : 0;

    public SpotLight(JSONObject jsonObject){
        super(jsonObject);
        SpotLight spotLight = (SpotLight) this.getObject();
        this.intensity = spotLight.intensity;
        this.position = spotLight.position;
        this.setNarrowBeam(NarrowBeamArchitecture.MY_ARCHITECTURE, (int) spotLight.narrowBeam);
        this.setKq(spotLight.getkQ());
        this.setKc(spotLight.getkC());
        this.setKl(spotLight.getkL());
        this.radius = spotLight.radius;
        this.direction = spotLight.direction;
    }

    /**
     * the ctor to initialize the SpotLight.
     *
     * @param intensity the color of the light.
     * @param position  the position of the light.
     * @param direction the direction of the light.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * sets the narrow parameter that sets the angle of the spotlight.
     * and sets the parameter to be the cos of the angle, in order to compare them in the get intensity function.
     *
     * @param i the new angle to the narrow.
     * @return the spotlight after the change.
     */
    public SpotLight setNarrowBeam(NarrowBeamArchitecture architecture, int i) {
        this.ARCHITECTURE = architecture;
        this.narrowBeam = ARCHITECTURE == NarrowBeamArchitecture.DAN_ARCHITECTURE ? i : Math.cos(Math.toRadians(i));
        return this;
    }

    /**
     * the impact of the spotlight on a point on an object.
     *
     * @param p the point on the geometry.
     * @return the light of the point from the light source.
     */
    @Override
    public Color getIntensity(Point p) {
        //calculates the degree between the direction of the light source to the direction of the light source to the point.
        double degree = this.direction.dotProduct(super.getL(p));

        if (degree < 0 || (ARCHITECTURE == NarrowBeamArchitecture.MY_ARCHITECTURE && degree < narrowBeam))
            return Color.BLACK;

        if (ARCHITECTURE == NarrowBeamArchitecture.DAN_ARCHITECTURE) {
            for (int i = 0; i < narrowBeam - 1; i++) {
                degree *= degree;
            }
        }
        return super.getIntensity(p).scale(degree);
    }

    //////////SOFT SHADOW FUNCTIONS/////

    @Override
    public SpotLight setRadius(double radius) {
        this.radius = radius;
        this.initializePoints();
        return this;
    }

    private void initializePoints() {
        super.initializePoints(this.position.add(direction));
    }

    public Vector getDirection(){
        return this.direction;
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
                                "       \"direction\": {" +
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
                                "      \"intensity\",\"position\", \"direction\"" +
                                "   ], additionalProperties: false" +
                                "}")),
                json -> {
                    SpotLight pointLight = new SpotLight(
                            new Color(json.getJSONObject("intensity")),
                            new Point(json.getJSONObject("position")),
                            new Vector(json.getJSONObject("direction")));
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
