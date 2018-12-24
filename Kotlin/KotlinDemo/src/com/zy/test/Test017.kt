package com.zy.test


// 创建接口
interface Base {
    fun print()
    fun show()
}

// 实现此接口的被委托的类
class BaseImpl(val x: Int) : Base {
    override fun show() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        println("show")
    }

    override fun print() { println(x) }
}

// 通过关键字 by 建立委托类
class Derived(b: Base) : Base by b

fun main(args: Array<String>) {
    val b = BaseImpl(10)
    Derived(b).print() // 输出 10
    Derived(b).show()
}