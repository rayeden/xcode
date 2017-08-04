package chapter20_annotation.generatingExternalFiles;

/**
 * Created by xhtc on 2017/8/2.
 */

import chapter20_annotation.aptAnnotationProcessor.database.*;

import java.math.BigDecimal;

/**
 * 用注解设置成员变量
 */

@DBTable(name = "MemberName")
public class Member {

    //默认赋值到int
    @SQLString(length = 30)
    private String firstName;

    @SQLString(length = 50)
    private String lastName;

    @SQLInteger
    private Integer age;

    @SQLString(length = 30, constrants = @Constraints(primaryKey = true))
    private String handle;

    //创建时间
    @SQLDateTime(constraints = @Constraints(allowNull = false))
    private String createTime;

    @SQLDecimal
    private BigDecimal salary;

    private static int memberCount;

    public String getCreateTime() {
        return createTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getHandle() {
        return handle;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public static int getMemberCount() {
        return memberCount;
    }

    public String toString() {
        return handle;
    }

}
