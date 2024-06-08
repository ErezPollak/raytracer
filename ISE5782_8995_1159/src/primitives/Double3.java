/**
 *
 */
package primitives;

import json.JSONable;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

import java.util.Map;
import java.util.function.Function;

import static primitives.Util.isZero;

/**
 * This class will serve all primitive classes based on three numbers
 *
 * @author Dan Zilberstein
 */
public class Double3 extends JSONable {
    final double d1;
    final double d2;
    final double d3;

    /**
     * Zero triad (0,0,0)
     */
    public static final Double3 ZERO = new Double3(0, 0, 0);

    public Double3(JSONObject jsonObject) {
        super(jsonObject);
        Double3 instance = getJsonCreatedInstance(this.getClass());
        this.d1 = instance.d1;
        this.d2 = instance.d2;
        this.d3 = instance.d3;
    }


    /**
     * Constructor to initialize Double3 based object with its three number values
     *
     * @param d1 first number value
     * @param d2 second number value
     * @param d3 third number value
     */
    public Double3(double d1, double d2, double d3) {
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
    }

    /**
     * Constructor to initialize Double3 based object the same number values
     *
     * @param value number value for all 3 numbers
     */
    public Double3(double value) {
        this.d1 = value;
        this.d2 = value;
        this.d3 = value;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Double3))
            return false;
        Double3 other = (Double3) obj;
        return isZero(d1 - other.d1) && isZero(d2 - other.d2) && isZero(d3 - other.d3);
    }

    @Override
    public int hashCode() {
        return (int) Math.round(d1 + d2 + d3);
    }

    @Override
    public String toString() {
        return "(" + d1 + "," + d2 + "," + d3 + ")";
    }

    /**
     * Sum two floating point triads into a new triad where each couple of numbers
     * is summarized
     *
     * @param rhs right handle side operand for addition
     * @return result of add
     */
    public Double3 add(Double3 rhs) {
        return new Double3(d1 + rhs.d1, d2 + rhs.d2, d3 + rhs.d3);
    }

    /**
     * Subtract two floating point triads into a new triad where each couple of
     * numbers is subtracted
     *
     * @param rhs right handle side operand for addition
     * @return result of add
     */
    public Double3 subtract(Double3 rhs) {
        return new Double3(d1 - rhs.d1, d2 - rhs.d2, d3 - rhs.d3);
    }

    /**
     * Scale (multiply) floating point triad by a number into a new triad where each
     * number is multiplied by the number
     *
     * @param rhs right handle side operand for scaling
     * @return result of scale
     */
    public Double3 scale(double rhs) {
        return new Double3(d1 * rhs, d2 * rhs, d3 * rhs);
    }

    /**
     * Reduce (divide) floating point triad by a number into a new triad where each
     * number is divided by the number
     *
     * @param rhs right handle side operand for reducing
     * @return result of scale
     */
    public Double3 reduce(double rhs) {
        return new Double3(d1 / rhs, d2 / rhs, d3 / rhs);
    }

    /**
     * Product two floating point triads into a new triad where each couple of
     * numbers is multiplied
     *
     * @param rhs right handle side operand for product
     * @return result of product
     */
    public Double3 product(Double3 rhs) {
        return new Double3(d1 * rhs.d1, d2 * rhs.d2, d3 * rhs.d3);
    }


    /**
     * Checks whether all the numbers are lower than a test number
     *
     * @param k the test number
     * @return true if all the numbers are less than k, false otherwise
     */

    public boolean lowerThan(double k) {
        return d1 < k && d2 < k && d3 < k;
    }


    @Override
    public Map<Schema, Function<JSONObject, ? extends Object>> getCreationMap() {
        return Map.of(
                SchemaLoader.load(new JSONObject(
                        "{" +
                                "   \"$schema\": \"Double3\"," +
                                "   \"type\": \"object\"," +
                                "   \"properties\": {" +
                                "      \"value\": {" +
                                "          \"type\": \"number\"" +
                                "      }" +
                                "   }," +
                                "   \"required\": [" +
                                "       \"value\"" +
                                "   ], additionalProperties: false" +
                                "}")),
                json -> new Object[]{json.get("value")},
                SchemaLoader.load(new JSONObject(
                        "{" +
                                "   \"$schema\": \"Double3\"," +
                                "   \"type\": \"object\"," +
                                "   \"properties\": {" +
                                "      \"d1\": {" +
                                "          \"type\": \"number\"" +
                                "      }," +
                                "      \"d2\": {" +
                                "          \"type\": \"number\"" +
                                "      }," +
                                "      \"d3\": {" +
                                "          \"type\": \"number\"" +
                                "      }" +
                                "   }," +
                                "   \"required\": [" +
                                "      \"d1\", \"d2\", \"d3\" " +
                                "   ], additionalProperties: false" +
                                "}")),
                json -> new Object[]{json.get("d1"), json.get("d2"), json.get("d3")}
        );
    }
}

