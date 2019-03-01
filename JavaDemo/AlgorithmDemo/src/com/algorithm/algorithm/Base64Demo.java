package com.algorithm.algorithm;

import com.algorithm.utils.P;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * Value	Char Value	Char Value	Char Value	Char
 * 0	A	  16	Q	  32	g	  48	w
 * 1	B	  17	R	  33	h	  49	x
 * 2	C	  18	S	  34	i	  50	y
 * 3	D	  19	T	  35	j	  51	z
 * 4	E	  20	U	  36	k	  52	0
 * 5	F	  21	V	  37	l	  53	1
 * 6	G	  22	W	  38	m	  54	2
 * 7	H	  23	X	  39	n	  55	3
 * 8	I	  24	Y	  40	o	  56	4
 * 9	J	  25	Z	  41	p	  57	5
 * 10	K	  26	a	  42	q	  58	6
 * 11	L	  27	b	  43	r	  59	7
 * 12	M	  28	c	  44	s	  60	8
 * 13	N	  29	d	  45	t	  61	9
 * 14	O	  30	e	  46	u	  62	+
 * 15	P	  31	f	  47	v	  63	/
 * <p>
 * 4个base64 表示 3个ASCII
 * <p>
 * 转码过程例子：
 * 3*8=4*6
 * 内存1个字节占8位
 * 转前： s 1 3
 * 先转成ascii：对应 115 49 51
 * 2进制： 01110011 00110001 00110011
 * 6个一组（4组） 011100110011000100110011
 * 然后才有后面的 011100 110011 000100 110011
 * 然后计算机是8位8位的存数 6不够，自动就补两个高位0了
 * 所有有了 高位补0
 * 科学计算器输入 00011100 00110011 00000100 00110011
 * 得到 28 51 4 51
 * 查对下照表 c z E z
 * <p>
 * import java.util.Base64;
 * 对于标准的Base64：
 * 加密为字符串使用Base64.getEncoder().encodeToString();
 * 加密为字节数组使用Base64.getEncoder().encode();
 * 解密使用Base64.getDecoder().decode();
 * 对于URL安全或MIME的Base64，只需将上述getEncoder()getDecoder()更换为getUrlEncoder()getUrlDecoder()
 * 或getMimeEncoder()和getMimeDecoder()即可。
 */

public class Base64Demo {
    public static void main(String[] args) {
        P.pln(1 << 6);
        char cha = 'A';
        P.pln(cha + " : " + (int) cha);

        test1();
        test2();
        test3();

        test4();

        test5();
    }


    //加密 3*8 = 4*6 ==> (toBase64)63个字符
    private static void test5() {
        P.pln("test5------------------- 加密");
        String test = "s13";
        //获取字符串的ASCII码
        byte[] tmp1 = test.getBytes(StandardCharsets.ISO_8859_1);
        byte[] tmp2 = Base64.getEncoder().encode(tmp1);
        P.pln(Arrays.toString(tmp1));

        P.pln(Arrays.toString(tmp2));
        for (byte b : tmp1) {
            P.pln(b+": "+(char)b);
        }
        for (byte b : tmp2) {
            P.pln(b+": "+(char)b);
        }
    }

    //Base64 解密 实现
    private static void test4() {
        P.pln("\ntest4------------------");
        String test = "czEz";
        //获取字符串的ASCII码
        byte[] tmp1 = test.getBytes(StandardCharsets.ISO_8859_1);
        P.pln(Arrays.toString(tmp1));
        for (byte b : tmp1) {
            P.pln(b + " : " + (char) b);
        }

        byte[] dst = new byte[tmp1.length];
        int ret = decode0(tmp1, 0, tmp1.length, dst);
        //int ret = decode0(src, 0, src.length, dst);
        if (ret != dst.length) {
            dst = Arrays.copyOf(dst, ret);
        }
        P.pln(Arrays.toString(dst));
        for (byte b : dst) {
            P.pln(b + " : " + (char) b);
        }
    }


    //copy Object
    private static void test3() {
        P.pln("\ntest3----------------");
        byte[] dst = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        byte[] newDst = Arrays.copyOf(dst, 5);
        P.pln(Arrays.toString(dst));
        P.pln(Arrays.toString(newDst));
    }

