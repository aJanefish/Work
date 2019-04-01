package com.test;

import com.util.P;
import okio.ByteString;

import java.util.Arrays;

/**
 * 类比如字符串String
 *;
 * */
public class ByteStringDemo {
    public static void main(String args[]) {
        testCreate();
    }

    /**
     * ByteString base use
     * */
    private static void testCreate() {
        String data = "hello world";
        ByteString of = ByteString.of(data.getBytes());
        show("of", of, data);

        data = "张宇123abcABC";
        ByteString of1 = ByteString.of(data.getBytes());
        show("of1", of1, data);


        data = "68656c6c6f20776f726c64";
        ByteString decodeHex = ByteString.decodeHex(data);
        show("decodeHex", decodeHex, data);


        data = "0123456789";
        ByteString utf8 = ByteString.encodeUtf8(data);
        show("utf8", utf8, data);

        data = "MDEyMzQ1Njc4OQ==";
        ByteString base64 = ByteString.decodeBase64(data);
        show("base64", base64, data);
    }

    private static void show(String name, ByteString byteString, String data) {
        P.pln(name + "............................");
        P.pln(name + " data:" + data);
        P.pln(name + " :" + byteString.size() + " - " + byteString.toByteArray().length);
        P.pln(name + " :" + byteString);
        P.pln(name + " utf8:" + byteString.utf8());
        P.pln(name + " byte[]:" + Arrays.toString(byteString.toByteArray()));
        P.pln(name + " hex:" + byteString.hex());
        P.pln(name + " base64:" + byteString.base64());
        P.pln(name + " base64Url:" + byteString.base64Url());
        P.pln(name + " md5:" + byteString.md5());
        P.pln(name + " sha1:" + byteString.sha1());
        P.pln(name + " sha256:" + byteString.sha256());
        P.pln(name + " asByteBuffer:" + byteString.asByteBuffer());


        P.pln("\n\n");
    }
}
