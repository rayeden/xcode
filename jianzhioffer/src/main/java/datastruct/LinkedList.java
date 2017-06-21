package datastruct;

/**
 * Created by zetrov on 2016/12/4.
 */
public class LinkedList {

    public int val;
    public LinkedList next;

    public LinkedList(int val){
        this.val = val;
        this.next = null;
    }

    public static LinkedList create(int[] vals){
        if(vals == null)
            return null;
        LinkedList head = new LinkedList(-1);
        LinkedList tmp = head;
        int len = vals.length;
        for (int i = 0; i < len; i++) {
            LinkedList node = new LinkedList(vals[i]);
            tmp.next = node;
            tmp = node;
        }
        return head.next;
    }

    public static void print(LinkedList head){
        while(head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
