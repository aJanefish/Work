import datetime
import requests

def sendUrl(angle):
    url = "http://192.168.31.20:8080/wakeup?angle=" + angle.lstrip("0")
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


sendUrl("123")
