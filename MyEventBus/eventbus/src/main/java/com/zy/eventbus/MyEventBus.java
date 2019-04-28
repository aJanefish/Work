package com.zy.eventbus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MyEventBus {

    private SubscriberMethodFinder subscriberMethodFinder;
    private HashMap<Object, HashMap<Class, Method>> objectHashMap;


    public MyEventBus() {
        subscriberMethodFinder = new SubscriberMethodFinder();
        objectHashMap = new HashMap<>();
    }

    private static MyEventBus defaultInstance;

    public static MyEventBus getDefault() {
        if (defaultInstance == null) {
            synchronized (MyEventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new MyEventBus();
                }
            }
        }
        return defaultInstance;
    }


    public void register(Object subscriber) {
        System.out.println("subscriber:" + subscriber);
        Class<?> subscriberClass = subscriber.getClass();
        System.out.println("subscriberClass:" + subscriberClass);
        HashMap<Class, Method> methods = subscriberMethodFinder.findSubscriberMethods(subscriberClass);
        objectHashMap.put(subscriber, methods);
    }

    public void post(Object object) {
        Class<?> aClass = object.getClass();

        for (Object subscriber : objectHashMap.keySet()) {
            HashMap<Class, Method> classMethodHashMap = objectHashMap.get(subscriber);
            Method method = classMethodHashMap.get(aClass);
            if (method != null) {
                try {
                    method.invoke(subscriber,object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }finally {
                    P.pln("post finally");
                }
            }
        }

    }
}