    private static void test2() {
        P.pln("test2----------------");
        String test = "czEz";
        byte[] tmp1 = test.getBytes(StandardCharsets.ISO_8859_1);
        P.pln("ISO_8859_1:" + Arrays.toString(tmp1));
        tmp1 = test.getBytes(StandardCharsets.US_ASCII);
        P.pln("US_ASCII:" + Arrays.toString(tmp1));
        tmp1 = test.getBytes(StandardCharsets.UTF_8);
        P.pln("UTF_8:" + Arrays.toString(tmp1));
        tmp1 = test.getBytes(StandardCharsets.UTF_16);
        P.pln("UTF_16:" + Arrays.toString(tmp1));
        tmp1 = test.getBytes(StandardCharsets.UTF_16BE);
        P.pln("UTF_16BE:" + Arrays.toString(tmp1));
        tmp1 = test.getBytes(StandardCharsets.UTF_16LE);
        P.pln("UTF_16LE:" + Arrays.toString(tmp1));
    }

    //解密 Base64 -- ASCII
    private static void test1() {
        P.pln("解密 Base64 -- ASCII");
        //Base64 "czEz" -- "s13"
        String test = "czEz";
        //JAVA 自带的Base64解码
        //获取字符串的ASCII码
        byte[] tmp1 = test.getBytes(StandardCharsets.ISO_8859_1);
        P.pln(Arrays.toString(tmp1));
        for (byte b : tmp1) {
            P.pln(b + " : " + (char) b);
        }
        byte[] tmp = Base64.getDecoder().decode(tmp1);
        P.pln(Arrays.toString(tmp));
        for (byte b : tmp) {
            P.pln(b + " : " + (char) b + " : " + Character.toChars(b + 1).length + " : " + Character.toChars(b + 1)[0]);
        }
    }


    private static final int[] fromBase64 = new int[256];
    private static final char[] toBase64 = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };
    static {
        Arrays.fill(fromBase64, -1);
        for (int i = 0; i < toBase64.length; i++)
            fromBase64[toBase64[i]] = i;
        fromBase64['='] = -2;

        P.pln(Arrays.toString(fromBase64));
        P.pln(toBase64[28]);
        P.pln(toBase64[51]);
        P.pln(toBase64[4]);
        P.pln(toBase64[51]);

        P.pln(fromBase64[(int)toBase64[28]]);
        P.pln(fromBase64[(int)toBase64[51]]);
        P.pln(fromBase64[(int)toBase64[4]]);
        P.pln(fromBase64[(int)toBase64[51]]);
        P.pln("static -----\n\n\n");
    }




    //ecode0(src, 0, src.length, dst);
    private static int decode0(byte[] src, int sp, int sl, byte[] dst) {
        int[] base64 = fromBase64;
        int dp = 0;
        int bits = 0;
        int shiftto = 18;       // pos of first byte of 4-byte atom
        while (sp < sl) {
            int b = src[sp++] & 0xff;
            P.pln("b:"+b+" - "+base64[b]);
            if ((b = base64[b]) < 0) {
                if (b == -2) {         // padding byte '='
                    // =     shiftto==18 unnecessary padding
                    // x=    shiftto==12 a dangling single x
                    // x     to be handled together with non-padding case
                    // xx=   shiftto==6&&sp==sl missing last =
                    // xx=y  shiftto==6 last is not =
                    if (shiftto == 6 && (sp == sl || src[sp++] != '=') ||
                            shiftto == 18) {
                        throw new IllegalArgumentException(
                                "Input byte array has wrong 4-byte ending unit");
                    }
                    break;
                }
                if (false)    // skip if for rfc2045
                    continue;
                else
                    throw new IllegalArgumentException(
                            "Illegal base64 character " +
                                    Integer.toString(src[sp - 1], 16));
            }
            P.pln(b+" : "+shiftto);
            bits |= (b << shiftto);
            shiftto -= 6;
            if (shiftto < 0) {
                dst[dp++] = (byte) (bits >> 16);
                dst[dp++] = (byte) (bits >> 8);
                dst[dp++] = (byte) (bits);
                shiftto = 18;
                bits = 0;
            }
        }

        P.pln("dst:"+Arrays.toString(dst));
        // reached end of byte array or hit padding '=' characters.
        if (shiftto == 6) {
            dst[dp++] = (byte) (bits >> 16);
        } else if (shiftto == 0) {
            dst[dp++] = (byte) (bits >> 16);
            dst[dp++] = (byte) (bits >> 8);
        } else if (shiftto == 12) {
            // dangling single "x", incorrectly encoded.
            throw new IllegalArgumentException(
                    "Last unit does not have enough valid bits");
        }
        // anything left is invalid, if is not MIME.
        // if MIME, ignore all non-base64 character
        while (sp < sl) {
            if (false && base64[src[sp++]] < 0)
                continue;
            throw new IllegalArgumentException(
                    "Input byte array has incorrect ending byte at " + sp);
        }
        return dp;
    }
}



