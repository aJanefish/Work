package com.zy.test

//扩展函数是静态解析的
//扩展函数是静态解析的，并不是接受者类型的虚拟成员，在调用扩展函数时，具体调用的是
//哪一个函数，由调用的对象表达式来决定的，而不是动态的类型决定的

open class CC

class DD : CC()

fun CC.foo() = "CC"

fun DD.foo() = "DD"

fun printFooC(c:CC){
    println("$c,${c.foo()}")
}

fun printFooD(d:DD){
    println("$d,${d.foo()}")
}


fun main(args: Array<String>) {
    printFooC(DD())
    printFooC(CC())

    printFooD(DD())
    //printFooD(CC() as DD)
}