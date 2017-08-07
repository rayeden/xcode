package chapter20_annotation.aptAnnotationProcessor;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Created by xhtc on 2017/8/4.
 */

/**
 * apt工具需要一个工厂类来为其指明正确的处理器
 */
public class InterfaceExtractorProcessorFactory implements AnnotationProcessorFactory {
    @Override
    public Collection<String> supportedOptions() {
        return Collections.emptySet();
    }

    //提供注解完整的类名
    @Override
    public Collection<String> supportedAnnotationTypes() {
        return Collections.singleton("chapter20_annotation.aptAnnotationProcessor.ExtractInterface");
    }

    //返回注解处理器
    @Override
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> set, AnnotationProcessorEnvironment env) {
        return new InterfaceExtractorProcessor(env);
    }

}
