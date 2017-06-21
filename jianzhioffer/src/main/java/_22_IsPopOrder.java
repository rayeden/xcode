import java.util.Stack;

/**
 * Created by Zetrov on 2016-12-27.
 */
public class _22_IsPopOrder {

    public static void main(String[] args) {
        int[] pushA = {1,2,3,4,5};
        int[] popA = {4,5,3,2,1};
        System.out.println(isPopOrder(pushA,popA));
    }

    public static boolean isPopOrder(int [] pushA,int [] popA) {
        if(pushA.length != popA.length)
            return false;
        Stack<Integer> stack = new Stack<Integer>();
        int len = pushA.length;
        int push_index = 0;
        int pop_index = 0;
        stack.push(pushA[push_index++]);
        while(push_index <= len && pop_index < len){
            if(stack.peek() == popA[pop_index]){
                stack.pop();
                pop_index++;
            }else{
                do{
                    if(push_index < len)
                        stack.push(pushA[push_index]);
                    push_index++;
                }while (stack.peek() != popA[pop_index] && push_index < len);
            }
        }
        if(stack.empty())
            return true;
        else
            return false;
    }
}
