package json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {


    public static List<JSONObject> getJsonObjects(JSONArray jsonArray) {
        return IntStream.range(0, jsonArray.length())
                .mapToObj(jsonArray::getJSONObject)
                .collect(Collectors.toList());
    }


}
