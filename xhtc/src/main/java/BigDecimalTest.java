import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by xhtc on 2017/7/7.
 */
public class BigDecimalTest {

    public static void main(String[] args) {

        BigDecimal num = BigDecimal.valueOf(2.5);

        num = num.setScale(2);

        //设置成两位小数，2.50
        System.out.println(num);

        //转成两位小数的String
        BigDecimal num1 = BigDecimal.valueOf(2.358);
        DecimalFormat df = new DecimalFormat("0.00");
        //向下取整
        df.setRoundingMode(RoundingMode.FLOOR);
        System.out.println(df.format(num1));

    }

}
