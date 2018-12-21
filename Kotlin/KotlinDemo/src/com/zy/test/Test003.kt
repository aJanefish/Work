package com.zy.test

//NULL检查机制
//Kotlin 的空安全设计对于声明可为空的参数，在使用时要进行空判断处理
//有两种处理方式：
// 1字段后加!!像Java一样抛出空异常。
// 2字段后加？可不做处理返回值为null或配合?:做空判断处理

fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        // 做过类型判断以后，obj会被系统自动转换为String类型
        return obj.length
    }

    //在这里还有一种方法，与Java中instanceof不同，使用!is
    // if (obj !is String){
    //   // XXX
    // }

    // 这里的obj仍然是Any类型的引用
    return null
}

fun getStringLength1(obj: Any): Int? {
    // 在 `&&` 运算符的右侧, `obj` 的类型会被自动转换为 `String`
    if (obj is String && obj.length > 0)
        return obj.length
    return null
}


fun main(args: Array<String>) {
    //类型后面加?表示可为空
    var age : String ? = "23"
    println(age)
    //age = null

    //抛出空指针异常
    val ages = age!!.toInt()
    println(ages)
    //不作处理,返回null
    age = null
    val ages1 = age?.toInt()
    println(ages1)

    //age为空返回-1
    val ages2 = age?.toInt()?: -1
    println(ages2)
    age = "23"
    val ages3 = age?.toInt()?: -1
    println(ages3)

    println("lenght:${getStringLength(11)}")
}
