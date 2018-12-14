#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""
题目：打印出如下图案（菱形）:
"""

# print '*'
n = 6
for i in range(1, n, 1):
    for j in range(n-i):
        print ' ',

    for k in range(i*2):
        print '*',

    print

for i in range(1, n, 1):
    for j in range(i):
        print ' ',

    for k in range((n-i)*2):
        print '*',

    print
