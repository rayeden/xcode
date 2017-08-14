package chapter20_annotation.generatingExternalFiles;

import chapter20_annotation.aptAnnotationProcessor.database.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static chapter20_annotation.aptAnnotationProcessor.TableCreatorUtil.getConstraints;

/**
 * Created by xhtc on 2017/8/3.
 *
 * Excersice
 */

/**
 * args: Member
 */
public class TableCreator {

    public static void main(String[] args) throws ClassNotFoundException {
//        if (args.length < 1) {
//            System.out.println("arguments: annotated classes");
//            System.exit(0);
//        }
        String[] tables = new String[]{"chapter20_annotation.generatingExternalFiles.Member"};
        for (String className : tables) {
            //反射得到类型信息
            Class<?> cl = Class.forName(className);
            //获取类上指定的注解
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            //如果参数列表的类中不存在DBTable注解，跳过
            if (dbTable == null) {
                System.out.println("No Table annotation in class " + className);
                continue;
            }
            String tableName = dbTable.name();
            if (tableName.length() < 1) {
                tableName = cl.getName().toUpperCase();
            }
            //类型注解名称
            System.out.println("tableName: " + tableName);
            List<String> columnDefs = new ArrayList<>();
            //获取类中的成员
            for (Field field : cl.getDeclaredFields()) {
                //字段名称
                String columnName = null;
                //获取成员上的注解列表
                Annotation[] anns = field.getDeclaredAnnotations();
                if (anns.length < 1)
                    continue;
                //数据库表中每个字段只会有一种数据类型，数据类型注解中嵌套了约束注解，所以只需用anns[0]
                if (anns[0] instanceof SQLInteger) {
                    SQLInteger sInt = (SQLInteger) anns[0];
                    if (sInt.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sInt.name();
                    }
                    columnDefs.add(columnName + " INT" + getConstraints(sInt.constraints()));
                }
                if (anns[0] instanceof SQLString) {
                    SQLString sString = (SQLString) anns[0];
                    if (sString.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sString.name();
                    }
                    columnDefs.add(columnName + " VARCHAR(" + sString.length() + ")" + getConstraints(sString.constrants()));
                }
                if (anns[0] instanceof SQLDateTime) {
                    SQLDateTime sDateTime = (SQLDateTime) anns[0];
                    if (sDateTime.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sDateTime.name();
                    }
                    columnDefs.add(columnName + " DateTime()" + getConstraints(sDateTime.constraints()));
                }
                if (anns[0] instanceof SQLDecimal) {
                    SQLDecimal sDecimal = (SQLDecimal) anns[0];
                    if (sDecimal.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sDecimal.name();
                    }
                    columnDefs.add(columnName + " Decimal(" + sDecimal.length() + ", " + sDecimal.precision() + ")" + getConstraints(sDecimal.constraints()));
                }
            }
            StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "(");
            for (String columnDef : columnDefs)
                createCommand.append("\n    ").append(columnDef).append(".");
            String tableCreate = createCommand.substring(0, createCommand.length() - 1) + ");";
            System.out.println("Table Creation SQL for " + className + " is :\n" + tableCreate);

        }
    }
}
