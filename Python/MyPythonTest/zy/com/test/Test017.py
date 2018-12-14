#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""
题目：输入一行字符，分别统计出其中英文字母、空格、数字和其它字符的个数。
"""

import string

s = raw_input('请输入一个字符串:\n')
print s
letters = 0
space = 0
digit = 0
others = 0
print len(s)
i = 0
while i < len(s):
    # 获取字符
    c = s[i]
    print c
    i += 1
    if c.isalpha():
        letters += 1
    elif c.isspace():
        space += 1
    elif c.isdigit():
        digit += 1
    else:
        others += 1
print 'char = %d,space = %d,digit = %d,others = %d' % (letters,space,digit,others)
