package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UtilTest {

    @Test
    void testAlignDouble(){
        assertEquals(3, Util.alignDouble(3.0000000058));
        assertEquals(3, Util.alignDouble(2.9999999958));
        assertEquals(2.5325634, Util.alignDouble(2.53256340558));

    }

    @Test
    void testAlignHoleNumber(){
        assertEquals(2, Util.alignHoleNumber(2.00000000000002), "Not a hole number");
        assertEquals(2, Util.alignHoleNumber(1.99999999999999), "Not a hole number");
    }

    @Test
    void testSolve4degreeQuarticFunction() {
        var solutions = Util.solve4degreeQuarticFunction(1, -4, 8, -8, 3);

    }
}
