package json;

import org.everit.json.schema.Schema;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {


    public static List<JSONObject> getJsonObjects(JSONArray jsonArray) {
        return IntStream.range(0, jsonArray.length())
                .mapToObj(jsonArray::getJSONObject)
                .collect(Collectors.toList());
    }

    public static void validate(Schema jsonSchma, JSONObject jsonObject) {
        jsonSchma.validate(jsonObject);
    }
//
//    public static void copyProps(JSONable current, JSONable other) {
//        try {
//            for (Field field : other.getClass().getDeclaredFields()) {
//                field.setAccessible(true);
//                field.set(current, field.get(other));
//            }
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }

}
