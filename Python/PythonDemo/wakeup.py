#from urllib import quote
import datetime
import requests


# URL Encode
angle = "30"
#angle = quote(angle)

# Request URL
url = "http://192.168.201.14:8080/wakeup?angle=" + angle
timestr = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")

# Send HTTP Request
try:
    #r = requests.get("http://www.baidu.com")
    r = requests.get(url)
    print(timestr + " HTTP request sent. url: " + url)
    print(r)
except:
    print(timestr + " HTTP request failed. url: " + url)
   

