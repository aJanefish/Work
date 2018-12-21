package com.zy.test

/**
 * Kotlin 基础语法
 * */

//函数定义使用关键字 fun，参数格式为：参数 : 类型
fun sum(a: Int, b: Int): Int {   // Int 参数，返回值 Int
    return a + b
}

//表达式作为函数体，返回类型自动推断：
fun sum1(a: Int, b: Int) = a + b

fun sum2(a: Int, b: Int): Int = a + b
//public 方法则必须明确写出返回类型
public fun sum3(a: Int, b: Int): Int = a + b

//无返回值的函数(类似Java中的void)：
fun printSum(a: Int, b: Int) = println(a + b)

fun printSum1(a: Int, b: Int): Unit {
    println(a + b)
}

fun printSum2(a: Int, b: Int) {
    println(a + b)
}

// 如果是返回 Unit类型，则可以省略(对于public方法也是这样)：
public fun printSum3(a: Int, b: Int) {
    print(a + b)
}


fun main(args: Array<String>) {
    println(1)
    println(sum(1, 2))
    println(sum1(2, 3))
    println(sum2(3, 4))
    println(sum3(4, 5))
    printSum(5, 6)
    printSum1(6, 7)
    printSum2(7, 8)
    printSum3(8, 9)
}