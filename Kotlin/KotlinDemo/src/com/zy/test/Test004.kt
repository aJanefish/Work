package com.zy.test

//区间表达式由具有操作符形式 .. 的 rangeTo 函数辅以 in 和 !in 形成。
//区间是为任何可比较类型定义的，但对于整型原生类型，它有一个优化的实现

fun main(args: Array<String>) {
    print("循环输出：")
    for (i in 1..4) print(i) // 输出“1234”
    println("\n......")
    print("倒叙输出：")
    for (i in 4 downTo 1) print(i) // 什么都不输出
    println("\n......")
    // 使用 step 指定步长
    print("设置步长：")
    for (i in 1..4 step 2) print(".$i") // 输出“13”
    println("\n......")
    print("使用 downTo：")
    for (i in 4 downTo 1 step 2) print(i) // 输出“42”
    println("\n......")

    // 使用 until 函数排除结束元素
    print("使用 until：")
    for (i in 1 until 10) {   // i in [1, 10) 排除了 10
        print("$i")
    }
    println("\n......")

    for (i in 1..10) { // 等同于 1 <= i && i <= 10
        println(i)
    }
}