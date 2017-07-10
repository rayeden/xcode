import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by xhtc on 2017/7/6.
 */

public class DateTest {

    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        System.out.println(date);

        //使用getTime方法取出的就是从1970-01-01 00:00:00以来的毫秒数,是一个长整型数
        System.out.println(date.getTime());


        //MM要大些， HH是24小时制，hh是12小时制
        SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(date));


        String txt = "2017-01-01 00:00:00";
        date = format.parse(txt);
        System.out.println(date);
        System.out.println(format.format(date));

        Calendar calendar = new GregorianCalendar();
        calendar.set(2017,7,1,19,0,0);
        System.out.println(format.format(calendar.getTime()));

        calendar.add(Calendar.MONTH, -2);
        System.out.println(format.format(calendar.getTime()));

        //一个月中的第几天
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        //一天中的第几个小时
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));

        //比较时间大小
        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTime(new Date());
        Calendar calendar3 = new GregorianCalendar();
        calendar3.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DATE), 16,0,0);

        System.out.println(format.format(calendar3.getTime()));
        System.out.println(format.format(calendar2.getTime()));

        System.out.println(calendar2.before(calendar3));

        System.out.println("---------------SQL TIME TEST---------------");

        sqlTimeTest();

        addDay();

    }

    public static void sqlTimeTest(){

        SimpleDateFormat sqlTimeformat  = new SimpleDateFormat("HH:mm:ss");
        Time time = new Time(13,0,0);
        Date now = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        String nowStr = sqlTimeformat.format(now);
        Time timeNow  = new Time(calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
        System.out.println(nowStr);
        System.out.println("time is: " + time + ", now is: " + timeNow);
        System.out.println(time.before(timeNow));

    }


    /**
     * Calendar增加日期后，月份同时改变
     * @throws ParseException
     */
    public static void addDay() throws ParseException {

        SimpleDateFormat dateformat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String txt = "2017-01-31 00:00:00";
        Date date = dateformat.parse(txt);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        System.out.println(calendar.get(Calendar.MONTH) + 1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        calendar.add(Calendar.DAY_OF_MONTH, 1);

        System.out.println(calendar.get(Calendar.MONTH) + 1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

    }


}
