#!/usr/bin/python
# -*- coding: UTF-8 -*-
# 文件名：clientDemo.py

import socket  # 导入 socket 模块

s = socket.socket()  # 创建 socket 对象
host = socket.gethostname() # 获取本地主机名
# host = '192.168.201.80'
port = 12345  # 设置端口号

s.connect((host, port))


s.send('python send demo test1\n')

print s.recv(1024)
print s.recv(1024)
s.close()
