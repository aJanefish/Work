package com.zy.test

//条件控制
//val c = if (condition) a else b

fun main(args: Array<String>) {
    println(hasPrefix("sdf"))
    testWhen()
    test1()
}

fun hasPrefix(x: Any) = when(x) {
    is String -> x.startsWith("prefix")
    else -> false
}


fun testWhen(){


    //类似于switch
    var x : Int = 18
    when (x) {
        1 -> print("x == 1")
        2 -> print("x == 2")
        else -> { // 注意这个块
            print("x 不是 1 ，也不是 2")
        }
    }
    println()

    var validNumbers = intArrayOf(1,2,3)
    when (x) {
        in 1..10 -> print("x is in the range")
        in validNumbers -> print("x is valid")
        !in 10..20 -> print("x is outside the range")
        else -> print("none of the above")
    }
    println()

    val items = setOf("apple", "banana", "kiwi")
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
        "banana" in items -> println("banana is fine too")
    }
    println()
}

fun test1() {
    // 传统用法
    var a: Int = 1
    var b: Int = 2
    var max = a
    if (a < b) max = b

    // 使用 else
    var max1: Int
    if (a > b) {
        max1 = a
    } else {
        max1 = b
    }

    // 作为表达式
    val max2 = if (a > b) a else b

    val max3 = if (a > b) {
        print("Choose a")
        a
    } else {
        print("Choose b")
        b
    }
    println()

    val x = 5
    val y = 9
    if (x in 1..8) {
        println("x 在区间内")
    }
}