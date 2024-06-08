package primitives;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorTest {


    @Test
    void testjson() throws Exception {

        Color color = new Color(new JSONObject("{\"r\": 4, \"g\": 5.5, \"b\": 6}"));
        assertEquals(4, color.getColor().getRed());
        assertEquals(5, color.getColor().getGreen());
        assertEquals(6, color.getColor().getBlue());


        color = new Color(new JSONObject("{\"rgb\": {\"value\": 4.7}}"));
        assertEquals(4, color.getColor().getRed());
        assertEquals(4, color.getColor().getGreen());
        assertEquals(4, color.getColor().getBlue());


        color = new Color(new JSONObject("{\"rgb\": {\"d1\": 4, \"d2\": 5.5, \"d3\": 6}}"));
        assertEquals(4, color.getColor().getRed());
        assertEquals(5, color.getColor().getGreen());
        assertEquals(6, color.getColor().getBlue());

    }

}
