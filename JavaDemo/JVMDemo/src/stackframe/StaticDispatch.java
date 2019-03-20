package stackframe;

public class StaticDispatch {

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human human) {
        System.out.println("Hello , guy!:" + human);
    }

    public void sayHello(Man man) {
        System.out.println("Hello , gentleman!:" + man);
    }

    public void sayHello(Woman woman) {
        System.out.println("Hello , lady!:" + woman);
    }

    public static void main(String args[]) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch staticDispatch = new StaticDispatch();
        staticDispatch.sayHello(man);
        staticDispatch.sayHello(woman);
    }
}
