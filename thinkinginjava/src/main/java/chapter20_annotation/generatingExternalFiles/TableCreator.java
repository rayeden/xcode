package chapter20_annotation.generatingExternalFiles;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xhtc on 2017/8/3.
 */

/**
 * args: Member
 */
public class TableCreator {

    public static void main(String[] args) throws ClassNotFoundException {
        if(args.length < 1){
            System.out.println("arguments: annotated classes");
            System.exit(0);
        }
        for(String className : args){
            //反射得到类型信息
            Class<?> cl = Class.forName(className);
            //获取类上指定的注解
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            //如果参数列表的类中不存在DBTable注解，跳过
            if(dbTable == null){
                System.out.println("No Table annotation in class " + className);
                continue;
            }
            String tableName = dbTable.name();
            if(tableName.length() < 1){
                tableName = cl.getName().toUpperCase();
            }
            List<String> columnDefs = new ArrayList<>();
            //获取类中的成员
            for(Field field : cl.getDeclaredFields()){
                String columnName = null;
                //获取成员上的注解列表
                Annotation[] anns = field.getDeclaredAnnotations();
                if(anns.length < 1)
                    continue;
                if(anns[0] instanceof SQLInteger) {
                    SQLInteger sInt = (SQLInteger) anns[0];
                    if(sInt.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sInt.name();
                    columnDefs.add(columnName + " INT" + getConstraints(sInt.constraints()));
                }
                if(anns[0] instanceof SQLString){
                    SQLString sString = (SQLString) anns[0];
                    if(sString.name().length() < 1){
                        columnName = field.getName().toUpperCase();
                    }
                    else {
                        columnName = sString.name();
                    }
                    columnDefs.add(columnName + " VARCHAR(" + sString.value() +")" + getConstraints(sString.constrants()));
                }
                StringBuilder createCommand = new StringBuilder("CREATE TABLE " + tableName + "(");
                for(String columnDef : columnDefs)
                    createCommand.append("\n    " + columnDef + ".");
                String tableCreate = createCommand.substring(0,createCommand.length() - 1) + ");";
                System.out.println("Table Creation SQL for " + className + " is :\n" + tableCreate);
            }

        }
    }

    //Constraints 约束
    private static String getConstraints(Constraints con){
        String constraints = "";
        if(!con.allowNull()){
            constraints += " NOT NULL";
        }
        if(con.primaryKey()) {
            constraints += " PRIMARY KEY";
        }
        if(con.unique()){
            constraints += " UNIQUE";
        }
        return constraints;
    }

}
