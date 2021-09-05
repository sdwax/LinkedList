package com.project.list.linked;

class Node<E> {

    final E element;
    Node<E> next;

    Node(E element, Node<E> next) {
        this.element = element;
        this.next = next;
    }
}