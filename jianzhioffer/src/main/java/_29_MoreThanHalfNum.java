import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zetrov on 2017-01-01.
 */
public class _29_MoreThanHalfNum {

    public static void main(String[] args) {
        int[] array = {1,2,3,2,4,2,5,2,3};
        System.out.println(MoreThanHalfNum_Solution(array));
    }

    public static int MoreThanHalfNum_Solution(int [] array) {
        int len = array.length;
        if(len == 0)
            return 0;
        if(len == 1)
            return array[0];
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        int mid = len/2;
        for(int i = 0; i < len; ++i){
            Integer cont = map.get(array[i]) == null ? 1 : map.get(array[i]) + 1;
            map.put(array[i], cont);
            if(cont > mid)
                return array[i];
        }
        return 0;
    }
}
