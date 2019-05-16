package com.example.zy.rxjavademo.viewinjection;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * View 注入框架
 */

public class ViewUtils {
    private static final String TAG = "ViewUtils";

    public static void register(@NonNull Activity target) {

        View sourceView = target.getWindow().getDecorView();
        show(target);

        try {
            createBinding(target, sourceView);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Log.d(TAG, e.toString());
        }


    }


    private static void createBinding(Activity target, View sourceView) throws IllegalAccessException {
        initLayout(target);
        handleFields(target);
        handleMethods(target);
    }

    private static void initLayout(Activity target) {
        Class<? extends Activity> targetClass = target.getClass();
        if (targetClass.isAnnotationPresent(ViewLayout.class)) {
            ViewLayout viewLayout = targetClass.getAnnotation(ViewLayout.class);
            if (viewLayout.value() != -1) {
                target.setContentView(viewLayout.value());
            }
        }
    }


    private static void handleMethods(final Activity target) {
        Method[] declaredMethods = target.getClass().getDeclaredMethods();
        for (final Method method : declaredMethods) {
            if (method.isAnnotationPresent(ViewMethod.class)) {
                ViewMethod viewMethod = method.getAnnotation(ViewMethod.class);
                show(method + "    " + viewMethod);
                View view = target.findViewById(viewMethod.value());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            if (!method.isAccessible()) {
                                method.setAccessible(true);
                            }
                            method.invoke(target, v);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }


    /**
     * 注入view
     */
    private static void handleFields(Activity target) throws IllegalAccessException {
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            show(field);
            //遍历所有属性，找到标有ViewField注解的属性
            if (field.isAnnotationPresent(ViewField.class)) {
                ViewField viewField = field.getAnnotation(ViewField.class);
                field.setAccessible(true);
                field.set(target, target.findViewById(viewField.value()));
            }
        }
    }


    private static void show(Object object[]) {
        Log.d(TAG, "" + Arrays.toString(object));
    }

    private static void show(Object object) {
        Log.d(TAG, "" + object);
    }

}
