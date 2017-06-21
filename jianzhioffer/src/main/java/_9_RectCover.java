/**
 * Created by Zetrov on 2016-12-26.
 * n个2*1的方块在2*n的空间里可以有几种摆放方式
 */

public class _9_RectCover {

    public static void main(String[] args) {
        System.out.println(rectCover(3));
    }

    public static int rectCover(int target) {
        if(target == 0 || target == 1 || target == 2)
            return target;
        int res[] = new int[target+1];
        res[0] = 0;
        res[1] = 1; res[2] = 2;
        for(int i = 3; i <= target; ++i){
            res[i] = res[i-1] + res[i-2];
        }
        return res[target];
    }
}
