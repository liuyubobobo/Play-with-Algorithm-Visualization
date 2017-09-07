import java.util.LinkedList;

public class RandomQueue<E>{

    private LinkedList<E> queue;

    public RandomQueue(){
        queue = new LinkedList<E>();
    }

    public void add(E e){
        if(Math.random() < 0.5)
            queue.addFirst(e);
        else
            queue.addLast(e);
    }

    public E remove(){
        if(queue.size() == 0)
            throw new IllegalArgumentException("There's no element to remove in Random Qeuue");


        if(Math.random() < 0.5)
            return queue.removeFirst();
        else
            return queue.removeLast();
    }

    public int size(){
        return queue.size();
    }

    public boolean empty(){
        return size() == 0;
    }
}