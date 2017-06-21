import datastruct.LinkedList;

/**
 * Created by zetrov on 2016/12/4.
 */
public class _5_PrintListReversingly {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        int[] nums2 = null;
        LinkedList list = LinkedList.create(nums2);
        LinkedList.print(list);
        solution(list);
    }

    public static void solution(LinkedList head){
        if(head != null) {
            if (head.next != null)
                solution(head.next);
            System.out.print(head.val + " ");
        }
    }
}
