import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<E extends Comparable<E>> implements CircularLinkedListInterface<E>{
    
    private Node<E> front = null;
    public Node<E> end = null;
    private int size;


    public CircularLinkedList(){
        front = null;
    }


    @Override
    public void add(E value) {
        // No nodes, don't worry about next
        Node<E> newNode = new Node<E>(value);

        if(front == null){
            front = newNode;
            newNode.next = front;
            end = newNode;
            size++;

        } else {
            Node<E> current = front;

            while(current.next != front){
                current = current.next;
            }
            current.next = newNode;
            newNode.next = front;
            end = newNode;
            size++; 
     
        }
    }

    @Override
    public E get(int position) {
        int count = 0;
        Node<E> current = front;
        E data = null;

        while(current.next != null){
            if(count == position){
                data = current.data;
            }
            count++;
            current = current.next;
        }

        return data;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularLinkedListIterator();
    }

    @Override
    public boolean remove(E value) {
        Node<E> current = front;
        Node<E> previous = front;

        if(value == front.data){
            front = null;
            size--;
            return true;
        } else {
            while(current.next != front){
                if(current.data == value){
                    previous.next = current.next;
                    end = previous.next;
                    size--;
                    return true;
                }
                previous = current;
                current = current.next;
            }
        } if(current.data == value){
            end = previous;
            previous.next = front;
            size--;
            return false;
        }
        size--;
        return false;
    }

    @Override
    public void remove(int position) {
        int counter = 0; 
        Node<E> current = front;
        Node<E> previous = front;

        if(position == 0){
            front = null;
            size--;
        } else {
            while(current.next != front){
                if(position == counter){
                    previous.next = current.next;
                    size--;
                }
                previous = current;
                current = current.next;
                counter++;
            }      
        } 
        if(position == size){
            end = previous;
            previous.next = front;
            size--;
        }
    }


    private class Node<T>{
        public T data;
        public Node<T> next;

        public Node(T data){
            this.data = data;
            this.next = null;
        }

    }

    private class CircularLinkedListIterator implements Iterator<E>{
        
        Node<E> current = front;
        Node<E> prev = front;
        public CircularLinkedListIterator(){

        }

        @Override
        public boolean hasNext() {
            if(front == null){
                return false;
            } 
            return true;
        }

        @Override
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            while(hasNext()){
                prev = current;
                current = current.next;
                return prev.data;
            }
            return prev.data;
        }
    }
}