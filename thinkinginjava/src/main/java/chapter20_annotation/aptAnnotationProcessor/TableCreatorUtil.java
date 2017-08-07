package chapter20_annotation.aptAnnotationProcessor;

import chapter20_annotation.aptAnnotationProcessor.database.Constraints;
import chapter20_annotation.aptAnnotationProcessor.database.SQLInteger;
import chapter20_annotation.aptAnnotationProcessor.database.SQLString;
import com.sun.mirror.declaration.FieldDeclaration;

/**
 * Created by xhtc on 2017/8/4.
 */
public class TableCreatorUtil {

    //Constraints 约束
    public static String getConstraints(Constraints con) {
        String constraints = "";
        if (!con.allowNull()) {
            constraints += " NOT NULL";
        }
        if (con.primaryKey()) {
            constraints += " PRIMARY KEY";
        }
        if (con.unique()) {
            constraints += " UNIQUE";
        }
        return constraints;
    }

    /**
     * 查看域上的注解
     * @param d
     */
    public static void visitFieldDeclaration(FieldDeclaration d, String sql){
        String columnName = "";
        if(d.getAnnotation(SQLInteger.class) != null){
            SQLInteger sInt = d.getAnnotation(SQLInteger.class);
            if (sInt.name().length() < 1) {
                columnName = d.getSimpleName().toUpperCase();
            } else {
                columnName = sInt.name();
            }
            sql += "\n  " + columnName + " INT" + getConstraints(sInt.constraints()) + ",";
        }
        if(d.getAnnotation(SQLString.class) != null){
            SQLString sString = d.getAnnotation(SQLString.class);
            if (sString.name().length() < 1) {
                columnName = d.getSimpleName().toUpperCase();
            } else {
                columnName = sString.name();
            }
            sql += "\n  " + columnName + " VARCHAR(" + sString.length() + ")" + getConstraints(sString.constrants()) + ",";
        }
    }

}
