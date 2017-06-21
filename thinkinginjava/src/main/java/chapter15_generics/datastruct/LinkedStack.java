package chapter15_generics.datastruct;

/**
 * Created by xhtc on 2017/5/4.
 */

/**
 * 泛型实现下推堆栈
 * @param <T>
 */
public class LinkedStack<T> {

    private class Node<U>{
        U item;
        Node<U> next;

        Node(){
            this.item = null;
            this.next = null;
        }

        Node(U item, Node<U> next){
            this.item = item;
            this.next = next;
        }

        boolean end(){
            return item == null && next == null;
        }
    }

    private Node<T> top = new Node<T>();

    public void push(T item){
        top = new Node<T>(item, top);
    }

    public T pop(){
        T result = top.item;
        if(!top.end()){
            top = top.next;
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedStack<String> ls = new LinkedStack<String>();
        for(String s : "Phasers on stun!".split(" "))
            ls.push(s);

        String s;
        while ((s = ls.pop()) != null){
            System.out.println(s);
        }
    }

}
