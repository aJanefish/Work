#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""
题目：练习函数调用。
"""


def hello_world():
    print 'hello world'


def three_hellos():
    for i in range(4):
        print i
        hello_world()


if __name__ == '__main__':
    three_hellos()
