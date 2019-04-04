package com.test;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ObjectAnalyzer {

    ArrayList<Object> visited;

    public ObjectAnalyzer() {
        this.visited = new ArrayList<>();
    }

    public String toString(Object obj) {
        if (obj == null) return "null";
        if (visited.contains(obj)) return "...";
        visited.add(obj);
        Class<?> aClass = obj.getClass();
        if (aClass == String.class) return (String) obj;

        if (aClass.isArray()) {
            String r = aClass.getComponentType() + "[]{";
            for (int i = 0; i < Array.getLength(obj); i++) {
                if (i > 0) r += ",";
                Object val = Array.get(obj, i);
                if (aClass.getComponentType().isPrimitive())
                    r += val;
                else
                    r += toString(val);
            }
            return r + "}";
        }


        String name = aClass.getName();
        do {
            name += "[";

            Field[] fields = aClass.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            //P.pln("fields.length:" + fields.length);
            for (Field field : fields) {
                //P.pln(field);
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (!name.endsWith("[")) name += ",";
                    name += field.getName() + "=";
                    try {
                        Object val = field.get(obj);
                        name += val;
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            name += "]";
            aClass = aClass.getSuperclass();
            //aClass = null;

        } while (aClass != null);
        return name;

    }
}
