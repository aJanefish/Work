package com.zy.test

//继承
//构造函数
//子类有主构造函数


open class Person(var name: String) {
    // 基类
    var name1: String? = name
    var age1: Int = 2

    /**次级构造函数**/
    constructor(name: String, age: Int) : this(name) {
        this.age1 = age
        //初始化
        println("-------基类次级构造函数---------")
    }

    public open fun show(){
        println("$name <--> $age1")
    }
}

//如果子类有主构造函数， 则基类必须在主构造函数中立即初始化。
class Student(name: String, age: Int, var no: String, var score: Int) : Person(name, age) {
    var no1: String = no
    var score1: Int = score
}

/**子类继承 Person 类**/
class StudentTwo : Person {
    var no : String  = ""
    var score : Int = 0

    /**次级构造函数**/
    constructor(name: String, age: Int, no: String, score: Int) : super(name, age) {
        println("-------继承类次级构造函数---------")
        println("学生名： ${name}")
        println("年龄： ${age}")
        println("学生号： ${no}")
        println("成绩： ${score}")
        this.no = no
        this.score = score
    }

    override fun show() {
        super<Person>.show()
        println("$no < -- >$score")
    }
}

fun main(args: Array<String>) {
    var student: Student = Student("ZhangYu", 25, "001", 100)

    println("学生名： ${student.name1}")
    println("年龄： ${student.age1}")
    println("学生号： ${student.no1}")
    println("成绩： ${student.score1}")


    var studentTwo: StudentTwo = StudentTwo("ZhangYu", 25, "001", 100)
    studentTwo.show()

}