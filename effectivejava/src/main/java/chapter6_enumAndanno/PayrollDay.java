package chapter6_enumAndanno;

/**
 * Created by BG289522 on 2017/6/21.
 */
public enum PayrollDay {

    MONDAY(PayType.WEEKDAY), TUESDAY(PayType.WEEKDAY),
    WENSDAY(PayType.WEEKDAY), THURSDAY(PayType.WEEKDAY),
    FRIDAY(PayType.WEEKDAY), SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

    private final PayType payType;

    PayrollDay(PayType payType){
        this.payType = payType;
    }

    double pay(double hoursWorked, double payRate){
        return payType.pay(hoursWorked, payRate);
    }

    /**
     * 策略枚举
     */
    private enum PayType{
        /**
         * 正常工作日中，超出8小时产生加班工资
         */
        WEEKDAY{
            double overtimePay(double hours, double payRate){
                return hours <= HOURS_PER_SHIFT ? 0 : (hours - HOURS_PER_SHIFT) * payRate / 2;
            }
        },
        /**
         * 周末，都会产生加班工资
         */
        WEEKEND{
            double overtimePay(double hours, double payRate){
                return hours * payRate / 2;
            }
        };

        private static final int HOURS_PER_SHIFT= 8;

        abstract double overtimePay(double hrs, double payRate);

        double pay(double hoursWorked, double payRate){
            double basePay = hoursWorked * payRate;
            return basePay + overtimePay(hoursWorked, payRate);
        }
    }

    public static void main(String[] args) {
        for(PayrollDay day : PayrollDay.values()){
            System.out.println(day.pay(10, 0.5));
        }
    }
}
