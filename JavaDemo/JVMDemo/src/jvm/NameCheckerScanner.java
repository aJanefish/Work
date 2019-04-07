package jvm;

import javax.annotation.processing.Messager;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner6;
import javax.tools.Diagnostic;
import java.util.EnumSet;
import java.util.Set;


public class NameCheckerScanner extends ElementScanner6<Void, Void> {

    private final Messager messager;

    public NameCheckerScanner(Messager messager) {
        this.messager = messager;
    }

    @Override
    public Void visitType(TypeElement e, Void aVoid) {
        scan(e.getEnclosedElements(), aVoid);
        checkCameCase(e, true);
        super.visitType(e, aVoid);
        return null;
        //return super.visitType(e, aVoid);

    }


    @Override
    public Void visitExecutable(ExecutableElement e, Void aVoid) {

        if (e.getKind() == ElementKind.METHOD) {
            Name name = e.getSimpleName();
            if (name.contentEquals(e.getEnclosingElement().getSimpleName())) {
                messager.printMessage(Diagnostic.Kind.WARNING, "一个普通方法" + name + "不应道与类名重复，避免与构造器函数产生混淆", e);
            }
            checkCameCase(e, false);

        }
        super.visitExecutable(e, aVoid);
        return null;
    }

    @Override
    public Void visitVariable(VariableElement e, Void aVoid) {
        if (e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null || heuristicallyConstant(e)) {
            checkCameCase(e);
        } else
            checkCameCase(e, false);
        return null;

//        return super.visitVariable(e, aVoid);
    }


    private boolean heuristicallyConstant(VariableElement e) {
        if (e.getEnclosingElement().getKind() == ElementKind.INTERFACE) {
            return true;
        } else if (e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(EnumSet.of(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL))) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 检查传入的Element是否符合驼峰式命名法,如果不符合，则输出警告信息
     */
    private void checkCameCase(Element e, boolean initialCaps) {
        String name = e.getSimpleName().toString();
        boolean previousUpper = false;
        boolean conventional = true;
        int firstCodePoint = name.codePointAt(0);
        if (Character.isUpperCase(firstCodePoint)) {
            previousUpper = true;

            if (!initialCaps) {
                messager.printMessage(Diagnostic.Kind.WARNING, "名称" + name + "应当以小写字母开头", e);
                return;
            }
        } else if (Character.isLowerCase(firstCodePoint)) {
            if (initialCaps) {
                messager.printMessage(Diagnostic.Kind.WARNING, "名称" + name + "应当以大写字母开头", e);
                return;
            }
        } else {
            conventional = false;
        }

        if (conventional) {
            int cp = firstCodePoint;

            for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                cp = name.codePointAt(i);
                if (Character.isUpperCase(cp)) {
                    if (previousUpper) {
                        conventional = false;
                        break;
                    }
                    previousUpper = true;
                } else {
                    previousUpper = false;
                }
            }
        }


        if (!conventional) {
            messager.printMessage(Diagnostic.Kind.WARNING, "名称" + name + "应当符合驼峰式命名法(Camel Case Names)", e);
        }
    }


    /**
     * 大写命名检查,要求第一个字母必须是大写的英文字母,其余部分可以使下划线也可以是大写字母
     */
    private void checkCameCase(Element e) {
        String name = e.getSimpleName().toString();
        boolean conventional = true;
        int firstCodePoint = name.codePointAt(0);
        if(!Character.isUpperCase(firstCodePoint)){
            conventional = false;
        }else {
            boolean previousUnderscore = false;
            int cp = firstCodePoint;
            for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                cp = name.codePointAt(i);
                if(cp == (int)'_'){
                    if(previousUnderscore){
                        conventional = false;
                        break;
                    }
                    previousUnderscore = true;
                }else {
                    previousUnderscore = false;
                    if(!Character.isUpperCase(cp) && !Character.isDigit(cp)){
                        conventional = false;
                        break;
                    }

                }
            }
        }

        if (!conventional) {
            messager.printMessage(Diagnostic.Kind.WARNING, "常亮" + name + "应当字母必须是大写的英文字母,其余部分可以使下划线也可以是大写字母", e);
        }
    }
}
