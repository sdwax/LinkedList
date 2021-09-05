package com.list.linked;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class LinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    @Override
    public boolean add(E element) {
        Node<E> node = new Node<>(element, null);
        if (tail == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            add(element);
            return true;
        }
        if (index == 0) {
            head = new Node<>(element, head);
        } else {
            Node<E> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = new Node<>(element, current.next);
        }
        size++;
        return true;
    }

    @Override
    public boolean addAll(List<E> elements) {
        for (E element : elements) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, List<E> elements) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (elements == null) {
            throw new NullPointerException();
        }
        if (index == size) {
            addAll(elements);
            return true;
        }
        Node<E> current = head;
        Node<E> previous = null;
        if (!elements.isEmpty()) {
            for (int i = 0; i < index; i++) {
                previous = current;
                current = current.next;
            }
            boolean needFirstNode = true;
            for (E elem : elements) {
                if (index == 0 && needFirstNode) {
                    head = new Node<>(elem, null);
                    previous = head;
                    needFirstNode = false;
                } else {
                    Node<E> nextNode = new Node<>(elem, null);
                    previous.next = nextNode;
                    previous = nextNode;
                }
                size++;
            }
            if (previous != null) {
                previous.next = current;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean clear() {
        this.head = this.tail = null;
        this.size = 0;
        return true;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        E elem = head.element;
        if (index == 0) {
            head = current.next;
            size--;
            return elem;
        }
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        if (current.next == tail) {
            tail = current;
        }
        Node<E> nextNode = current.next.next;
        elem = current.next.element;
        current.next = nextNode;
        size--;
        return elem;
    }

    @Override
    public E remove(E element) {
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> current = head;
        Node<E> previous = null;
        E elem = null;
        while (current != null) {
            if (element.equals(current.element)) {
                elem = current.element;
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
            } else {
                previous = current;
            }
            current = current.next;
        }
        return elem;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index < size - 1) {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.element;
        }
        return tail.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && tail == null;
    }

    @Override
    public int indexOf(E element) {
        Iterator<E> it = iterator();
        if (it.hasNext()) {
            int index = 0;
            while (it.hasNext()) {
                E next = it.next();
                if (next.equals(element)) {
                    return index;
                }
                index++;
            }
            return index;
        } else {
            return -1;
        }
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<>(head);
    }

    @Override
    public String toString() {
        Iterator<E> it = iterator();
        if (!it.hasNext()) {
            return null;
        }

        StringBuilder str = new StringBuilder("{");
        while (it.hasNext()) {
            E element = it.next();
            str.append(", ").append(element.toString());
        }

        return str.append("}").toString();
    }

    @Override
    public List<E> filter(Predicate<E> predicate) {
        List<E> result = new LinkedList<>();
        for (E next : this) {
            if (predicate.test(next)) {
                result.add(next);
            }
        }
        return result;
    }

    @Override
    public <T> List<T> map(Function<E, T> mapper) {
        List<T> result = new LinkedList<>();
        for (E e : this) {
            result.add(mapper.apply(e));
        }
        return result;
    }

    @Override
    public Optional<E> max(Comparator<E> comparator) {
        if (size != 0) {
            Iterator<E> it = iterator();
            E maximum = it.next();
            while (it.hasNext()) {
                E next = it.next();
                if (comparator.compare(next, maximum) > 0) {
                    maximum = next;
                }
            }
            return Optional.ofNullable(maximum);
        }
        return Optional.empty();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof List) {
            List<?> otherList = (List<?>) obj;
            if (this.size() == otherList.size()) {
                Iterator<E> list1 = this.iterator();
                Iterator<?> list2 = otherList.iterator();

                while (list1.hasNext()) {
                    Object e1 = list1.next();
                    Object e2 = list2.next();
                    if (!(Objects.equals(e1, e2))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}