package chapter20_annotation.generatingExternalFiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xhtc on 2017/8/2.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {

    int value() default 0;

    String name() default "";

    Constraints constrants() default @Constraints;
}
