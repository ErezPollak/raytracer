package renderer;

import com.fasterxml.jackson.annotation.*;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * the class that build a ray and checks for intersections with all the geometries in the scene.
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RayTracerBasic.class, name = "rayTracerBasic")
})
public abstract class RayTracerBase {
    /**
     * the scene filed saves the scene in witch we will look for the rays color.
     */
    @JsonProperty("scene")
    protected Scene scene;

    /**
     * the constructor, that initialize the class.
     *
     * @param scene the class initializer.
     */
    @JsonCreator
    public RayTracerBase(@JsonProperty("scene") Scene scene) {
        this.scene = new Scene(scene.name)
                .setBackground(scene.background)
                .setAmbientLight(scene.ambientLight)
                .setGeometries(scene.geometries)
                .setLights(scene.lights);
    }

    /**
     * constructing the ray on the scene, returns the color of the closest object to the camera.
     *
     * @param ray the ray we need to build from.
     * @return the color of the scene.
     */
    public abstract Color traceRay(Ray ray);
}
