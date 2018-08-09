#第一个注释
import keyword
import sys



def main():
    print ("sys.argv =",sys.argv,"-----------------------------------start")
    base()
    listDemo()
    tupleDemo()
    setDemo()
    dictDemo()
    print ("sys.argv =",sys.argv,"-----------------------------------end")
  

#Dictionary（字典）
#字典（dictionary）是Python中另一个非常有用的内置数据类型。
#列表是有序的对象集合，字典是无序的对象集合。两者之间的区别在于：字典当中的元素是通过键来存取的，而不是通过偏移存取。
#字典是一种映射类型，字典用"{ }"标识，它是一个无序的键(key) : 值(value)对集合。
#键(key)必须使用不可变类型。
#在同一个字典中，键(key)必须是唯一的。 

def dictDemo():
    print("dictDemo -------------------------------------------------------------start")
    dict = {}
    dict['one'] = "1 - cainiaojiaocheng"
    dict[2]     = "2 - cainiaogongju"
    tinydict = {'name': 'runoob','code':1, 'site': 'www.runoob.com'}
    print (dict['one'])       # 输出键为 'one' 的值
    print (dict[2])           # 输出键为 2 的值
    print (tinydict)          # 输出完整的字典
    print (tinydict.keys())   # 输出所有键
    print (tinydict.values()) # 输出所有值
    print("dictDemo -------------------------------------------------------------end")



def setDemo():
    print("setDemo -------------------------------------------------------------start")
    student = {'Tom', 'Jim', 'Mary', 'Tom', 'Jack', 'Rose'}
    print(student)   # 输出集合，重复的元素被自动去掉
 
    # 成员测试
    if 'Rose' in student :
        print('Rose in')
    else :
          print('Rose not in')

    # set可以进行集合运算
    a = set('abracadabra')
    b = set('alacazam')
    print("a =",a)
    print("b =",b)
    print("a - b =",a - b)     # a和b的差集 
    print("a | b =",a | b)     # a和b的并集
    print("a & b =",a & b)     # a和b的交集
    print("a ^ b =",a ^ b)     # a和b中不同时存在的元素
    print("setDemo -------------------------------------------------------------end")


def tupleDemo():
    tuple = ( 'abcd', 786 , 2.23, 'runoob', 70.2  )
    tinytuple = (123, 'runoob')
 
    print (tuple)             # 输出完整元组
    print (tuple[0])          # 输出元组的第一个元素
    print (tuple[1:3])        # 输出从第二个元素开始到第三个元素
    print (tuple[2:])         # 输出从第三个元素开始的所有元素
    print (tinytuple * 2)     # 输出两次元组
    print (tuple + tinytuple) # 连接元组


def listDemo():
    t = ['a','b','c','d','e']
    print(t)
    print(t[:])
    print(t[1:3])
    print(t[1:2])
    print(t[3:])
    print(t[:4])

    list = [ 'abcd', 786 , 2.23, 'runoob', 70.2 ]
    tinylist = [123, 'runoob'] 
    print (list)            # 输出完整列表
    print (list[0])         # 输出列表第一个元素
    print (list[1:3])       # 从第二个开始输出到第三个元素
    print (list[2:])        # 输出从第三个元素开始的所有元素
    print (tinylist * 2)    # 输出两次列表
    print (list + tinylist) # 连接列表

    #list change
    a = [1,2,3,4,5,6,7]
    a[0]=9
    a[2:5] = [12,14,15]
    print(a)
    a[2:5] = []
    print(a)

def base():
    a = b = c = 1
    print(a,b,c)
    a, b, c = 1, 2, "runoob"
    print(a,b,c)
    counter = 100          # 整型变量
    miles   = 1000.0       # 浮点型变量
    name    = "runoob"     # 字符串
    print (counter)
    print (miles)
    print (name) 
    print('Ru\noob')
    print(r'Ru\noob')
    word = 'Python'
    print(word[0], word[5])
    print(word[-1], word[-6])
main()
