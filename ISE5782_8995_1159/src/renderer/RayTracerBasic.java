package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * the class
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * construction the class with the given scene.
     *
     * @param scene the scene to pass on to the superclass.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * tracing the ray and returns the color that the pixel needs to be painted with.
     *
     * @param ray the ray we need to get the color for.
     * @return the color of te pixel.
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> lst = scene.geometries.findGeoIntersections(ray);
        if (lst == null)
            return scene.background;
        else {
            return calcColor(ray.findClosestGeoPoint(lst), ray);
        }
    }

    /**
     * calculate the color that needed to be returned from the pixel.
     *
     * @param minDistancePoint the point to calculate the color for.
     * @param ray              the ray to pass to the function that summarise all the effects of the light sources.
     * @return the color to paint the pixel.
     */
    private Color calcColor(GeoPoint minDistancePoint, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(minDistancePoint.geometry.getEmission(),
                        calcLocalEffects(minDistancePoint, ray));
    }

    /**
     * the sum of all the impacts of the light sources on the point.
     *
     * @param gp  the point with the geometry properties including the material properties.
     * @param ray the ray to check the light from.
     * @return the sum of all the light sources on the point.
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = Color.BLACK;
        Vector v = ray.getVector();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = gp.geometry.getMaterial();

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(l.dotProduct(n));
            Color iL = lightSource.getIntensity(gp.point);
            color = color.add(iL.scale(calcDiffusive(material, nl)),
                    iL.scale(calcSpecular(material, n, l, nl, v)));
        }
        return color;
    }

    /**
     * calculating the specular part of the light.
     *
     * @param material the material containing the properties of
     * @param n        the normal vector of the point in the intersection.
     * @param l        the direction point of the camera.
     * @param nl       the dot product between them.
     * @param v        the to vector of the camera.
     * @return the specular coefficient.
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {

        //the specular vector, to check the match between it and the camera to vector.
        Vector r = l.subtract(n.scale(2 * nl)).normalize();

        double vr = -v.dotProduct(r);

        //if the object is behind the light source in relation to the camera the specular impact is zero.
        if (vr <= 0) return Double3.ZERO;

        //duplicating it by itself for the nShininess factor.
        double vrMult = 1;
        for (int i = 0; i < material.nShininess; i++) {
            vrMult *= vr;
        }
        return material.kS.scale(vrMult);
    }

    /**
     * calculate the diffusive part of the light source.
     *
     * @param material the material.
     * @param nl       the direction degree between the direction vector of the light to the direction between the light and geometry.
     * @return the defensive coefficient.
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(nl < 0 ? -nl : nl);
    }
}
