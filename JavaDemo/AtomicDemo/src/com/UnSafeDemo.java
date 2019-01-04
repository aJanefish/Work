package com;


import com.bean.Person;
import com.bean.User;
import com.utils.Print;
import sun.dc.pr.PRError;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class UnSafeDemo {

    private static Unsafe unsafe;

    private Person person;

    private static final long personOffset;

    static {
        // 通过反射得到theUnsafe对应的Field对象
        Field field = null;
        try {
            field = Unsafe.class.getDeclaredField("theUnsafe");
            Print.P(field);
            // 设置该Field为可访问
            field.setAccessible(true);
            // 通过Field得到该Field对应的具体对象，传入null是因为该Field为static的
            unsafe = (Unsafe) field.get(null);

            personOffset = unsafe.objectFieldOffset
                    (UnSafeDemo.class.getDeclaredField("person"));
        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    private final boolean compareAndSetPerson(Person person) {
        return unsafe.compareAndSwapObject(this, personOffset, null, person);
    }

    private final boolean compareAndSetPerson(Person person ,Person updatePerson  ) {
        return unsafe.compareAndSwapObject(this, personOffset, person, updatePerson);
    }

    public boolean setPersion(Person person){
        return compareAndSetPerson(person);
    }

    public boolean setPersion(Person person,Person UpdatePerson){
        return compareAndSetPerson(person,UpdatePerson);
    }




    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        test1();
        test2();
    }

    private static void test2() {
        Print.P("test2-----------------------------------------------------");
        UnSafeDemo unSafeDemo = new UnSafeDemo();
        Print.P(unSafeDemo.person);
        Person person1 = new Person(26,"zhangyu");
        Print.P(unSafeDemo.setPersion(person1));

        //已经被更改 替换失败
        Print.P(unSafeDemo.setPersion(new Person(22,"zhangyu")));

        Person person = unSafeDemo.person;
        Person updatePerson = new Person(22,"zhangyu");
        Print.P(unSafeDemo.setPersion(person,updatePerson));
        Print.P(person1);
        Print.P(person+"-->"+updatePerson);
        Print.P(unSafeDemo.person);

    }

    private static void test1() throws InstantiationException, NoSuchFieldException, IllegalAccessException {
//        // 通过反射得到theUnsafe对应的Field对象
//        Field field = Unsafe.class.getDeclaredField("theUnsafe");
//        Print.P(field);
//        // 设置该Field为可访问
//        field.setAccessible(true);
//
//        // 通过Field得到该Field对应的具体对象，传入null是因为该Field为static的
//        Unsafe unsafe = (Unsafe) field.get(null);
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
        unsafe.putInt(user, unsafe.objectFieldOffset(age), 18);
        unsafe.putObject(user, unsafe.objectFieldOffset(name), "android TV");
        unsafe.putObject(user, unsafe.objectFieldOffset(person), new Person(1, "zhangyu"));

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
        Print.P("staticBase:" + staticBase + " , " + staticBase.hashCode() + " , " + user.hashCode());

        //获取静态变量id的偏移量staticOffset
        long staticOffset = unsafe.staticFieldOffset(userClass.getDeclaredField("id"));
        Print.P("staticOffset:" + staticOffset);
        //获取静态变量的值
        System.out.println("设置前的ID:" + unsafe.getObject(staticBase, staticOffset));
        //设置值
        unsafe.putObject(staticBase, staticOffset, "ZhangYu");
        //获取静态变量的值
        System.out.println("设置后的ID:" + unsafe.getObject(staticBase, staticOffset));
        //输出USER
        System.out.println("输出USER:" + user.toString());
    }
}
