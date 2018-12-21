package com.zy.test

import kotlin.math.roundToInt

/**
 * Kotlin 基本数据类型
 * Double	64
 * Float	32
 * Long	64
 * Int	32
 * Short	16
 * Byte	8
 * */

public fun dateTest() {
    var d: Double = 3.0
    println(d)
    println(d.inc())
}


fun main(args: Array<String>) {
    // dateTest()
    string_operation()
    arrar_operation()
    char_opoeration()
    bit_operation()
    compare()
    type_conversion()
}

//字符串
public fun string_operation(){
    println("string_operation:")
    var text = """
    |多行字符串
    |菜鸟教程
    |多行字符串
    |Runoob
    """
    println(text)   // 输出有一些前置空格

    text = """
    |多行字符串
    |菜鸟教程
    |多行字符串
    |Runoob
    """.trimMargin("|")

    val price = """
    $9.99
    ${'$'}9.99
    """
    println(price)  // 求值结果为 $9.99

    println(text)    // 前置空格删除了
    for (c in "zhangyu") {
        println(c)
    }
}


//数组
public fun arrar_operation(){
    println("arrar_operation:")
    //[1,2,3]
    val a = arrayOf(1, 2, 3)
    //[0,2,4]
    val b = Array(3, { i -> (i * 2) })
    println(a[0])
    println(b[1])
}

//char operation

public fun char_opoeration() {
    println("char_opoeration:")
    println('0'.toInt())
    println('b'.toInt())
    println('b'.toInt() - '0'.toInt()) // 显式转换为数字
}



//Bit operation
//位操作符
//对于Int和Long类型，还有一系列的位操作符可以使用，分别是：
//shl(bits) – 左移位 (Java’s <<)
//shr(bits) – 右移位 (Java’s >>)
//ushr(bits) – 无符号右移位 (Java’s >>>)
//and(bits) – 与
//or(bits) – 或
//xor(bits) – 异或
//inv() – 反向

public fun bit_operation() {
    println("bit_operation:")
    var x: Int = 5
    println(x)
    println(x.shl(1))
    println(x.shr(1))
    println(x.ushr(1))
    println(x.and(2))
    println(x.or(2))
    println(x.xor(5))
    println(x.inv())
}


//类型转换
//toByte(): Byte
//toShort(): Short
//toInt(): Int
//toLong(): Long
//toFloat(): Float
//toDouble(): Double
//toChar(): Char
public fun type_conversion() {
    println("type_conversion:")
    var b: Byte? = 98
    println(b)
    println(b?.toByte())
    println(b?.toShort())
    println(b?.toString())
    println(b?.toInt())
    println(b?.toLong())
    println(b?.toFloat())
    println(b?.toDouble())
    println(b?.toChar())
}


//比较两个数字
//Kotlin 中没有基础数据类型，只有封装的数字类型，你每定义的一个变量，其实 Kotlin 帮你封装了一个对象，这样可以保证不会出现空指针
//在 Kotlin 中，三个等号 === 表示比较对象地址，两个 == 表示比较两个值大小。
public fun compare() {
    println("compare:")
    val a: Int = 10000
    println(a === a) // true，值相等，对象地址相等
    println(a == a)

    //经过了装箱，创建了两个不同的对象
    val boxa: Int? = a
    val anotherBoxedA: Int? = a

    //虽然经过了装箱，但是值是相等的，都是10000
    //  false，值相等，对象地址不一样
    println(boxa === anotherBoxedA)
    // true，值相等
    println(boxa == anotherBoxedA)
    println("$boxa,$anotherBoxedA")
}