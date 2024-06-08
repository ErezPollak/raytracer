import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import primitives.Color;
import primitives.Double3;
import primitives.Point;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.sql.SQLOutput;


public final class Main {

    public static void main(String[] args) throws Exception {
//
//        for (Constructor<?> constructor : Double3.class.getDeclaredConstructors()) {
//            //System.out.println("Constructor: " + constructor.getName());
//
//
//            for (Class cls : constructor.getParameterTypes()) {
//                //System.out.println("Parameter: " + cls.getName());
//            }
//
//            // Iterate over parameters
//            for (Parameter parameter : constructor.getParameters()) {
//                // System.out.println("  Parameter type: " + parameter.getType().getName());
//            }
//        }


        Color c = new Color(new JSONObject("{\"r\": 4, \"g\": 5.5, \"b\": 6}"));
        System.out.println(c);

//
//        String scheme1 =
//                "{" +
//                "   \"$schema\": \"as\"," +
//                "   \"type\": \"object\"," +
//                "   \"properties\": {" +
//                "      \"value\": {" +
//                "          \"type\": \"number\"" +
//                "      }" +
//                "   }," +
//                "   \"required\": [" +
//                "       \"value\"" +
//                "   ]" +
//                "}";
//
//        String scheme2 =
//                "{" +
//                "   \"$schema\": \"asdfasd\"," +
//                "   \"type\": \"object\"," +
//                "   \"properties\": {" +
//                "      \"d1\": {" +
//                "          \"type\": \"number\"" +
//                "      }," +
//                "      \"d2\": {" +
//                "          \"type\": \"number\"" +
//                "      }," +
//                "      \"d3\": {" +
//                "          \"type\": \"number\"" +
//                "      }" +
//                "   }," +
//                "   \"required\": [" +
//                "      \"d1\", \"d2\", \"d3\" " +
//                "   ]" +
//                "}";
//
//        JSONObject rawSchema = new JSONObject(scheme2);
//        // Create a Schema instance
//        Schema schema = SchemaLoader.load(rawSchema);
//
//        // Load your JSON data from a file or a string
//        JSONObject jsonData = new JSONObject(json2);
//
//        // Validate JSON against the schema
//        try {
//            schema.validate(jsonData);
//            System.out.println("JSON is valid against the schema.");
//        } catch (ValidationException e) {
//            System.out.println("JSON is not valid against the schema: " + e.getMessage());
//            e.getCausingExceptions().forEach(ex -> System.out.println("   " + ex.getMessage()));
//        }

    }
}
