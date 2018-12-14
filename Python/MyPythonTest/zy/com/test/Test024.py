#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""
题目：有一分数序列：2/1，3/2，5/3，8/5，13/8，21/13...求出这个数列的前20项之和。

程序分析：请抓住分子与分母的变化规律。
"""

denominador = 2.0
molecule = 1.0

str = [denominador/molecule]
print str

for i in range(2, 21):
    molecule, denominador = denominador, denominador + molecule
    # print "%3d , %3d , %3d" % (i, denominador, molecule)
    str.append(denominador/molecule)

print str
print sum(str)


a = 2.0
b = 1.0
l = []
l.append(a / b)
for n in range(1,20):
    b,a = a,a + b
    l.append(a / b)
print reduce(lambda x,y: x + y,l)
