#第一个注释
import keyword
import sys
from sys import argv,path  #  导入特定的成员

print("keyword.kwlist =",keyword.kwlist)

if True:
    print ("Answer")
    print ("True")
else:
    print ("Answer")
print ("False")    # 缩进不一致，会导致运行错误
total = ['item_one', 'item_two', 'item_three',
        'item_four', 'item_five']
print ("total =",total)

def main():
    print ("Hello,Python!")#第二个注释
    StringDemo()
    #inputDemo()
    printDemo()

def printDemo():
    print("printDemo---------------------start")
    x="a"
    y="b"
    # 换行输出
    print( x )
    print( y )
    print('---------')
    # 不换行输出
    print( x, end=" " )
    print( y, end=" " )
    print("w")

    print('================Python import mode==========================');
    print ('params:')
    for i in sys.argv:
        print ("i =",i)
    print ('\npython path',sys.path)

    print('================python from import===================================')
    print('path:',path) # 因为已经导入path成员，所以此处引用时不需要加sys.path
    print("printDemo---------------------end")


def inputDemo():
    #input("\n\n按下 enter 键后退出。")
    str = input("\n\n enter")
    print("inputdemo =",str)


def StringDemo():
  str='Runoob'
  print(str)                 # 输出字符串
  print(str[0:-1])           # 输出第一个到倒数第二个的所有字符串
  print(str[0])              # 输出字符串第一个字符
  print(str[2:5])            # 输出从第三个开始到第五个的字符
  print(str[2:])             # 输出从第三个开始的后的所有字符
  print(str *4)            # 输出字符串两次
  strTmp = "ssss"
  print("strTmp =",strTmp)   
  print(str + "sss")          # 连接字符串




main()
