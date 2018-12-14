#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""
题目：一个数如果恰好等于它的因子之和，这个数就称为"完数"。例如6=1＋2＋3.编程找出1000以内的所有完数。
"""

# 找出一个数的所有因子

print


def factor(n):
    nd = n / 2
    fac = [1]
    for i in range(2, nd + 1):
        if n % i == 0:
            # print i,
            fac.append(i)
    #print
    return fac


# facss = factor(480)
# print facss
#
# sum = reduce(lambda x, y: x + y, facss)
#
# print sum

for i in range(1, 10001):
    factors = factor(i)
    sum1 = reduce(lambda x, y: x + y, factors)
    if i == sum1:
        print i, factors
