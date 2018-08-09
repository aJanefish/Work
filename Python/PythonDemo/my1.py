#!/usr/bin/env python
# it's a program of luo, piedgogo@sina.com

import serial
import signal
import datetime
import requests
from time import sleep

flag_stop = False

def onsignal_int(a,b):
    print ("sigint!")
    global flag_stop
    flag_stop = True

def sendUrl(angle):
    url = "http://192.168.201.14:8080/wakeup?angle=" + angle.lstrip("0")
    timestr = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    # Send HTTP Request
    try:
       #r = requests.get("http://www.baidu.com")
       r = requests.get(url)
       print(timestr + " HTTP request sent. url: " + url)
       print(r)
    except:
       print(timestr + " HTTP request failed. url: " + url)

signal.signal(signal.SIGINT, onsignal_int)
signal.signal(signal.SIGTERM, onsignal_int)

ser = serial.Serial('/dev/ttyUSB0', 115200, timeout = 1)
print ("serial.isOpen() =",ser.isOpen())


while True:
    sleep(0.5)
    print ("serial.isOpen() =",ser.isOpen())
    print ("flag_stop =",flag_stop)
    if flag_stop:                # read data until Ctrl+c
        #ser.write(stop)            # send cmd stop before exit
        print ("reset cmd has been sent!")
        sleep(0.05)
        break
    
    text = ser.readline()         # read one, with timout

    #bytes 转 String wakeup:145
    line = bytes.decode(text)
    print (r"text = ",text, r" line =",line)

    if("wakeup" in line):
       print ("has wakeup")
       print ("wakeup =",line[7:10])
       sendUrl(line[7:10])
    else:
       print ("not has wakeup")

ser.close()






