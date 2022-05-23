/**
 * the Point class
 * <p>
 * written by Erez Polak
 * and Eliran Salama
 */

package primitives;

public class Ray {

    /**
     * the distance to move the point of the shade ray, in case of shade.
     */
    private static final double DELTA = 0.01;

    private final Point point;
    private final Vector vector;

    /**
     * Ray Constructor
     *
     * @param v
     * @param p
     */
    public Ray(Point p, Vector v) {
        point = p;
        vector = v.normalize();
    }

    /**
     * the head of the constructed ray will be moves by the direction vector multiplied by delta.
     * the direction ov the move with or against the normal vector determined by the dot product of the direction and normal.
     *
     * @param head      the point that the ray suppose to start with.
     * @param direction the direction of the ray.
     * @param normal    the direction to move the ray with.
     */
    public Ray(Point head, Vector direction, Vector normal) {
        double mult = direction.dotProduct(normal);
        this.point = head.add(normal.scale(mult >= 0 ? DELTA : -DELTA));
        this.vector = direction.normalize();
    }

    /**
     * Get ray's point
     *
     * @return
     */
    public Point getPoint() {
        return point;
    }


    /**
     * Get ray's vector
     *
     * @return
     */
    public Vector getVector() {
        return vector;
    }


    /**
     * returns the Point on the ray that is given by the scaling of the vector by the parameter t.
     *
     * @param t the parameter that needed for scaling the vector.
     * @return the result point.
     */
    public Point getPoint(double t) {
        return this.point.add(this.vector.normalize().scale(t));
    }

    /**
     * return a string with the status of the object.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Ray{" +
                "point=" + point +
                ", vector=" + vector +
                '}';
    }

    /**
     * returns the equal of the point and the vector.
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return point.equals(ray.point) && vector.equals(ray.vector);
    }

}
