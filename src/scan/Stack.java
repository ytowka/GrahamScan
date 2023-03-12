package scan;

import java.util.Collection;
import java.util.Iterator;

public class Stack<T> implements Iterable<T>{
    Node<T> head = null;

    public void push(T value){
        head = new Node<T>(value, head);
    }

    public T peak(){
        if (head.value == null){
            return null;
        }
        return head.value;
    }

    public T peak2(){
        return head.next.value;
    }

    public T pop(){
        if(head != null){
            T value = head.value;
            head = head.next;
            return value;
        }
        return null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }
        };
    }

    private record Node<T>(T value, Node<T> next){}
}
