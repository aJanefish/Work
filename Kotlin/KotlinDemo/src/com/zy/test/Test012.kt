package com.zy.test
/**
 * 扩展函数
 * 扩展函数可以在已有类中添加新的方法，不会对原类做修改，扩展函数定义形式：
 * fun receiverType.functionName(params){
 *     body
 * }
 * receiverType：表示函数的接收者，也就是函数扩展的对象
 * functionName：扩展函数的名称
 * params：扩展函数的参数，可以为NULL
 *
 * */

class User(var name:String)

/**扩展函数**/
fun User.print(){
    println("用户名 $name")
}

fun User.getName():String{
    return name
}

fun main(arg:Array<String>){
    var user = User("Runoob")
    user.print()
    println(user.getName())
}
