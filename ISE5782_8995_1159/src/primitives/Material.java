package primitives;

import json.JSONable;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static json.Utils.copyProps;

/**
 * the properties of teh material the object is made from.
 */
public class Material extends JSONable {

    public Material() {
    }

    public Material(JSONObject json) {
        super(json);
        this.initObject(this);
    }


    /**
     * the defensive coefficient
     */
    public Double3 kD = Double3.ZERO;
    /**
     * the shininess coefficient.
     */
    public Double3 kS = Double3.ZERO;
    /**
     * the times to multiply the cos in itself.
     */
    public int nShininess = 0;

    /**
     * the transparency coefficient.
     */
    public Double3 kT = Double3.ZERO;
    /**
     * the refraction coefficient.
     */
    public Double3 kR = Double3.ZERO;

    public Material(Double3 kd, Double3 ks, int nShininess , Double3 kt, Double3 kr){
        this.kD = kd;
        this.kS = ks;
        this.nShininess = nShininess;
        this.kT = kt;
        this.kR = kr;
    }

    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    public Material setKt(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    public Material setKr(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }

    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    @Override
    public Map<Schema, Function<JSONObject, ? extends Object>> getCreationMap() {
        return Map.of(
                SchemaLoader.load(new JSONObject(
                        "{" +
                                "   \"$schema\": \"Material\"," +
                                "   \"type\": \"object\"," +
                                "   \"properties\": {" +
                                "      \"kD\": {" +
                                "          \"type\": [\"object\", \"number\"]" +
                                "      }," +
                                "      \"kS\": {" +
                                "          \"type\": [\"object\", \"number\"]" +
                                "      }," +
                                "      \"nShininess\": {" +
                                "          \"type\": \"integer\"" +
                                "      }," +
                                "      \"kT\": {" +
                                "          \"type\": [\"object\", \"number\"]" +
                                "      }," +
                                "      \"kR\": {" +
                                "          \"type\": [\"object\", \"number\"]" +
                                "      }" +
                                "   }," +
                                "   additionalProperties: false" +
                                "}")),
                json -> {
                    Material m = new Material();
                    if (json.has("kD")) if (json.get("kD") instanceof Number) m.setKd(json.getDouble("kD")); else m.setKd(new Double3(json.getJSONObject("kD")));
                    if (json.has("kS")) if (json.get("kS") instanceof Number) m.setKs(json.getDouble("kS")); else m.setKs(new Double3(json.getJSONObject("kS")));
                    if (json.has("kT")) if (json.get("kT") instanceof Double) m.setKt(json.getDouble("kT")); else m.setKt(new Double3(json.getJSONObject("kT")));
                    if (json.has("kR")) if (json.get("kR") instanceof Double) m.setKr(json.getDouble("kR")); else m.setKr(new Double3(json.getJSONObject("kR")));
                    if (json.has("nShininess")) m.setShininess(json.getInt("nShininess"));
                    return m;

                }
        );
    }
}
