# -*- coding: UTF-8 -*-

import urllib
import re


def get_html(url):
    page = urllib.urlopen(url)
    html = page.read()
    return html


def get_image(html_code):
    reg = r'src="(.+?\.jpg)" width'
    reg_img = re.compile(reg)
    img_list = reg_img.findall(html_code)
    print img_list
    x = 0

    for img in img_list:
        # 获取图片
        urllib.urlretrieve(img, 'picture/%s.jpg' % x)
        x += 1
        #print img


url = 'http://tieba.baidu.com/p/1753935195'
url = 'http://tieba.baidu.com/p/1753935195'

htmlCode = get_html(url)

pageFile = open('pageCode.txt', 'w')  # 以写的方式打开pageCode.txt
pageFile.write(htmlCode)  # 写入
pageFile.close()  # 开了记得关

print u'-------网页图片抓取-------'
get_image(htmlCode)
print u'-----------下载成功-----------'
