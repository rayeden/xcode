package chapter20_annotation.generatingExternalFiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xhtc on 2017/8/2.
 */

//Target约束注解，只能应用于该类型
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {

    String name() default "";

}
