package com.zy.test


//扩展函数，swap ,调换不同位置的值
fun MutableList<Int>.swap(index1:Int,index2:Int){
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

val <T> List<T>.lastIndex: Int
    get() = size - 1

fun main(args: Array<String>) {
    val list = mutableListOf(1,2,3)
    println(list)
    list.swap(0,2)
    println(list)
    list.swap(0,1)
    println(list)

    println(list.lastIndex)
}


