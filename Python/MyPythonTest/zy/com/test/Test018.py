#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""
题目：输入一行字符，分别统计出其中英文字母、空格、数字和其它字符的个数。
"""

n = int(raw_input('n = '))
a = int(raw_input('a = '))
print n, a
Tn = 0
Sn = []
for i in range(0, n):
    x = a * 10 ** i
    Tn = Tn + x
    Sn.append(Tn)
    print Tn

print Sn

xx = reduce(lambda x, y: x + y, Sn)
xxx = reduce(lambda x, y: y - x, Sn)
print "计算和为：", xxx
print "计算和为：", xx
