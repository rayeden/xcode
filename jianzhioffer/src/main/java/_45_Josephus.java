/**
 * Created by zetrov on 2016/12/4.
 *
 *  0  1  2  3  ... M-1  M  M+1  ...  N-2  N-1
 *  第一次去掉第M个人，即下标为M-1，第二次报数时从第M个人开始，可以重新分配下标
 *
 *  M M+1 ... N-2  N-1  0  1  2 ...  M-2
 *  0  1  ...                        N-2
 *
 *  所以下一个出队的人的下标是 (2M-1)%N
 *
 *  以此类推得到，f(n) = (f(n-1)+m)%n
 *  其中n=1时，出队人的下标为0
 *
 */

public class _45_Josephus {

    public static void main(String[] args) {
        int n = 3;
        int m = 2;

        System.out.println(lastRemaining(n, m));
    }

    public static int lastRemaining(int n, int m){
        if(n == 1)
            return 0;
        else
            return (lastRemaining(n-1, m) + m) % n;
    }
}
