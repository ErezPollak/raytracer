import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import primitives.Color;
import primitives.Double3;
import primitives.Point;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.sql.SQLOutput;

class MyClass {
    public MyClass(Point... ints) {
        // Constructor logic
        for (Point i : ints) {
            System.out.println(i);
        }
    }
}

public final class Main {

    public static void main(String[] args)  {



        try {
            // Get the constructor
            Constructor<MyClass> constructor = MyClass.class.getConstructor(Point[].class);

            // Create an array of integers
            Point[] ints = {new Point(2,3,4), new Point(4,5,6), new Point(4,1,2), new Point(5,2,3)};

            // Call the constructor with the array
            MyClass instance = constructor.newInstance(new Object[] { ints });

            // Print the instance
            System.out.println(instance);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace(); // Print detailed message for debugging
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }


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
//
//        JSONObject a = new JSONObject(
//                "{" +
//                        "   \"vertices\": " +
//                        "   [" +
//                        "      {" +
//                        "           \"x\":4," +
//                        "           \"y\":5," +
//                        "           \"z\": 6.4" +
//                        "      }, " +
//                        "      {" +
//                        "           \"d\":{" +
//                        "               \"value\":3}" +
//                        "      }, " +
//                        "      {" +
//                        "           \"d\":{" +
//                        "               \"value\":3}" +
//                        "      }" +
//                        "   ]" +
//                        "}");
//        System.out.println(a);




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
