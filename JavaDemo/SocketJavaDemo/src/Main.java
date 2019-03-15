import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Socket socket = new Socket();
        List<Integer> ss = getNarcissisticNumbers(8);
        System.out.println(ss);
        int tmp = (int) Math.pow(9, 8);
        System.out.println(tmp);

    }

    /**
     * 求水仙花数
     * */
    public static List<Integer> getNarcissisticNumbers(int n) {
        // write your code here

        int max = (int) Math.pow(10, n);

        int min = (int) Math.pow(10, n - 1);
        if (n == 1) {
            min = 0;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = min; i < max; i++) {
            if (isNarcissistic(i, n, max)) {
                list.add(i);
            }
        }
        return list;
    }


    public static boolean isNarcissistic(int values, int n, int max) {

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.pow(getNumByIndex(values, i), n);
            if (sum >= max) {
                return false;
            }
        }

        return sum == values;
    }


    public static int getNumByIndex(int values, int index) {

        int max = (int) Math.pow(10, index + 1);
        int min = (int) Math.pow(10, index);

        int data = values % max / min;
        //System.out.println(values + ":" + index + ":" + min + ":" + max + ":" + data);

        //values
        return data;
    }
}
