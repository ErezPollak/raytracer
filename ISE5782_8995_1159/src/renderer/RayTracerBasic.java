package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

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
        List<Point> lst =  scene.geometries.findIntersections(ray);
        if(lst == null)
            return scene.background;
        else{
            Point minDistancePoint = lst.get(0);
            for (Point p : lst){
                if(p.distance(ray.getPoint()) < minDistancePoint.distance(ray.getPoint()))
                    minDistancePoint = p;
            }
            return calcColor(minDistancePoint);
        }
    }

    private Color calcColor(Point minDistancePoint) {
        return scene.ambientLight.getIntensity();
    }
}
