#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""
题目：画图，学用line画直线。　　　
"""

if __name__ == '__main__':
    from Tkinter import *

    canvas = Canvas(width=800, height=600, bg='green')
    canvas.pack(expand=YES, fill=BOTH)
    x0 = 263
    y0 = 263
    y1 = 275
    x1 = 275
    for i in range(19):
        canvas.create_line(x0, y0, x0, y1, width=1, fill='red')
        x0 = x0 - 5
        y0 = y0 - 5
        x1 = x1 + 5
        y1 = y1 + 5

    x0 = 263
    y1 = 275
    y0 = 263
    for i in range(21):
        canvas.create_line(x0, y0, x0, y1, fill='red')
        x0 += 5
        y0 += 5
        y1 += 5

    canvas.create_line(0, 300, 300, 300, fill='red')
    canvas.create_line(300, 0, 300, 300, fill='red')
    mainloop()
