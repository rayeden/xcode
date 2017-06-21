import datastruct.RandomListNode;

/**
 * Created by Zetrov on 2016-12-29.
 * 复制复杂链表
 */

public class _26_Clone {

    public static void main(String[] args) {

        RandomListNode pHead = new RandomListNode(1);
    }
    public RandomListNode Clone(RandomListNode pHead){
        if(pHead == null){
            return null;
        }
        RandomListNode tmp = pHead;
        while(tmp != null){
            RandomListNode node = new RandomListNode(tmp.label);
            node.next = tmp.next;
            tmp.next = node;
            tmp = node.next;
        }
        tmp = pHead;
        while(tmp != null){
            if(tmp.random != null){
                tmp.next.random = tmp.random.next;
            }
            tmp = tmp.next.next;
        }
        tmp = pHead.next;
        RandomListNode newHead = tmp;
        while(tmp.next != null){
            tmp.next = tmp.next.next;
            tmp = tmp.next;
        }
        return newHead;
    }
}
