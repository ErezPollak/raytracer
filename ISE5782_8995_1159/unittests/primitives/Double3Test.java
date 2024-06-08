package primitives;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Double3Test {

    @Test
    void testDouble3() throws Exception {


        Double3 d = new Double3(new JSONObject("{\"d1\": 4, \"d2\": 5.5, \"d3\": 6}"));
        assertEquals(4, d.d1, "d1");
        assertEquals(5.5, d.d2, "d2");
        assertEquals(6, d.d3, "d3");


        d = new Double3(new JSONObject("{\"value\": 4.5}"));
        assertEquals(4.5, d.d1, "d1");
        assertEquals(4.5, d.d2, "d2");
        assertEquals(4.5, d.d3, "d3");

    }


}
