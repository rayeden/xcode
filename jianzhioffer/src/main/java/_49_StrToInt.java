/**
 * Created by Zetrov on 2016-12-03.
 *
 * 把字符串转换成整数
 * 考虑数据溢出
 *
 */
public class _49_StrToInt {

    public static void main(String[] args) {
        String s = "-12312312312314434";
        System.out.println(solution(s));
    }

    public static long solution(String s){
        if(s == null)
            return 0;
        char[] ch = s.toCharArray();
        int len = s.length();
        int index = 0;
        int sign = 1;
        long res = 0;
        if(ch[0] == '+' || ch[0] == '-') {
            ++ index;
            if(ch[0] == '-')
                sign = -1;
        }
        for(int i = index; i < len; ++i){
            if(ch[i] < '0' || ch[i] > '9')
                throw new NumberFormatException();
            res = (ch[i] - '0') + res * 10;
        }
        return res * sign;
    }
}
