package com.zy.test

//类定义
//Kotlin 类可以包含：构造函数和初始化代码块、函数、属性、内部类、对象声明。
//Kotlin 中使用关键字 class 声明类，后面紧跟类名：

class Empty

class Runoob {  // 类名为 Runoob
    // 大括号内是类体构成
    var name: String? = "zhangyu"
        // 将变量赋值后转换为大写
        get() = field?.toUpperCase() ?: "EMPTY"

    var url: String = "singou"

    private var _city: String = "shenzhen"
    var city: String
        get() = _city
        private set(value) {
            _city = value
        }
    var age:Int = 18
        get() = field*1 // // 后端变量
        set(value) {
            field = if (value>18)
                18
            else
                -1
        }

    init {
        println("FirstName is $name")
    }

    constructor(name: String?, url: String, city: String) {
        this.name = name
        this.url = url
        this.city = city
    }

    constructor()


    fun foo() {
        println("Foo")
    } // 成员函数

    override fun toString(): String {
        return "Runoob(name=$name, url='$url', city='$city')"
    }

}

fun main(args: Array<String>) {
    println(Empty())
    classTest()
}

fun classTest() {
    var runoob: Runoob = Runoob()
    println(runoob)
    runoob.foo()
    println(runoob.city+" , "+runoob.name+" , "+runoob.url)

    var runoob1: Runoob = Runoob(null,"bb","sichuan")
    println(runoob1)
    println(runoob1.age)
    runoob1.age = 15
    println(runoob1.age)

}