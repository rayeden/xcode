/**
 * Created by zetrov on 2016/12/4.
 *
 * 判断整数二进制中1的个数
 * 位运算问题
 *
 */
public class _10_NumberOf1 {

    public static void main(String[] args) {
        System.out.println(solution2(5));
    }

    public static int solution2(int n){
        int count = 0;
        while(n != 0){
            count++;
            n = n & (n-1);
        }
        return count;
    }

    //避免死循环,32位要移动32次
    public static int solution(int n){
        int t = 1;
        int count = 0;
        //每次运算选择把1左移一位，而不是把n右移一位
        while((t != 0)){
            if((n & t) == 1)
                count++;
            t = t << 1;
        }
        return count;
    }
}
