package com.test.enums;

import com.test.utils.Print;

public interface Food {
    enum Appetizer implements Food {
        SALAD, SOUP, SPRING_ROLLS;

        @Override
        public void eat() {
            Print.println("Appetizer eat.."+name());
        }
    }
    enum MainCourse implements Food {
        LASAGNE, BURRITO, PAD_THAI,
        LENTILS, HUMMOUS, VINDALOO;

        @Override
        public void eat() {
            Print.println("MainCourse eat.."+name());
        }
    }
    enum Dessert implements Food {
        TIRAMISU, GELATO, BLACK_FOREST_CAKE,
        FRUIT, CREME_CARAMEL;

        @Override
        public void eat() {
            Print.println("Dessert eat.."+name());
        }
    }
    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
        LATTE, CAPPUCCINO, TEA, HERB_TEA;

        @Override
        public void eat() {
            Print.println("Coffee eat.."+name());
        }
    }

    void eat();
}
