package primitives;

import java.util.Objects;

public class Ray {
    private Point point;
    private Vector vector;

    /**
     * Ray Constructor
     * @param v
     * @param p
     */
    public Ray(Vector v, Point p){
        point = p;
        vector = v.normalize();
    }

    /**
     * Get ray's point
     * @return
     */
    public Point getPoint() {
        return point;
    }

    /**
     * Get ray's vector
     * @return
     */
    public Vector getVector() {
        return vector;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "point=" + point +
                ", vector=" + vector +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(point, ray.point) && Objects.equals(vector, ray.vector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, vector);
    }
}
