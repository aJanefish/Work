package com.zy.compiler;


import com.google.auto.service.AutoService;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.sun.net.httpserver.Headers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.logging.LogRecord;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * @AutoService(Processor.class) 这个注解不要忘了，否则无法生成Java文件
 */

@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor {

    private Filer mFiler;
    private Elements mElementUtils;
    private Messager mMessager;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        mFiler = processingEnv.getFiler();

    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        createJava("MyTestClass");
        createHelloWorld("HelloWorld");
        createParameterSpec("ParameterSpecTest");
        createFields("FieldsTest");
        createInterfaces("InterfacesTest");
        createEnums("EnumsTest");
        createEnums1("Roshambo");
        create("InnerClassesTest");


        return true;
    }

    //Anonymous Inner Classes
    private void create(String className) {
        MethodSpec toString = MethodSpec.methodBuilder("toString")
                .addAnnotation(Override.class)
                .returns(String.class)
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return $S", "Hoverboard")
                .build();

        TypeSpec comparator = TypeSpec.anonymousClassBuilder("")
                .addSuperinterface(ParameterizedTypeName.get(Comparator.class, String.class))
                .addMethod(MethodSpec.methodBuilder("compare")
                        .addAnnotation(Override.class)
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(String.class, "a")
                        .addParameter(String.class, "b")
                        .returns(int.class)
                        .addStatement("return $N.length() - $N.length()", "a", "b")
                        .build())
                .build();

        TypeSpec helloWorld = TypeSpec.classBuilder(className)
                .addMethod(MethodSpec.methodBuilder("sortByLength")
                        .addParameter(ParameterizedTypeName.get(List.class, String.class), "strings")
                        .addStatement("$T.sort($N, $L)", Collections.class, "strings", comparator)
                        .build())
                .addMethod(toString)
                .build();

        // 创建Java文件
        JavaFile javaFile = JavaFile.builder("com.zy.annotationdemo", helloWorld).build();

        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void createEnums1(String className) {
        TypeSpec helloWorld = TypeSpec.enumBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addEnumConstant("ROCK", TypeSpec.anonymousClassBuilder("$S", "fist")
                        .addMethod(MethodSpec.methodBuilder("toString")
                                .addAnnotation(Override.class)
                                .addModifiers(Modifier.PUBLIC)
                                .addStatement("return $S", "avalanche!")
                                .returns(String.class)
                                .build())
                        .build())
                .addEnumConstant("SCISSORS", TypeSpec.anonymousClassBuilder("$S", "peace")
                        .build())
                .addEnumConstant("PAPER", TypeSpec.anonymousClassBuilder("$S", "flat")
                        .build())
                .addField(String.class, "handsign", Modifier.PRIVATE, Modifier.FINAL)
                .addMethod(MethodSpec.constructorBuilder()
                        .addParameter(String.class, "handsign")
                        .addStatement("this.$N = $N", "handsign", "handsign")
                        .build())
                .build();


        // 创建Java文件
        JavaFile javaFile = JavaFile.builder("com.zy.annotationdemo", helloWorld).build();

        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createEnums(String className) {

        TypeSpec helloWorld = TypeSpec.enumBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addEnumConstant("ROCK")
                .addEnumConstant("SCISSORS")
                .addEnumConstant("PAPER")
                .addField(TypeName.BOOLEAN, "flag", Modifier.PUBLIC)
                .build();

        // 创建Java文件
        JavaFile javaFile = JavaFile.builder("com.zy.annotationdemo", helloWorld).build();

        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createInterfaces(String className) {

        TypeSpec helloWorld = TypeSpec.interfaceBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec.builder(String.class, "ONLY_THING_THAT_IS_CONSTANT")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                        .initializer("$S", "change")
                        .build())
                .addMethod(MethodSpec.methodBuilder("beep")
                        .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                        .returns(TypeName.BOOLEAN)
                        .build())
                .build();

        // 创建Java文件
        JavaFile javaFile = JavaFile.builder("com.zy.annotationdemo", helloWorld).build();

        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFields(String className) {
        FieldSpec android = FieldSpec.builder(String.class, "android")
                .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
                .initializer("$S + $L", "Lollipop v.", 5.0d)
                .build();


        TypeSpec helloWorld = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addField(android)
                .addField(String.class, "robot", Modifier.PRIVATE)
                .build();

        // 创建Java文件
        JavaFile javaFile = JavaFile.builder("com.zy.annotationdemo", helloWorld).build();

        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void createParameterSpec(String className) {
        ParameterSpec android = ParameterSpec.builder(String.class, "android")
                .addModifiers(Modifier.FINAL)
                .build();

        MethodSpec welcomeOverlords = MethodSpec.methodBuilder("welcomeOverlords")
                .addParameter(android)
                .addParameter(String.class, "robot", Modifier.FINAL)
                .build();

        TypeSpec helloWorld = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(welcomeOverlords)
                .build();

        // 创建Java文件
        JavaFile javaFile = JavaFile.builder("com.zy.annotationdemo", helloWorld).build();

        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createHelloWorld(String className) {
        MethodSpec flux = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(String.class, "greeting")
                .addStatement("this.$N = $N", "greeting", "greeting")
                .build();

        TypeSpec helloWorld = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addField(String.class, "greeting", Modifier.PRIVATE, Modifier.FINAL)
                .addMethod(flux)
                .build();

        // 创建Java文件
        JavaFile javaFile = JavaFile.builder("com.zy.annotationdemo", helloWorld).build();

        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createJava(String className) {
        List<FieldSpec> fieldSpecs = getFieldSpecs();
        List<MethodSpec> methodSpecs = getMethodSpecs();


        TypeSpec finderClass = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .addFields(fieldSpecs)
                .addMethods(methodSpecs)
                .build();

        // 创建Java文件
        JavaFile javaFile = JavaFile.builder("com.zy.annotationdemo", finderClass).build();

        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Filed
    private List<FieldSpec> getFieldSpecs() {

        List<FieldSpec> fieldSpecs = new ArrayList<>();
        FieldSpec fieldSpec = FieldSpec.builder(Integer.TYPE, "age", Modifier.PUBLIC).build();
        fieldSpecs.add(fieldSpec);

        FieldSpec fieldSpec1 = FieldSpec.builder(TypeName.OBJECT, "object", Modifier.PUBLIC, Modifier.VOLATILE)
                .addAnnotation(Deprecated.class)
                .addJavadoc("Javadoc", new File(""))
                .build();

        fieldSpecs.add(fieldSpec1);

        return fieldSpecs;
    }

    //Method
    private List<MethodSpec> getMethodSpecs() {
        MethodSpec setAge = MethodSpec.methodBuilder("setAge")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Deprecated.class)
                .addParameter(ParameterSpec.builder(TypeName.INT.box(), "age").build())
                //.addCode(CodeBlock.builder().add("age").build())
                .build();

        MethodSpec getAge = MethodSpec.methodBuilder("getAge")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Deprecated.class)
                .build();

        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .build();


        MethodSpec main1 = MethodSpec.methodBuilder("main1")
                .addCode(""
                        + "int total = 0;\n"
                        + "for (int i = 0; i < 10; i++) {\n"
                        + "  total += i;\n"
                        + "}\n")
                .build();


        MethodSpec main2 = MethodSpec.methodBuilder("main2")
                .addStatement("int total = 0")
                .beginControlFlow("for (int i = 0; i < 10; i++)")
                .addStatement("total += i")
                .endControlFlow()
                .addStatement("System.out.println(total)")
                .build();

        MethodSpec main4 = MethodSpec.methodBuilder("main4")
                .addStatement("long now = $T.currentTimeMillis()", System.class)
                .beginControlFlow("if ($T.currentTimeMillis() < now)", System.class)
                .addStatement("$T.out.println($S)", System.class, "Time travelling, woo hoo!")
                .nextControlFlow("else if ($T.currentTimeMillis() == now)", System.class)
                .addStatement("$T.out.println($S)", System.class, "Time stood still!")
                .nextControlFlow("else")
                .addStatement("$T.out.println($S)", System.class, "Ok, time still moving forward")
                .endControlFlow()
                .build();


        MethodSpec main5 = MethodSpec.methodBuilder("main5")
                .beginControlFlow("try")
                .addStatement("throw new Exception($S)", "Failed")
                .nextControlFlow("catch ($T e)", Exception.class)
                .addStatement("throw new $T(e)", RuntimeException.class)
                .endControlFlow()
                .build();

        List<MethodSpec> methodSpecs = new ArrayList<>();
        methodSpecs.add(setAge);
        methodSpecs.add(getAge);
        methodSpecs.add(main);
        methodSpecs.add(main1);
        methodSpecs.add(main2);
        methodSpecs.add(computeRange("main3", 0, 10, "+"));
        methodSpecs.add(computeRange1("main6", 0, 10, "+"));
        methodSpecs.add(main4);
        methodSpecs.add(main5);
        methodSpecs.add(whatsMyName("slimShady"));
        methodSpecs.add(whatsMyName("eminem"));
        methodSpecs.add(whatsMyName("marshallMathers"));
        methodSpecs.add(getToday());
        methodSpecs.add(getMethodSpec("tomorrow", ClassName.get(Date.class)));
        methodSpecs.add(getListMethodSpec());
        methodSpecs.add(getMethodSpec1());
        methodSpecs.add(getMethodSpec2());
        methodSpecs.add(getMethodSpec3());


        return methodSpecs;
    }

    private MethodSpec getMethodSpec3() {
        MethodSpec flux = MethodSpec.methodBuilder("flux")
                .addModifiers(Modifier.ABSTRACT, Modifier.PROTECTED)
                .build();
        return flux;
    }


    private MethodSpec getMethodSpec2() {
        MethodSpec hexDigit = MethodSpec.methodBuilder("hexDigit")
                .addParameter(int.class, "i")
                .returns(char.class)
                .addStatement("return (char) (i < 10 ? i + '0' : i - 10 + 'a')")
                .build();
        return hexDigit;
    }

    private MethodSpec getMethodSpec1() {


        MethodSpec byteToHex = MethodSpec.methodBuilder("byteToHex")
                .addParameter(int.class, "b")
                .returns(String.class)
                .addStatement("char[] result = new char[2]")
                .addStatement("result[0] = $N((b >>> 4) & 0xf)", getMethodSpec2())
                .addStatement("result[1] = $N(b & 0xf)", getMethodSpec2())
                .addStatement("return new String(result)")
                .build();
        return byteToHex;
    }

    //$N for Names


    //$T for Types
    private static MethodSpec getListMethodSpec() {
        ClassName hoverboard = ClassName.get("java.util", "Date");
        ClassName list = ClassName.get("java.util", "List");
        ClassName arrayList = ClassName.get("java.util", "ArrayList");
        TypeName listOfHoverboards = ParameterizedTypeName.get(list, hoverboard);

        MethodSpec beyond = MethodSpec.methodBuilder("beyond")
                .returns(listOfHoverboards)
                .addStatement("$T result = new $T<>()", listOfHoverboards, arrayList)
                .addStatement("result.add(new $T())", hoverboard)
                .addStatement("result.add(new $T())", hoverboard)
                .addStatement("result.add(new $T())", hoverboard)
                .addStatement("return result")
                .build();
        return beyond;
    }


    private static MethodSpec getToday() {
        MethodSpec today = MethodSpec.methodBuilder("today")
                .returns(Date.class)
                .addStatement("return new $T()", Date.class)
                .build();
        return today;
    }

    private static MethodSpec getMethodSpec(String name, ClassName className) {
        MethodSpec today = MethodSpec.methodBuilder(name)
                .returns(className)
                .addStatement("return new $T()", className)
                .build();
        return today;
    }


    //$S for Strings
    private static MethodSpec whatsMyName(String name) {
        return MethodSpec.methodBuilder(name)
                .returns(String.class)
                .addStatement("return $S", name)
                .build();
    }

    private MethodSpec computeRange1(String name, int from, int to, String op) {
        return MethodSpec.methodBuilder(name)
                .returns(int.class)
                .addStatement("int result = 0")
                .beginControlFlow("for (int i = $L; i < $L; i++)", from, to)
                .addStatement("result = result $L i", op)
                .endControlFlow()
                .addStatement("return result")
                .build();
    }

    private MethodSpec computeRange(String name, int from, int to, String op) {
        return MethodSpec.methodBuilder(name)
                .returns(int.class)
                .addStatement("int result = 1")
                .beginControlFlow("for (int i = " + from + "; i < " + to + "; i++)")
                .addStatement("result = result " + op + " i")
                .endControlFlow()
                .addStatement("return result")
                .build();
    }

    /**
     * 你好
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(Override.class.getCanonicalName());
//        types.add(OnClick.class.getCanonicalName());
        return types;
    }


}
