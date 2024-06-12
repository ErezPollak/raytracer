package primitives;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void jsonTest(){
        Ray ray = new Ray(new JSONObject("{\"p\":{\"d\": {\"value\": 4.7}}, \"v\":{\"d\": {\"value\": 5}}}"));
        assertEquals(4.7, ray.getPoint().getX());
        assertEquals(4.7, ray.getPoint().getY());
        assertEquals(4.7, ray.getPoint().getZ());

        assertEquals(Util.alignDouble(0.5773502691896257), Util.alignDouble(ray.getVector().getX()));
        assertEquals(Util.alignDouble(0.5773502691896257), Util.alignDouble(ray.getVector().getY()));
        assertEquals(Util.alignDouble(0.5773502691896257), Util.alignDouble(ray.getVector().getZ()));

        ray = new Ray(new JSONObject("{\"head\":{\"d\": {\"value\": 4.7}}, \"direction\":{\"d\": {\"value\": 5}}, \"normal\":{\"d\": {\"value\": 1}}}"));

        assertEquals(Util.alignDouble(0.5773502691896257), Util.alignDouble(ray.getVector().getX()));
        assertEquals(Util.alignDouble(0.5773502691896257), Util.alignDouble(ray.getVector().getY()));
        assertEquals(Util.alignDouble(0.5773502691896257), Util.alignDouble(ray.getVector().getZ()));

    }



//
//    /**
//     * testing the function that returns the clothest point from a list.
//     * @author Erez Polak
//     */
//    @Test
//    void findClosestPoint() {
//
//        Point p1 = new Point(0,3,4) , p2 = new Point(0,2,3), p3 = new Point(0,4,5);
//        List<Point> list = List.of(p1,p2,p3);
//        Ray r = new Ray(new Point(0,1,2) , new Vector(0,1,1));
//
//        // ============ Equivalence Partitions Tests ==============
//
//        assertEquals(p2 , r.findClosestPoint(list) , "Equivalence Partitions Tests Failed.");
//
//        // =============== Boundary Values Tests ==================
//
//        //TC01: the list is empty:
//        List<Point> emptyList = List.of();
//        assertEquals(null , r.findClosestPoint(emptyList) , "Boundary Values Tests TC01 Failed.");
//
//        //TC02: the first point is required:
//        list = List.of(p2,p1,p3);
//        assertEquals(p2 , r.findClosestPoint(list) , "Boundary Values Tests TC02 Failed.");
//
//        //TC03: the last point is required:
//        list = List.of(p3,p1,p2);
//        assertEquals(p2 , r.findClosestPoint(list) , "Boundary Values Tests TC03 Failed.");
//
//    }




}