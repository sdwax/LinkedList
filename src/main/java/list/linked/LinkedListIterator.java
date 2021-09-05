package list.linked;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedListIterator<E> implements Iterator<E> {

    private Node<E> head;

    LinkedListIterator(Node<E> head) {
        this.head = head;
    }

    @Override
    public boolean hasNext() {
        return head != null;
    }

    @Override
    public E next() {
        if (!hasNext()) throw new NoSuchElementException();
        E tmp = head.element;
        head = head.next;
        return tmp;
    }
}