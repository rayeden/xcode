import java.math.BigDecimal;

/**
 * Created by BG289522 on 2017/7/7.
 */
public class BigDecimalTest {

    public static void main(String[] args) {

        BigDecimal num = BigDecimal.valueOf(2.5);

        num = num.setScale(2);

        //设置成两位小数，2.50
        System.out.println(num);

    }

}
