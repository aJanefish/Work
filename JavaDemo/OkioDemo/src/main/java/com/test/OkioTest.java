package com.test;

import com.http.Headers;
import com.util.P;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class OkioTest {
    private static BufferedSource source;
    private static BufferedSink sink;

    public static void main(String[] args) throws IOException, InterruptedException {
        Headers headers = new Headers.Builder()
                .add("Host", "www.googletagservices.com")
                .build();
        okioTest("www.googletagservices.com", "GET /activeview/js/current/osd.js?cb=%2Fr20100101 HTTP/1.1", 80, headers);


        //目前不能发送Https请求
        //https://free-api.heweather.com/s6/air/now?location=beijing&key=f464c53cb02240a194640685ee425116
        //GET /s6/air/now?location=beijing&key=f464c53cb02240a194640685ee425116 HTTP/1.1
        //Host: free-api.heweather.com
//        Headers headers1 = new Headers.Builder()
//                .add("Host", "free-api.heweather.com")
//                .build();
//        okioTest("free-api.heweather.com", "GET /s6/air/now?location=beijing&key=f464c53cb02240a194640685ee425116 HTTP/1.1", 443, headers1);

    }

    private static void okioTest(String host, String headLine, int port, Headers headers) throws IOException, InterruptedException {

        Socket rawSocket = new Socket();
        //http://www.googletagservices.com/activeview/js/current/osd.js?cb=%2Fr20100101
        try {
            InetAddress[] inetAddresses = InetAddress.getAllByName(host);
            P.pln(inetAddresses.length);
            P.pln(Arrays.toString(inetAddresses));
            //new InetSocketAddress(inetAddress, socketPort)

            InetSocketAddress inetSocketAddress1 = null;
            if (inetAddresses.length > 1) {
                inetSocketAddress1 = new InetSocketAddress(inetAddresses[0], port);
            }
            if (inetSocketAddress1 != null) {
                rawSocket.connect(inetSocketAddress1, 10000);

                source = Okio.buffer(Okio.source(rawSocket));
                sink = Okio.buffer(Okio.sink(rawSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            P.pln("rawSocket:" + rawSocket);
            P.pln(source);
            P.pln(sink);
        }


        writeHeaders(headLine, headers);

        flushRequest();

        Thread.sleep(500);

        readSource();
    }

    private static void readSource() throws IOException {
        //P.pln("Response ------------");
        String line = source.readUtf8Line();

        P.pln(line);
        for (String tmp = null; (tmp = source.readUtf8Line()).length() != 0; ) {
            P.pln(tmp);
        }
        P.pln();
        for (String tmp = null; (tmp = source.readUtf8Line()).length() != 0; ) {
            P.pln(tmp);
        }

    }

    private static void flushRequest() throws IOException {
        sink.flush();
    }

    private static void writeHeaders(String requestLine, Headers headers) throws IOException {
        //GET /activeview/js/current/osd.js?cb=%2Fr20100101 HTTP/1.1
        //Host: www.googletagservices.com
        //请求行
        P.pln(requestLine);
        sink.writeUtf8(requestLine).writeUtf8("\r\n");
        for (int i = 0, size = headers.size(); i < size; i++) {
            sink.writeUtf8(headers.name(i))
                    .writeUtf8(": ")
                    .writeUtf8(headers.value(i))
                    .writeUtf8("\r\n");

            P.pln(headers.name(i)+":" +headers.value(i));
        }
        sink.writeUtf8("\r\n");
        P.pln();
    }
}
