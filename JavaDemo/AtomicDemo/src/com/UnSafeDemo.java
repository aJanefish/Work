package com;


import com.bean.Person;
import com.bean.User;
import com.utils.Print;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnSafeDemo {
    public  static  void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        // 通过反射得到theUnsafe对应的Field对象
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        Print.P(field);
        // 设置该Field为可访问
        field.setAccessible(true);

        // 通过Field得到该Field对应的具体对象，传入null是因为该Field为static的
        Unsafe unsafe = (Unsafe) field.get(null);
        Print.P(unsafe);
        //通过allocateInstance直接创建对象
        User user = (User) unsafe.allocateInstance(User.class);
        Print.P(user);

//        Integer integer = (Integer) unsafe.allocateInstance(Integer.class);
//        Print.P(integer);
//
//
//        AtomicMain atomicMain = (AtomicMain) unsafe.allocateInstance(AtomicMain.class);
//        Print.P(atomicMain);

        Class userClass = user.getClass();
        //Print.P("userClass.getClassLoader:"+userClass.getClassLoader());
        Field name = userClass.getDeclaredField("name");
        Field age = userClass.getDeclaredField("age");
        Field id = userClass.getDeclaredField("id");
        Field person = userClass.getDeclaredField("person");
        //获取实例变量name和age在对象内存中的偏移量并设置值
        unsafe.putInt(user,unsafe.objectFieldOffset(age),18);
        unsafe.putObject(user,unsafe.objectFieldOffset(name),"android TV");
        unsafe.putObject(user,unsafe.objectFieldOffset(person),new Person(1,"zhangyu"));

        Print.P(user);
        name.setAccessible(true);
        Print.P(name.get(user));
        age.setAccessible(true);
        Print.P(age.get(user));

        id.setAccessible(true);
        Print.P(id.get(user));
        person.setAccessible(true);
        Print.P(person.get(user));

        // 这里返回 User.class，
        Object staticBase = unsafe.staticFieldBase(id);
        Print.P("staticBase:"+staticBase+" , "+staticBase.hashCode() +" , "+ user.hashCode());

        //获取静态变量id的偏移量staticOffset
        long staticOffset = unsafe.staticFieldOffset(userClass.getDeclaredField("id"));
        Print.P("staticOffset:"+staticOffset);
        //获取静态变量的值
        System.out.println("设置前的ID:"+unsafe.getObject(staticBase,staticOffset));
        //设置值
        unsafe.putObject(staticBase,staticOffset,"ZhangYu");
        //获取静态变量的值
        System.out.println("设置后的ID:"+unsafe.getObject(staticBase,staticOffset));
        //输出USER
        System.out.println("输出USER:"+user.toString());



    }
}
