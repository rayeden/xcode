package chapter20_annotation.aptAnnotationProcessor;


import chapter20_annotation.aptAnnotationProcessor.ExtractInterface;
import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.declaration.MethodDeclaration;
import com.sun.mirror.declaration.Modifier;
import com.sun.mirror.declaration.ParameterDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xhtc on 2017/8/4.
 */

@ExtractInterface("IDivisor")
public class InterfaceExtractorProcessor implements AnnotationProcessor {

    private final AnnotationProcessorEnvironment env;

    private List<MethodDeclaration> interfaceMethods = new ArrayList<>();

    public InterfaceExtractorProcessor(AnnotationProcessorEnvironment env) {
        this.env = env;
    }

    @Override
    public void process() {
        for (TypeDeclaration typeDecl : env.getSpecifiedTypeDeclarations()) {
            interfaceMethods.clear();
            ExtractInterface annot = typeDecl.getAnnotation(ExtractInterface.class);
            if (annot == null)
                break;
            for (MethodDeclaration m : typeDecl.getMethods()) {
                //找到接口中的public方法，不包括static，添加到方法列表中
                if (m.getModifiers().contains(Modifier.PUBLIC) && !(m.getModifiers().contains(Modifier.STATIC)))
                    interfaceMethods.add(m);
            }
            if (interfaceMethods.size() > 0) {
                try {
                    //以将要新建的类或接口名称，打开一个普通的输出流
                    PrintWriter writer = env.getFiler().createSourceFile(annot.value());
                    writer.println("public interface " + annot.value() + " {");
                    for (MethodDeclaration m : interfaceMethods) {
                        writer.print("  public ");
                        writer.print(m.getReturnType() + " ");
                        writer.print(m.getSimpleName() + "(");
                        int i = 0;
                        for (ParameterDeclaration parm : m.getParameters()) {
                            writer.print(parm.getType() + " " + parm.getSimpleName());
                            if (++i < m.getParameters().size())
                                writer.print(", ");
                        }
                        writer.print(");");
                    }
                    writer.println("}");
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
