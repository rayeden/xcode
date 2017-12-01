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

public class ValidatorClass4Group {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private static class Person {

        @Getter @Setter
        @NotNull(message = "name不可为空", groups = {INFO.class, STUDENT.class, EMPLOYEE.class})
        private String name;

        @Getter @Setter
        @NotNull(message = "studentId不可为空", groups = {INFO.class, STUDENT.class})
        private Integer studentId;

        @Getter @Setter
        @NotNull(message = "employId不可为空", groups = {INFO.class, EMPLOYEE.class})
        private Integer employId;
    }

    private static void check(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> violations = validator.validate(object, groups);
        Iterator<ConstraintViolation<Object>> iterator = violations.iterator();
        if (iterator.hasNext()) {
            String errMessage = iterator.next().getMessage();
            throw new ValidationException(errMessage);
        }
    }

    private static void test1() {
        Person intern = new Person();
        intern.setName("xhtc");
        intern.setStudentId(1);
        intern.setEmployId(1);
        try {
            check(intern, INFO.class);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("test1 passed");
    }

    private static void test2() {
        Person student = new Person();
        student.setName("xhtc");
        student.setStudentId(1);
        try {
            check(student, STUDENT.class);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("test2 passed");
    }

    private static void test3() {
        Person employee = new Person();
        employee.setName("xhtc");
        employee.setEmployId(1);
        try {
            check(employee, EMPLOYEE.class);
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("test3 passed");
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

    interface STUDENT {};

    interface EMPLOYEE {};

    interface INFO {};
}
