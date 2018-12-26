package com.zy.interfaces;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//声明Test注解
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {

}

//public enum ElementType {
//    /** Class, interface (including annotation type), or enum declaration */
//    /**标明该注解可以用于类、接口（包括注解类型）或enum声明*/
//    TYPE,
//
//    /** Field declaration (includes enum constants) */
//    /** 标明该注解可以用于字段(域)声明，包括enum实例 */
//    FIELD,
//
//    /** Method declaration */
//    /** 标明该注解可以用于方法声明 */
//    METHOD,
//
//    /** Formal parameter declaration */
//    /** 标明该注解可以用于参数声明 */
//    PARAMETER,
//
//    /** Constructor declaration */
//    /** 标明注解可以用于构造函数声明 */
//    CONSTRUCTOR,
//
//    /** Local variable declaration */
//    /** 标明注解可以用于局部变量声明 */
//    LOCAL_VARIABLE,
//
//    /** Annotation type declaration */
//    /** 标明注解可以用于注解声明(应用于另一个注解上)*/
//    ANNOTATION_TYPE,
//
//    /** Package declaration */
//    /** 标明注解可以用于包声明 */
//    PACKAGE,
//
//    /**
//     * Type parameter declaration
//     *
//     * @since 1.8
//     */
//    TYPE_PARAMETER,
//
//    /**
//     * Use of a type
//     *
//     * @since 1.8
//     */
//    TYPE_USE
//}
