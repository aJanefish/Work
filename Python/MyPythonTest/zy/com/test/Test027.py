#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""
题目：利用递归函数调用方式，将所输入的5个字符，以相反顺序打印出来。
"""


def output(ss, ll):
    if ll == 0:
        return
    print ss[ll - 1]
    output(ss, ll - 1)


s = raw_input('Input a string:')
ls = len(s)

print ls, s
output(s, ls)
