/**
 * Created by zetrov on 2016/12/4.
 *
 * 考虑大数问题，用字符串模拟整数打印
 */
public class _12_Print1ToMaxOfDigits_ {

    public static void main(String[] args) {
        char[] number = {'1','2','3'};
        printNumber(number);
    }

    public static void solution(int n) {
        if(n <= 0)
            return;;

        char[] number = new char[n];
        for(int i = 0; i < n; ++i)
            number[i] = '0';
        number[n] = '\0';

        while (!increment(number)){
            printNumber(number);
        }
    }

    public static boolean increment(char[] number){


        return false;
    }

    static void printNumber(char[] number){
        if(number == null)
            return;;
        int len = number.length;
        if(len == 0)
            System.out.println();
        for (int i = 0; i < len; i++) {
            if(number[i] == '0')
                continue;
            else
                System.out.print(number[i]);
        }
        System.out.println();
    }
}
