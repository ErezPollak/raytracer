package primitives;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaterialTest {

    @Test
    void testMaterial() {

        Material mat = new Material(new JSONObject("{\"kD\": {\"d1\": 4, \"d2\": 5.5, \"d3\": 6},\"kS\":5, \"nShininess\": 5}"));
        assertEquals(5,mat.kS.d1);
        assertEquals(5,mat.kS.d2);
        assertEquals(5,mat.kS.d3);

        assertEquals(4,mat.kD.d1);
        assertEquals(5.5,mat.kD.d2);
        assertEquals(6,mat.kD.d3);

        assertEquals(5,mat.nShininess);

        assertEquals(0,mat.kR.d1);
        assertEquals(0,mat.kR.d1);
        assertEquals(0,mat.kR.d1);


    }

}