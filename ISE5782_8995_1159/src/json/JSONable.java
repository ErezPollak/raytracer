package json;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static json.Utils.validate;

public abstract class JSONable {

    //TODO add support for dynamic initialization and delete the object initialization.
    private JSONable object;


    private Object[] params;

    public JSONable() {
        params = new Object[0];
    }

    public JSONable(JSONObject json) {

        Map<Schema, Function<JSONObject, ? extends Object>> creationMap = getCreationMap();

        String overAllError = "";

        for (Map.Entry<Schema, Function<JSONObject, ? extends Object>> entry : creationMap.entrySet()) {
            try {
                validate(entry.getKey(), json);
                //TODO log found schema
                Object returned = entry.getValue().apply(json);
                if (returned instanceof Object[])
                    this.params = (Object[]) returned;
                else if (returned instanceof JSONable)
                    this.object = (JSONable) returned;
                break;
            } catch (ValidationException e) {
                overAllError += "JSON is not valid against the schema: " + e.getMessage();
                for (Exception ex : e.getCausingExceptions()) {
                    overAllError += "   " + ex.getMessage();
                }
            }
        }

        if (this.object == null && (this.params == null || this.params.length == 0)) {
            System.out.println("json was not crated" + json.toString());
            throw new ValidationException("JSON object is not valid against the schemas" + overAllError);
        }

    }

    public JSONable getObject() {
        return this.object;
    }


    public abstract Map<Schema, Function<JSONObject, ? extends Object>> getCreationMap();

    public <T> T getJsonCreatedInstance(Class<T> clazz) {
        if (this.object != null) {
            System.out.println("JSON object is already created using object itself");
            return null;
        }

        /*
            trys to look for a constranctor that expects one Object type with ...
         */
        try {
            return clazz.getConstructor(this.params.getClass()).newInstance(new Object[]{this.params});
        } catch (InvocationTargetException e) {
            System.out.println(e.getTargetException().getMessage());
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException _) {
        }

        /*
        the classic case when i need to create an array with all the types of the params and use that to search for the
        conatructor.
         */
        Class<?>[] paramTypes = null;
        try {
            paramTypes =
                    Arrays.stream(this.params)
                            .map(cls -> getPrimitiveClass(cls.getClass()))
                            .toArray(Class<?>[]::new);
            return clazz.getConstructor(paramTypes).newInstance(this.params);
        } catch (NullPointerException e) {
            System.out.println("jsonable was not created properly");
            return null;
        } catch (NoSuchMethodException e) {
            System.out.println("DID NOT FOUND THE CORRECT CONSTRUCTOR for params " + Arrays.toString(paramTypes) + ". \nConstranctors: ");
            for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                for (Parameter parameter : constructor.getParameters()) {
                    System.out.print("   " + parameter.getType().getName());
                }
                System.out.println();
            }
            throw new RuntimeException(e);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<?> getPrimitiveClass(Class<?> classObj) {
        Map<Class, Class> wrapperToPrimitiveMap = new HashMap<>();
        wrapperToPrimitiveMap.put(Integer.class, double.class);
        wrapperToPrimitiveMap.put(Double.class, double.class);

        return wrapperToPrimitiveMap.getOrDefault(classObj, classObj);
    }

}
