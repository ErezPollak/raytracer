package primitives;

import java.util.Objects;

public class Ray {
    Point point;
    Vector vector;
    Ray(Vector v, Point p){
        point = p;
        vector = v;
    }

    public Point getPoint() {
        return point;
    }

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
