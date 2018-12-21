package com.zy.test

//接口
//接口中的属性只能是抽象的，不允许初始化值，接口不会保存属性值，实现接口时，必须重写属性：

interface MyInterface {
    var name:String //name 属性, 抽象的
    fun bar()    // 未实现
    fun foo() {  //已实现
        // 可选的方法体
        println("foo")
    }
}

class Child : MyInterface {
    override var name: String = "ddd"

    constructor(name: String) {
        this.name = name
        println(this.name)
    }


    override fun bar() {
        // 方法体
        println("bar")
    }
}

fun main(args: Array<String>) {

    var child = Child("Singou")
    child.foo()
    child.bar()
    println(child.name)

}