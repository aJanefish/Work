#第一个注释
import keyword
import sys


var1 = 'Hello World!'
var2 = "Runoob"
print ("var1 : ", var1)
print ("var2 : ", var2)

def main():
    print ("sys.argv =",sys.argv,"-----------------------------------start")
    base()
    stringToInt()
    
    print ("sys.argv =",sys.argv,"-----------------------------------end")

def stringToInt():
    #text = b'wakeup:188\r\n'
    print(b'wakeup:188\r\n')
    print(r'wakeup:188\r\n')
    str = 'wakeup:188\r\n'
    if( "wakeup" in str) :
       print("wakeup in str ")
    else :
       print("wakeup not in str ")


def base():
   print ("var1[0]: ", var1[0])
   print ("var2[1:5]: ", var2[1:5])
   
   a = "Hello"
   b = "Python"
 
   print("a + b:", a + b)
   print("a * 2 :", a * 2)
   print("a[1] :", a[1])
   print("a[1:4] :", a[1:4])
 
   if( "H" in a) :
      print("H in a ")
   else :
    print("H not in a ")
 
   if( "M" not in a) :
      print("M not a ")
   else :
      print("M in a ")
 
   print (r'\n')
   print (R'\n')
  

main()
