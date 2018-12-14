#!/usr/bin/python
# -*- coding: UTF-8 -*-

"""
题目：输出 9*9 乘法口诀表。

程序分析：分行与列考虑，共9行9列，i控制行，j控制列。
"""


def test():
    for i in range(1, 10, 1):
        for j in range(1, i+1, 1):
            print "%2d *%2d = %2d" % (i, j, i*j),
        print
    return 'hello'


print test()