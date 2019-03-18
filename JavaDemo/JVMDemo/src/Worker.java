import utils.P;

public class Worker {
    static {
        P.pln("Worker Static");
    }

    public Worker() {
        System.out.println("<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>【重磅消息】我被构造了");
    }


    public void doit() {
        System.out.println(this.getClass().getClassLoader().toString() + "--->----------------->666666  666" );
    }
}

