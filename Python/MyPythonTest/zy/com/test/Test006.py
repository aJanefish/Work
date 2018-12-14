#!/usr/bin/python
# -*- coding: UTF-8 -*-

"""
题目：斐波那契数列。

F0 = 0     (n=0)
F1 = 1    (n=1)
Fn = F[n-1]+ F[n-2](n=>2)

"""


def fib(n):
    if (n == 0) or n == 1:
        return n
    return fib(n - 1) + fib(n - 2)


def fib1(n):
    if (n == 0) or n == 1:
        return n
    a, b = 1, 1
    for i in range(n - 1):
        a, b = b, a+b
    return a


def fib2(n):
    if n == 1:
        return [1]
    if n == 2:
        return [1, 1]
    fibs = [1, 1]
    for i in range(2, n):
        # print (fibs(-1), fibs(-2))
        fibs.append(fibs[-1] + fibs[-2])
    return fibs


print fib(20)
print fib1(20)
# 输出前 10 个斐波那契数列
print fib2(20)
