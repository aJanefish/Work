package com.zy.eventbus;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;

class SubscriberMethodFinder {

    public SubscriberMethodFinder() {
    }


    public HashMap<Class,Method> findSubscriberMethods(Class<?> subscriberClass) {
        Method[] methods = subscriberClass.getDeclaredMethods();
        HashMap<Class,Method> hashMap = new HashMap<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(ZySubscribe.class)){
                P.pln(method);
                Class<?>[] parameterTypes = method.getParameterTypes();
                P.pln(Arrays.toString(parameterTypes));
                for (Class<?> parameterType : parameterTypes) {
                    hashMap.put(parameterType,method);
                }
            }
        }
        return hashMap;
    }
}
