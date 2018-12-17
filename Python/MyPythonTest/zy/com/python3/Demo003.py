# -*- coding: UTF-8 -*-


# Filename : test.py
# author by : www.runoob.com
# 文件操作
# 写文件
print
with open("test.txt", "wt") as out_file:
    out_file.write("该文本会写入到文件中\n看到我了吧！")

# Read a file
with open("test.txt", "rt") as in_file:
    text = in_file.read()

print(text)
