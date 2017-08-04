package chapter20_annotation.aptAnnotationProcessor.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by BG289522 on 2017/8/4.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLDecimal {

    //字段名称
    String name() default "";

    //长度为10
    int length() default 10;

    //精度为2
    int precision() default 2;

    Constraints constraints() default @Constraints;

}
