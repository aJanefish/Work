package com.test.enums;

import com.test.utils.Print;
import com.test.utils.PrintUtils;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class EnumMain {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
    }


    //EnumMap 使用
    private static void test7() {
        Print.println("test7............................");
        //使用第一种构造
        Map<Color,Integer> enumMap1=new EnumMap<>(Color.class);
        //使用第二种构造
        Map<Color,Integer> enumMap2=new EnumMap<>(enumMap1);
        //使用第三种构造
        Map<Color,Integer> hashMap = new HashMap<>();
        hashMap.put(Color.GREEN, 2);
        hashMap.put(Color.BLUE, 3);
        Map<Color, Integer> enumMap = new EnumMap<>(hashMap);
        enumMap.put(Color.RED,5);
        Print.println(enumMap);
        Print.println(enumMap.keySet());


    }

    //枚举与接口 合用 2
    private static void test6() {
        Print.println("test6............................");
        Meal meal = Meal.APPETIZER;
        //是否是枚举类
        if (meal.getClass().isEnum()) {
            Meal[] meals = meal.getClass().getEnumConstants();
            Print.println(Arrays.toString(meals));


            for (Meal meal1 : meals) {
                if(true){
                    Print.println(Arrays.toString(meal1.getValues()));
                }else {
                    Class<? extends Food> aClass = meal1.getKind();
                    if (aClass.isEnum()) {

                        if (aClass.equals(Food.Appetizer.class)) {
                            Food.Appetizer[] sss = (Food.Appetizer[]) aClass.getEnumConstants();
                            Print.println(Arrays.toString(sss));
                        }

                        if (aClass.equals(Food.Dessert.class)) {
                            Food.Dessert[] sss = (Food.Dessert[]) aClass.getEnumConstants();
                            Print.println(Arrays.toString(sss));
                        }

                        if (aClass.equals(Food.MainCourse.class)) {
                            Food.MainCourse[] sss = (Food.MainCourse[]) aClass.getEnumConstants();
                            Print.println(Arrays.toString(sss));
                        }

                        if (aClass.equals(Food.Coffee.class)) {
                            Food.Coffee[] sss = (Food.Coffee[]) aClass.getEnumConstants();
                            Print.println(Arrays.toString(sss));
                        }
                    }
                }
            }
        }


    }

    //枚举与接口 合用 1
    private static void test5() {
        Print.println("test5............................");
        Food food = Food.Appetizer.SALAD;
        Print.println(food + " , " + ((Food.Appetizer) food).name());
        food.eat();
        food = Food.Coffee.BLACK_COFFEE;
        Print.println(food);
        food.eat();
        food = Food.MainCourse.BURRITO;
        Print.println(food);
        food.eat();
        food = Food.Dessert.GELATO;
        Print.println(food);
        food.eat();
    }

    //枚举带有抽象方法
    private static void test4() {
        EnumTest[] enumTests = EnumTest.values();
        Print.println(Arrays.toString(enumTests));
        for (EnumTest enumTest : enumTests) {
            Print.println(enumTest + " , " + enumTest.getInfo() + " , " + enumTest.ordinal());
        }
    }

    //进阶使用枚举
    private static void test3() {
        Day2[] day2s = Day2.values();
        for (Day2 day2 : day2s) {
            Print.println(day2 + " , " + day2.name());
        }
    }

    //通过枚举对象获取所有的枚举元素
    private static void test2() {
        //正常使用
        Day[] ds = Day.values();
        //向上转型Enum
        Enum e = Day.MONDAY;
        //无法调用,没有此方法
        // e.values();
        // 获取class对象引用
        Class<?> clasz = e.getDeclaringClass();
        Print.println(clasz);
        if (clasz.isEnum()) {
            Day[] dsz = (Day[]) clasz.getEnumConstants();
            Print.println("dsz:" + Arrays.toString(dsz));
        }

    }

    private static void test1() {
        Day day = Day.TUESDAY;

        Print.println(day + " , " + day.name() + ", " + day.ordinal());
        Day day1 = Day.valueOf("TUESDAY");
        Print.println(day1);
        Day[] days = Day.values();
        Print.println("day2:" + Arrays.toString(days));
        for (int i = 0; i < days.length; i++) {
            Print.println(days[i] + "," + days[i].ordinal());
        }
        new PrintUtils().show(days);
    }

}
