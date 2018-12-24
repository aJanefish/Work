package com.zy.test
/**
 * 标准委托
 * Kotlin 的标准库中已经内置了很多工厂方法来实现属性的委托。
 * 延迟属性 Lazy
 * lazy() 是一个函数, 接受一个 Lambda 表达式作为参数, 返回一个 Lazy <T> 实例的函数，
 * 返回的实例可以作为实现延迟属性的委托： 第一次调用 get() 会执行已传递给 lazy() 的 lamda 表达式并记录结果，
 * 后续调用 get() 只是返回记录的结果。
 * */

val lazyValue: String by lazy {
    println("computed!")     // 第一次调用输出，第二次调用不执行
    "Hello"
}

val lazyValue1: String by lazy {
    println("lazyValue1 computed!")     // 第一次调用输出，第二次调用不执行
    "lazyValue1"
}

fun main(args: Array<String>) {
    println(lazyValue)   // 第一次执行，执行两次输出表达式
    println(lazyValue)   // 第二次执行，只输出返回值
    println(lazyValue1)
    println(lazyValue1)
}