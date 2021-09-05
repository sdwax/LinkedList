package list;

import list.linked.LinkedList;

public class LinkedListApp {
    public static void main(String[] args) {

        LinkedList<Integer> integers = new LinkedList<>();
        integers.add(2);
        integers.add(4);
        integers.add(8);
        integers.reverse();
    }
}
