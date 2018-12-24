package com.zy.test

//若扩展函数和成员函数一致,则使用该函数时，会优先使用成员函数

class C15{
    fun foo(){
        println("foo 成员函数")
    }
}

fun C15.foo(){
    println("foo 扩展函数")
}

fun C15.foo1(){
    println("foo1 扩展函数")
}


fun Any?.toString():String{
    if (this == null) return this+" NULL"
    return toString()
}



fun main(args: Array<String>) {
    var c = C15()
    c.foo()
    c.foo1()

    var t: Nothing? = null
    println(t.toString())
}