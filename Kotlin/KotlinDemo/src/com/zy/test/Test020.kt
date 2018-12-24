package com.zy.test

import kotlin.properties.Delegates
/**
 * 可观察属性 Observable
 * observable 可以用于实现观察者模式。
 * Delegates.observable() 函数接受两个参数: 第一个是初始化值, 第二个是属性值变化事件的响应器(handler)。
 *
 * 在属性赋值后会执行事件的响应器(handler)，它有三个参数：被赋值的属性、旧值和新值：
 * */

class User20 {
    var name: String by Delegates.observable("初始值") {
        prop, old, new ->
        println("(name)旧值：$old -> 新值：$new")
    }

    var age : Int by Delegates.observable(20){
        property, oldValue, newValue ->
        println("(age)旧值：$oldValue-> 新值：$newValue")
    }


}

fun main(args: Array<String>) {
    val user = User20()
    user.name = "第一次赋值"
    println(user.name)
    user.name = "第二次赋值"
    println(user.name)

    user.age = 21
    println(user.age)
    user.age = 22
    println(user.age)
}