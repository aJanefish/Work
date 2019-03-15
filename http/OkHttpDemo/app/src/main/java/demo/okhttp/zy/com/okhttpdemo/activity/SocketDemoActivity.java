package demo.okhttp.zy.com.okhttpdemo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import demo.okhttp.zy.com.okhttpdemo.R;

public class SocketDemoActivity extends BaseActivity {

    private static String TAG = "SocketDemoActivity";
    private TextView title;

    public static final String IP_ADDR = "192.168.201.121";
    public static final int PORT = 8989;
    private ExecutorService executorService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_demo);
        title = findViewById(R.id.activity_socket_demo_title);
        initDate();
    }

    @Override
    protected String getLog() {
        return "MainActivity2";
    }


    private void initDate() {
        title.setText(IP_ADDR + ":" + PORT);
        executorService = Executors.newCachedThreadPool();
    }

    public void send1(View view) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                send2SingouServer("1");
            }
        });
    }

    public void send2(View view) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                send2SingouServer("2");
            }
        });
    }

    public void send3(View view) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                int x = 0;
                while (x++ < 500) {
                    send2SingouServer("{\"name\":\"zhangyu\"};adsgf;lala;gdlagdadsgfagfas2019-03-13 17:14:41.846 29235-30031/demo.okhttp.zy.com.okhttpdemo D/SocketDemoActivity");
                }
            }
        });
    }


    public static void send2SingouServer(String values) {

        Socket socket = null;
        try {
            //创建一个流套接字并将其连接到指定主机上的指定端口号
            socket = new Socket(IP_ADDR, PORT);
            Log.d(TAG, "连接已经建立");

            DataOutputStream outputStream = null;
            outputStream = new DataOutputStream(socket.getOutputStream());

            outputStream.write(values.getBytes());
            outputStream.flush();
            Log.d(TAG, "传输数据完毕");


        } catch (Exception e) {
            Log.d(TAG, "客户端异常:" + e);

        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    socket = null;
                    Log.d(TAG, "客户端 finally 异常:" + e.getMessage());
                }
            }
        }
    }


}
