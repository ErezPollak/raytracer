package common;

import java.util.ArrayList;
import java.util.List;

public class Utils {


    public static List<String> getClassNames(String packageName) {
        List<String> classNames = new ArrayList<>();
        String path = packageName.replace('.', '/');
        java.net.URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
        if (resource != null) {
            java.io.File directory = new java.io.File(resource.getFile());
            if (directory.exists()) {
                for (java.io.File file : directory.listFiles()) {
                    if (file.getName().endsWith(".class")) {
                        String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                        classNames.add(className);
                    }
                }
            }
        }
        return classNames;
    }

    public static List<Class<?>> getSubclassesOf(Class<?> superclass) {
        List<Class<?>> subclasses = new ArrayList<>();
        for (Package pkg : Package.getPackages()) {
            for (String className : getClassNames(pkg.getName())) {
                try {
                    Class<?> cls = Class.forName(className);
                    if (superclass.isAssignableFrom(cls) && !superclass.equals(cls)) {
                        subclasses.add(cls);
                    }
                } catch (ClassNotFoundException e) {
                    // Ignore, class may not be found
                }
            }
        }
        return subclasses;
    }
}
