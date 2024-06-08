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

public abstract class JSONable {

    private Object[] params;

    public JSONable() {
        params = new Object[0];
    }

    public JSONable(JSONObject json) {

        Map<Schema, Function<JSONObject, Object[]>> creationMap = getCreationMap();

        String overAllError = "";

        for (Map.Entry<Schema, Function<JSONObject, Object[]>> entry : creationMap.entrySet()) {
            try {
                entry.getKey().validate(json);
                System.out.println("FoundSchema");
                this.params = entry.getValue().apply(json);
                break;
            } catch (ValidationException e) {
                overAllError += "JSON is not valid against the schema: " + e.getMessage();
                for (Exception ex : e.getCausingExceptions()) {
                    overAllError += "   " + ex.getMessage();
                }
            }
        }

        if (this.params == null || this.params.length == 0) {
            throw new ValidationException("JSON object is not valid against the schemas" + overAllError);
        }

    }


    public abstract Map<Schema, Function<JSONObject, Object[]>> getCreationMap();

    public <T> T getJsonCreatedInstance(Class<T> clazz) {
        Class<?>[] paramTypes = null;
        try {
            paramTypes = Arrays.stream(this.params).map(cls -> getPrimitiveClass(cls.getClass())).toArray(Class<?>[]::new);
            return clazz.getConstructor(paramTypes).newInstance(this.params);
        } catch (NullPointerException e) {
            System.out.println("jsonable was not created properly");
            return null;
        } catch (NoSuchMethodException e) {
            System.out.println("DID NOT FOUND THE CORRECT CONSTRUCTOR for params " + Arrays.toString(paramTypes) + ". \nConstranctors: ");
            for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
                for (Parameter parameter : constructor.getParameters()) {
                    System.out.print("   " + parameter.getType().toString());
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
