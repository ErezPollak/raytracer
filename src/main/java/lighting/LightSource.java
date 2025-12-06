package lighting;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * holds all the functionality of a lightSource.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DirectionalLight.class, name = "directional"),
        @JsonSubTypes.Type(value = SpotLight.class, name = "spot"),
        @JsonSubTypes.Type(value = PointLight.class, name = "point")
})
public interface LightSource {

    /**
     * the intensity of the light source on some point on a geometry.
     *
     * @param p the point on the geometry.
     * @return the impact the light source has in the intensity of the point.
     */
    Color getIntensity(Point p);

    /**
     * the vector of the direction between the point on the geometry.
     *
     * @param p the point on the geometry.
     * @return the vector between the light source and the point.
     */
    Vector getL(Point p);

    /**
     * calculate the distance between the intersection point to the light source.
     *
     * @param point the intersection point.
     * @return the distance.
     */
    double getDistance(Point point);


}
