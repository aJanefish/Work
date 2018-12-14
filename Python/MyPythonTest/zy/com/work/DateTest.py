#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""
模拟发送串口数据
"""
import struct


def dec2hl8(dec):
    s = struct.pack('>h', dec)
    print dec, '-------------=', repr(s)
    n = len(s)
    # print n, n/2
    # print s[:n / 2], s[n / 2:]
    # print hex(ord(s[:n / 2])), hex(ord(s[n / 2:]))
    return ord(s[:n / 2]), ord(s[n / 2:])  ##高八位在前，低八位在后


def dec2hl8_new(dec):
    print dec, '-------------=', repr(dec)
    print hex(dec)
    print repr(hex(dec))
    print chr(dec)
    print ord(chr(dec))

    # print n, n/2
    # print s[:n / 2], s[n / 2:]
    # print hex(ord(s[:n / 2])), hex(ord(s[n / 2:]))
    return ord(chr(dec))


def char_checkxor(data, n):
    print data[n:len(data) - 3]
    ss = hex(reduce(lambda x, y: x ^ y, data[n:len(data) - 3]))
    print ss, int(ss, 16)
    return ss


# ord() 函数是 chr() 函数（对于8位的ASCII字符串）或 unichr() 函数（对于Unicode对象）的配对函数，它以一个字符（长度为1的字符串）作为参数，返回对应的 ASCII 数值，或者 Unicode 数值，如果所给的 Unicode 字符超出了你的 Python 定义范围，则会引发一个 TypeError 的异常。

def send_command_new(left_fb, left_speed):
    print 'new date'


def send_command(left_fb, left_speed, right_fb, right_speed):
    data = [0xAA, 0x55, 0x06, 0x01, 0x04, 0, 0, 0, 0, 0, 0x0D, 0x0A]
    # data[6], data[5] = dec2hl8(100)
    # data[8], data[7] = dec2hl8(1)
    data[5] = left_fb
    data[6] = left_speed
    data[7] = right_fb
    data[8] = right_speed
    data[9] = int(char_checkxor(data, 5), 16)

    mdata = map(lambda x: chr(x), data)

    print ('Try to send data=', data)
    print 'mdata:', mdata


if __name__ == '__main__':
    # 0x0F
    # 0x0B
    send_command(0x0F, 99, 0X0F, 88)
    send_command_new(1, 1)
    # print ord('Z'), ord('\x0f'), ord('d')
    print dec2hl8(100)
    # print hex(-100)
    #
    print int('0x0F', 16)
    print ord(chr(int('0x0F', 16)))
    print dec2hl8_new(int('0x0F', 16))
