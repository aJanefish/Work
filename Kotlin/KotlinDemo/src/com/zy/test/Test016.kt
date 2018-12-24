package com.zy.test

//泛型

class Box<T>(t:T){
    var value  = t
}

fun main(args: Array<String>) {
    var boxInt = Box<Int>(10)
    var boxString = Box<String>("zhangyu")

    println(boxInt.value)
    println(boxString.value)

}