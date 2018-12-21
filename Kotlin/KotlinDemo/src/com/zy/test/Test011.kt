package com.zy.test

//函数重写
//实现多个接口时，可能会遇到同一方法继承多个实现的问题。例如:

interface A {
    fun foo() {
        println("A foo")
    }   // 已实现

    fun bar()                  // 未实现，没有方法体，是抽象的
}

interface B {
    fun foo() {
        println("B foo")
    }   // 已实现

    fun bar() {
        println("B bar")
    } // 已实现
}

class C : A {
    override fun bar() {
        println("C bar")
    }   // 重写
}

class D : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()

        println("D foo")
    }

    override fun bar() {
        super<B>.bar()
        println("D bar")
    }
}

fun main(args: Array<String>) {
    val d = D()
    d.foo()
    d.bar()
}