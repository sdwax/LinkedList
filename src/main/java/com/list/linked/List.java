package com.list.linked;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public interface List<E> extends Iterable<E> {

    boolean add(E element);

    boolean add(int index, E element);

    boolean addAll(List<E> elements);

    boolean addAll(int index, List<E> elements);

    boolean clear();

    E remove(int index);

    E remove(E element);

    E get(int index);

    int size();

    boolean isEmpty();

    int indexOf(E element);

    boolean contains(E element);

    List<E> filter(Predicate<E> predicate);

    <T> List<T> map(Function<E, T> mapper);

    Optional<E> max(Comparator<E> comparator);

    default Optional<E> min(Comparator<E> comparator) {
        return max(comparator.reversed());
    }

    boolean equals(Object o);
}