#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""
题目：求1+2!+3!+...+20!的和。
"""
tmp = 1
sums = 0
for i in range(1, 21):
    tmp = tmp * i
    sums += tmp
    print i, tmp
print sums
