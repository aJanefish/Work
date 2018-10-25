package demo.okhttp.zy.com.okhttpdemo.http;

import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.http.WebSocket;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import demo.okhttp.zy.com.okhttpdemo.event.LongServerEvent;
import demo.okhttp.zy.com.okhttpdemo.log.MyLog;

/**
 *
 * server 长连接
 *
 * */

public class MyAsyncHttpServer {

    private static final String TAG = "MyAsyncHttpServer";
    List<WebSocket> _sockets;

    public MyAsyncHttpServer() {
        init();
    }

    private void init() {
        MyLog.i(TAG, "ws: startWS");
        _sockets = new ArrayList<>();

        //First create a new server instance
        AsyncHttpServer server = new AsyncHttpServer();

        /*Inside onCreate*/
        //Add a websocket connection callback
        server.websocket("/demo/", new MySocketRequestCallback());
        server.listen(8888);
    }

    public void sendWs(String str) {
        MyLog.i(TAG, "sendWs: " + str);
        for (WebSocket socket : _sockets) {
            socket.send(str);
        }
    }

    private class MySocketRequestCallback implements AsyncHttpServer.WebSocketRequestCallback {


        @Override
        public void onConnected(WebSocket webSocket, AsyncHttpServerRequest request) {
            //Here you may set up the callbacks on the websocket instance
            MyLog.i(TAG, "ws: onConnected:" + webSocket);
            MyLog.i(TAG, "ws: onConnected:" + request);
            //This will be what you use in 90% of the use cases I imagine
            final WebSocket socket = webSocket;

            webSocket.setStringCallback(new WebSocket.StringCallback() {

                @Override
                public void onStringAvailable(String s) {
                    MyLog.i(TAG, "onStringAvailable:" + s);
                    EventBus.getDefault().post(new LongServerEvent("onStringAvailable:"+s));

                }
            });
            webSocket.setDataCallback(new DataCallback() {

                @Override
                public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {
                    MyLog.d(TAG, "" + String.format("onDataAvailable:%s", bb.readString()));
                    bb.recycle();
                }
            });

            //Use this to clean up any references to your websocket
            webSocket.setClosedCallback(new CompletedCallback() {

                @Override
                public void onCompleted(Exception ex) {
                    _sockets.remove(socket);

                }
            });

            //and I usually also add this websocket to a data structure so that I can batch send messages

            MyLog.d(TAG, "new client connect");
            _sockets.add(socket);
        }
    }
}
