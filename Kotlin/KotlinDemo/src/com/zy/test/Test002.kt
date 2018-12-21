package com.zy.test

/***/
//可变长参数函数
//函数的变长参数可以用 vararg 关键字进行标识：
fun vars(vararg v: Int) {
    for (vt in v) {
        print(vt)
    }
    println()
}


fun main(args: Array<String>) {
    println(args)
    vars(1, 2, 3, 4, 5, 6)
    vars(1, 2, 3, 4)


    //lambda(匿名函数)
    val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
    println(sumLambda(1, 2))

    //定义常量与变量
    //可变变量定义：var 关键字
    //var <标识符> : <类型> = <初始化值>
    var x: Int = 2
    println("x=" + x)
    x = 3
    println("x=" + x)
    //不可变变量定义：val 关键字，只能赋值一次的变量(类似Java中final修饰的变量)
    //val <标识符> : <类型> = <初始化值>
    val finalNum: Int = 4
    println(finalNum)
    //常量与变量都可以没有初始化值,但是在引用前必须初始化
    //
    //编译器支持自动类型判断,即声明时可以不指定类型,由编译器判断。

    val a: Int = 1
    val b = 1       // 系统自动推断变量类型为Int
    val c: Int      // 如果不在声明时初始化则必须提供变量类型
    c = 1           // 明确赋值
    println("$a , $b , $c")

    var x1 = 5        // 系统自动推断变量类型为Int
    x1 += 1           // 变量可修改
    println(x1)

    //字符串模板
    //$ 表示一个变量名或者变量值
    //$varName 表示变量值
    //${varName.fun()} 表示变量的方法返回值:
    var a1 = 1
    // 模板中的简单名称：
    val s1 = "a is $a1"
    println(s1)

    a1 = 2
    // 模板中的任意表达式：
    val s2 = "${s1.replace("is", "was").replace("a","A")}, but now is $a1"
    println(s2)
}