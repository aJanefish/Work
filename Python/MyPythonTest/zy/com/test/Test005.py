#!/usr/bin/python
# -*- coding: UTF-8 -*-

"""
题目：输入三个整数x,y,z，请把这三个数由小到大输出。
"""

ints = []

for i in range(3):
    print i
    x = int(raw_input('integer:\n'))
    ints.append(x)

print ints
# 排序 从小到大
ints.sort()
print ints

# 排序 从大到小
ints.reverse()
print ints
