#!/usr/bin/python
# -*- coding: utf-8 -*-

# it's a program of Singou, yu.zhang@singou.mo


import serial
import datetime
import requests
from time import sleep

ser = None

def sendUrl(angle):
    url = "http://192.168.31.76:8080/wakeup?angle=" + angle.lstrip("0")
    timestr = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    # Send HTTP Request
    try:
       #r = requests.get("http://www.baidu.com")
       print(timestr + " HTTP request sending url: " + url)
       r = requests.get(url)
       print(timestr + " HTTP request sent. url: " + url)
       print(r)
    except Exception as err:
       print(timestr + " HTTP request failed. e: " + str(err))


def getSerial(ser):

  #flag_stop = False
    timestr = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    print (timestr+" serial.isOpen() =",ser.isOpen())
    

#    if flag_stop:                # read data until Ctrl+c
#        #ser.write(stop)            # send cmd stop before exit
#        print ("reset cmd has been sent!")
#        sleep(0.05)
#        return;

    text = ser.readline()         # read one, with timout
    #bytes String wakeup:145
    line = bytes.decode(text)
    print (r"text = ",text, r" line =",line)
    if("wakeup" in line):
       print ("has wakeup")
       print ("wakeup =",line[7:10])
       sendUrl(line[7:10])
    else:
       print ("not has wakeup")

def open():
    try:
      # 打开串口
        #ser = serial.Serial("/dev/ttyAMA0", 9600)
        ser = serial.Serial('/dev/ttyUSB1', 115200, timeout = 1)
    except Exception as err:
        print("OPEN serial failed. e: " + str(err))
        return None   
    else:
       return ser


def main():
  #判断设备是否打开
  ser = open()
  while True:
    if ser == None:
      print ("ser is None ==",ser)
      sleep(1) 
      ser = open()
    else:
       print ("serial.isOpen() =",ser.isOpen())
       if (ser.isOpen()):
           sleep(1)
           getSerial(ser)
       else:
           sleep(1) 
           ser = open()


if __name__ == '__main__':

    try:
        main()
    except KeyboardInterrupt:
        if ser != None:
            ser.close()
