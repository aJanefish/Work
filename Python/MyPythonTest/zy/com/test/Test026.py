#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""
题目：利用递归方法求5!。
"""

print


def fib(n):
    if n == 0 or n == 1:
        return 1
    return n*fib(n-1)


print fib(5)
