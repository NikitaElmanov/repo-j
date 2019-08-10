package ru.collect.ion.elem;

import java.util.Iterator;

public class ArrayIterator<E> implements Iterator<E> {

    private E[] values;
    private int index = 0;

    public ArrayIterator(E[] values) {
        this.values = values;
    }

    public boolean hasNext() {
        return index < values.length;
    }

    public E next() {
        return values[index++];
    }
}
