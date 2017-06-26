import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xhtc on 2017/6/26.
 */
public class Funds {

    private static final BigDecimal salary = BigDecimal.valueOf(20000);

    public static final BigDecimal rate = BigDecimal.valueOf(0.12);

    private static final BigDecimal basicFund = salary.multiply(rate).multiply(BigDecimal.valueOf(2));

    private static final int MONTH = 12;

    private static final BigDecimal PUBLICFUND = BigDecimal.valueOf(0.00325);

    private static final BigDecimal COMFUND = BigDecimal.valueOf(0.0049);

//    private static final Date startWork = DateFormat("Mon Jun 26 14:05:03 CST 2017");

    public static void main(String[] args) {
        BigDecimal sumFund = BigDecimal.valueOf(0);
        BigDecimal monthFund = BigDecimal.valueOf(0);
        for (int i = 1; i <= MONTH; ++i){
            //每个月公积金账户的余额
            monthFund = monthFund.add(basicFund);
            sumFund = sumFund.add(monthFund);
        }
        BigDecimal average = sumFund.divide(BigDecimal.valueOf(12));
        BigDecimal couldGet = average.multiply(BigDecimal.valueOf(15));
        System.out.println("average: " + average);
        System.out.println("couldGet: " + couldGet);
        System.out.println("publicFund per month: " + couldGet.multiply(PUBLICFUND));
        System.out.println("comfund per mount: " + couldGet.multiply(COMFUND));

    }

    private static Date dateFormater(String dateTime){
        String formatPatern = "yyyy-mm-dd hh-mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(formatPatern);
        try{
            return sdf.parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
    }
}

enum Month{

    JANUARY(1), FEBRARY(2), MARCH(3), APRIL(4), MAY(5),
    JUNE(6), JULY(7), AUGUST(8), SEPTEMBER(9), OCTOBER(10),
    NOVEMBER(11), DECENMBER(12);

    private final int month;

    Month(int month){
        this.month = month;
    }

}
