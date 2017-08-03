package chapter20_annotation.generatingExternalFiles;

/**
 * Created by xhtc on 2017/8/2.
 */

/**
 * 用注解设置成员变量
 */

@DBTable(name = "MemberName")
public class Member {

    //默认赋值到int
    @SQLString(30)
    String firstName;

    @SQLString(50)
    String lastName;

    @SQLInteger
    Integer age;

    @SQLString(value = 30, constrants = @Constraints(primaryKey = true))
    String handle;

    static int memberCount;

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

    public String toString(){
        return handle;
    }

}
