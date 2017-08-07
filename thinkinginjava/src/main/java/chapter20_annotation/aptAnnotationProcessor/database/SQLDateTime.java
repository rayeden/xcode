package chapter20_annotation.aptAnnotationProcessor.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xhtc on 2017/8/4.
 */

/**
 * 自定义数据库字段DateTime类型
 *
 * 注解元素仅支持所有基本类型(int, float, boolean等),String,Class,enum,Annotation
 * 不支持DateTime
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLDateTime {

    //字段名称
    String name() default "";

//    Date dateTime() default ?

    Constraints constraints() default @Constraints;

}
