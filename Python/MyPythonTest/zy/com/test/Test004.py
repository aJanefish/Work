#!/usr/bin/python
# -*- coding: UTF-8 -*-

"""
题目：输入某年某月某日，判断这一天是这一年的第几天？
"""

year = int(raw_input('year:\n'))
month = int(raw_input('month:\n'))
day = int(raw_input('day:\n'))

months = (0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334)
print months[0:5]
for x in months:
    print x
# sum
if 0 < month <= 12:
    sumer = months[month - 1]
else:
    print 'data error'
sumer += day
leap = 0
if (year % 400 == 0) or ((year % 4 == 0) and (year % 100 != 0)):
    leap = 1
if (leap == 1) and (month > 2):
    sumer += 1
print 'it is the %dth day.' % sumer
