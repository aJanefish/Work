package com.test.enums;

public enum Meal {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    private final Class<? extends Food> kind;

    private Food[] values;
    private Meal(Class<? extends Food> kind) {
        //通过class对象获取枚举实例
        this.kind = kind;
        //获取所有的枚举类型
        values = kind.getEnumConstants();
    }

    public Class<? extends Food> getKind() {
        return kind;
    }

    public Food[] getValues() {
        return values;
    }

    public void setValues(Food[] values) {
        this.values = values;
    }

    //    public interface Food {
//        enum Appetizer implements Food {
//            SALAD, SOUP, SPRING_ROLLS;
//        }
//        enum MainCourse implements Food {
//            LASAGNE, BURRITO, PAD_THAI,
//            LENTILS, HUMMOUS, VINDALOO;
//        }
//        enum Dessert implements Food {
//            TIRAMISU, GELATO, BLACK_FOREST_CAKE,
//            FRUIT, CREME_CARAMEL;
//        }
//        enum Coffee implements Food {
//            BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
//            LATTE, CAPPUCCINO, TEA, HERB_TEA;
//        }
//    }
}
