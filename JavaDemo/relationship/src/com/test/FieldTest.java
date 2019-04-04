package com.test;

import com.utils.P;

import java.lang.reflect.Field;
import java.util.Date;

public class FieldTest {
    public static void main(String args[]) throws NoSuchFieldException, IllegalAccessException {
        fieldTest1();
    }

    private static void fieldTest1() throws NoSuchFieldException, IllegalAccessException {
        Class<ReflectionBean> dateClass = ReflectionBean.class;

        Field field = dateClass.getDeclaredField("reflectionBean");
        Field dateField = dateClass.getDeclaredField("date");
        dateField.setAccessible(true);
        Field idField = dateClass.getDeclaredField("id");
        idField.setAccessible(true);
        Field nameField = dateClass.getDeclaredField("name");
        nameField.setAccessible(true);

        field.setAccessible(true);
        Object object = field.get(null);
        P.pln(object.getClass() + " - " + object);
        ReflectionBean reflectionBean = (ReflectionBean) object;
        P.pln(reflectionBean.getClass() + " - " + reflectionBean);
        dateField.set(reflectionBean,new Date());
        idField.set(reflectionBean,1000);
        nameField.set(reflectionBean,"zhangyu");
        P.pln(reflectionBean.getClass() + " - " + reflectionBean);

        Object id = idField.get(reflectionBean);
        P.pln(id.getClass() + " - " + id);

        Object name = nameField.get(reflectionBean);
        P.pln(name.getClass() + " - " + name);


        Object date = dateField.get(reflectionBean);
        P.pln(date.getClass() + " - " + date );


    }

}
