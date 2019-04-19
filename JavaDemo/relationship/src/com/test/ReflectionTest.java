package com.test;

import com.utils.P;
import sun.misc.Unsafe;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

public class ReflectionTest {
    public static void main(String args[]) throws ClassNotFoundException {


        Class<?>  dateClass = String.class;

        String className = dateClass.getName();
        P.pln(className);

        reflectionTest(className);
    }

    private static void reflectionTest(String className) throws ClassNotFoundException {

        Class<?> cl = Class.forName(className);
        Class superCl = cl.getSuperclass();

        String modifiers = Modifier.toString(cl.getModifiers());
        if (modifiers.length() > 0) P.p(modifiers + " ");
        P.p("class " + className);
        if (superCl != null && superCl != Object.class) P.p(" extents " + superCl.getName());

        P.p("\n{\n");

        printConstructors(cl);
        P.pln();
        printMethods(cl);
        P.pln();
        printFields(cl);
        P.pln("}");
    }

    private static void printFields(Class<?> cl) {
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            Class<?> type = field.getType();
            String name = field.getName();


            P.p("    ");
            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length() > 0) P.p(modifiers + " ");
            P.pln(type.getName() + " " + name + ";");

        }
    }

    private static void printMethods(Class<?> cl) {
        Method[] methods = cl.getDeclaredMethods();
        for (Method method : methods) {
            Class<?> returnType = method.getReturnType();
            String name = method.getName();
            P.p("    ");

            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length() > 0) P.p(modifiers + " ");
            P.p(returnType.getName() + " " + name + "(");

            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0) P.p(", ");
                P.p(parameterTypes[i].getName());
            }
            P.pln(")");


        }
    }

    private static void printConstructors(Class<?> cl) {
        Constructor<?>[] constructors = cl.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            P.p("    ");
            String modifiers = Modifier.toString(constructor.getModifiers());
            if (modifiers.length() > 0) P.p(modifiers + " ");
            P.p(constructor.getName() + "(");

            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (i > 0) P.p(", ");
                P.p(parameterTypes[i].getName());
            }
            P.pln(")");
        }
    }

}
