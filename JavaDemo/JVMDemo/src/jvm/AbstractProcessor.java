package jvm;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

public abstract class AbstractProcessor {
    public void init(ProcessingEnvironment processingEnvironment){};

    public abstract boolean process(Set<? extends TypeElement> annotations, RoundEnvironment r);
}
