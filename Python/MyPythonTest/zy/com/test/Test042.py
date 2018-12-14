#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""
题目：模仿静态变量的用法。
"""

num = 2


def autofunc():
    num = 1
    print 'internal block num = %d' % num
    num += 1


for i in range(3):
    print 'The num = %d' % num
    num += 1
    autofunc()
