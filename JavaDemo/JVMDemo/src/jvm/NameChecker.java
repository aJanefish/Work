package jvm;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;

public class NameChecker {

    private final Messager messager;

    NameCheckerScanner nameCheckerScanner;


    public NameChecker(ProcessingEnvironment processingEnvironment) {
        this.messager = processingEnvironment.getMessager();
        nameCheckerScanner = new NameCheckerScanner(messager);
    }

    public void checkName(Element rootElement) {
        nameCheckerScanner.scan(rootElement);
    }
}
