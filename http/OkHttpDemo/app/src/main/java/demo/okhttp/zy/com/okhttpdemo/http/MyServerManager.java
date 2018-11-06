package demo.okhttp.zy.com.okhttpdemo.http;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import demo.okhttp.zy.com.okhttpdemo.event.ServerEvent;
import demo.okhttp.zy.com.okhttpdemo.log.MyLog;

/**
 *
 * ServerSocket 服务端 短连接
 *
 *
 * */


public class MyServerManager implements Runnable {
    private static final String TAG = "MyServerManager";
    private final int MAX_THREAD_NUM = 8;
    private boolean mIsRunning;

    interface Method {
        String GET = "GET";
        String POST = "POST";
    }

    private ServerSocket mServerSocket;
    private Context mContext;
    private Executor mExecutor;

    /**
     * WebServer constructor.
     */
    public MyServerManager(Context context) {

        mContext = context;
        mExecutor = Executors.newFixedThreadPool(MAX_THREAD_NUM);
    }

    @Override
    public void run() {

        try {
            MyLog.d(TAG,"创建服务端");
            mServerSocket = new ServerSocket(9000);

            while (mIsRunning) {
                MyLog.d(TAG,"等待客户端接入......");
                final Socket socket = mServerSocket.accept();

                // 放入線程池中執行
                mExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            handle(socket);

                        } catch (SocketException e) {
                            // The server was stopped; ignore.
                        } catch (IOException e) {
                            MyLog.e(TAG, "Web server error."+e);
                        }

//                        try {
//                            Thread.sleep(5000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }

                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (SocketException e) {
            // The server was stopped; ignore.
            MyLog.e(TAG, "Web server SocketException error."+ e);
        } catch (IOException e) {
            MyLog.e(TAG, "Web server IOException error."+ e);
        }

    }

    /**
     * Respond to a request from a client.
     *
     * @param socket The client socket.
     * @throws IOException
     */
    private void handle(Socket socket) throws IOException {
        BufferedReader reader = null;
        PrintStream output = null;
        try {
            String route = null;

            // Read HTTP headers and parse out the route.
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String method = null;
            String line;
            StringBuilder stringBuilder = new StringBuilder("[");
            while ((line = reader.readLine()) != null) {
                MyLog.i(TAG, "readLine: " + line+"\n");
                stringBuilder.append(line);
                if (line.startsWith("GET /")) {
                    method = Method.GET;
                    int start = line.indexOf('/') + 1;
                    int end = line.indexOf(' ', start);
                    route = line.substring(start, end);
                    break;
                } else if (line.startsWith("POST /")) {
                    method = Method.POST;
                    int start = line.indexOf('/') + 1;
                    int end = line.indexOf(' ', start);
                    route = line.substring(start, end);
                    break;
                }
            }
            stringBuilder.append("]");
            EventBus.getDefault().post(new ServerEvent(stringBuilder.toString()));
            // Output stream that we send the response to
            output = new PrintStream(socket.getOutputStream());

            // Prepare the content to send.
            MyLog.i(TAG, "Received http request: " + route);
            byte[] bytes = null;


            if (new Random().nextBoolean()) {

                bytes = "200 OK".getBytes();
            } else {
                bytes = "FAIL  This is Demo".getBytes();
            }

            // Send out the content.
            output.println("HTTP/1.0 200 OK");
            //output.println("Content-Type: " + detectMimeType(route));
            output.println("Content-Length: " + bytes.length);
            output.println();
            output.write(bytes);
            output.flush();
        } finally {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (null != output) {
                output.close();
            }
            if (null != reader) {
                reader.close();
            }
        }
    }


    /**
     * This method starts the web server listening to the specified port.
     */
    public void start() {
        mIsRunning = true;
        new Thread(this).start();
    }

    /**
     * This method stops the web server
     */
    public void stop() {
        try {
            mIsRunning = false;
            if (null != mServerSocket) {
                mServerSocket.close();
                mServerSocket = null;
            }
        } catch (IOException e) {
            MyLog.e(TAG, "Error closing the server socket."+ e);
        }
    }
}
