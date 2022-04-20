package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * the class
 */
public class RayTracerBasic extends  RayTracerBase{

    /**
     * construction the class with the given scene.
     * @param scene the scene to pass on to the superclass.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> lst =  scene.geometries.findGeoIntersections(ray);
        if(lst == null)
            return scene.background;
        else{
            return calcColor(ray.findClosestGeoPoint(lst));
        }
    }

    private Color calcColor(GeoPoint minDistancePoint) {
        return scene.ambientLight.getIntensity().add(minDistancePoint.geometry.getEmission());
    }
}
