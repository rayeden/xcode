package chapter20_annotation.aptAnnotationProcessor;

import chapter20_annotation.aptAnnotationProcessor.database.DBTable;
import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.util.SimpleDeclarationVisitor;
//getDeclarationScanner
import static com.sun.mirror.util.DeclarationVisitors.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * Created by BG289522 on 2017/8/4.
 */

public class TableCreationProcessorFactory implements AnnotationProcessorFactory {

    private String sql = "";

    @Override
    public Collection<String> supportedOptions() {
        return Collections.emptySet();
    }

    @Override
    public Collection<String> supportedAnnotationTypes() {
        return Arrays.asList(
                "chapter20_annotation.aptAnnotationProcessor.database.Constraints",
                "chapter20_annotation.aptAnnotationProcessor.database.Constraints.DBTable",
                "chapter20_annotation.aptAnnotationProcessor.database.Constraints.SQLDateTime",
                "chapter20_annotation.aptAnnotationProcessor.database.Constraints.SQLDecimal",
                "chapter20_annotation.aptAnnotationProcessor.database.Constraints.SQLInteger",
                "chapter20_annotation.aptAnnotationProcessor.database.Constraints.SQLString",
                "chapter20_annotation.aptAnnotationProcessor.database.Constraints.Uniqueness"
        );
    }

    @Override
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> set, AnnotationProcessorEnvironment env) {
        return new TableCreationProcessor(env);
    }

    //注解处理器
    private class TableCreationProcessor implements AnnotationProcessor {

        private final AnnotationProcessorEnvironment env;

        private TableCreationProcessor(AnnotationProcessorEnvironment env) {
            this.env = env;
        }

        //对注解执行的操作
        @Override
        public void process() {
            for (TypeDeclaration typeDecl : env.getSpecifiedTypeDeclarations()) {
                typeDecl.accept(getDeclarationScanner(new TableCreationVisitor(), NO_OP));
                sql = sql.substring(0, sql.length() -1) + ")";
                System.out.println("creation SQL is :\n + sql");
                sql = "";
            }
        }
    }

    private class TableCreationVisitor extends SimpleDeclarationVisitor{

        public void visitClassDeclaration(ClassDeclaration d){
            DBTable dbTable = d.getAnnotation(DBTable.class);
            if(dbTable != null){
                sql += "CREATE TABLE ";
                sql += (dbTable.name().length() < 1) ? d.getSimpleName().toUpperCase() : dbTable.name();
                sql += "(";
            }
        }

    }

}


