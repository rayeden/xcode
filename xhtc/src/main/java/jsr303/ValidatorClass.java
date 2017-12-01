package jsr303;

import lombok.Getter;
import lombok.Setter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.Set;

public class ValidatorClass {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private static class Person {

        @Getter
        @Setter
        @NotNull(message = "name不可为空")
        private String name;
    }


    static void check(Object object) {
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        Iterator<ConstraintViolation<Object>> iterator = violations.iterator();
        if (iterator.hasNext()) {
            String errMessage = iterator.next().getMessage();
            System.out.println(errMessage);
            throw new ValidationException(errMessage);
        }
    }

    private static void test1() {
        //实例化对象但不设置name
        Person person = new Person();
        try {
            check(person);
        } catch (ValidationException e) {
            //输出“name不可为空”
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("test1 passed");
    }

    private static void test2() {
        //实例化对象且设置name
        Person person = new Person();
        person.setName("xhtc");
        try {
            check(person);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("test2 passed");
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
