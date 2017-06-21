/**
 * Created by Zetrov on 2016-12-03.
 *
 * 不用“+”计算两个数的和
 *
 */
public class _47_TwoSum {

    public static void main(String[] args) {
        System.out.println(solution(3,5));
    }

    public static int solution(int a, int b){

        int sum = 0, carry = 0;

        do{
            // ^计算两个数bit位上的没有进位的结果
            sum = a ^ b;
            // &计算两个数bit位上的进位，左移1位，再做下一次计算进位处的加法
            carry = (a & b) << 1;

            a = sum;
            b = carry;

        }while(b != 0);
        return a;
    }

    public static int solution2(int a, int b){
        if(b == 0)
            return a;
        int sum = a ^ b;
        int carry = (a & b) << 1;
        return solution2(sum, carry);
    }
}
