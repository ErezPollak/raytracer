package primitives;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;


@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@JsonDeserialize(using = VectorDeserializer.class)
public class Vector extends Point {

    /**
     * Vector Constructor
     *
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector(0,0,0) is not allowed");
        }
    }

    /**
     * initialize the vector according to a Double3 object.
     *
     * @param d
     */

    public Vector(Double3 d) {
        super(d);
        if (d.equals(Double3.ZERO)) {
            throw new IllegalArgumentException("Vector(0,0,0) is not allowed");
        }
    }

    /**
     * Calculates Add between two vectors
     *
     * @param v
     * @return
     */
    public Vector add(Vector v) {
        return new Vector(this.xyz.add(v.xyz));
    }

    /**
     * Calculates double between vector and scalar
     *
     * @param scale
     * @return
     */
    public Vector scale(double scale) {
        return new Vector(this.xyz.scale(scale));
    }

    /**
     * Calculates Scalar double between two vectors
     *
     * @param v
     * @return
     */
    public double dotProduct(Vector v) {
        Double3 temp = this.xyz.product(v.xyz);
        return temp.d1 + temp.d2 + temp.d3;
    }

    /**
     * Calculates Vector double between two vectors
     *
     * @param v
     * @return
     */
    public Vector crossProduct(Vector v) {
        return new Vector(
                this.xyz.d2 * v.xyz.d3 - this.xyz.d3 * v.xyz.d2,
                this.xyz.d3 * v.xyz.d1 - this.xyz.d1 * v.xyz.d3,
                this.xyz.d1 * v.xyz.d2 - this.xyz.d2 * v.xyz.d1
        );
    }

    @Override
    public Double3 getXyz() {
        return super.getXyz();
    }

    /**
     * Calculates Vector length squared
     *
     * @return
     */
    public double lengthSquared() {
        return super.distanceSquared(new Point(0, 0, 0));
    }

    /**
     * creates an orthogonal vector to a given vector.
     *
     * @return the orthogonal vector.
     */

    public Vector getOrthogonal() {
        return this.getX() == 0 ? new Vector(1, 0, 0) : new Vector(-this.xyz.d2, this.xyz.d1, 0);
    }


    /**
     * Calculates length of vector
     *
     * @return
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Normalize vector
     *
     * @return
     */
    public Vector normalize() {
        return new Vector(this.xyz.reduce(length()));
    }

    /**
     * returns the function implemented the father.
     *
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }


}


class VectorDeserializer extends StdDeserializer<Vector> {
    public VectorDeserializer() { super(Vector.class); }

    @Override
    public Vector deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        if (node.has("xyz")) {
            return new Vector(
                    new Double3(
                            node.get("xyz").get("d1").doubleValue(),
                            node.get("xyz").get("d2").doubleValue(),
                            node.get("xyz").get("d3").doubleValue()
                    )
            );
        } else {
            return new Vector(
                    node.get("x").asDouble(),
                    node.get("y").asDouble(),
                    node.get("z").asDouble()
            );
        }
    }
}

