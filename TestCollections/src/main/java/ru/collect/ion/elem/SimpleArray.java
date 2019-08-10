package ru.collect.ion.elem;

import java.util.Iterator;

public class SimpleArray<E> implements Simple<E> {

    public static void main(String[] args) {
        SimpleArray<String> strings = new SimpleArray<>();

        strings.add("1");
        strings.add("2");
        strings.add("3");

        strings.delete(1);

        for (String s : strings){
            System.out.println(s);
        }
    }

    private E[] values;

    public SimpleArray() {
        this.values = (E[]) new Object[0];
    }

    @Override
    public boolean add(E e) {

        try{
            E[] temp = values;
            values = (E[]) new Object[values.length+1];
            System.arraycopy(temp, 0, values, 0, temp.length);
            values[values.length-1] = e;

            return true;

        } catch (ClassCastException ex){
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public void delete(int index) {
        try {
            E[] temp = values;
            values = (E[]) new Object[values.length - 1];

            System.arraycopy(temp, 0, values, 0, index);

            System.arraycopy(temp, index + 1, values, index, temp.length - index - 1);
        } catch (ClassCastException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return values[index];
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public void update(int index, E e) {
        values[index] = e;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<E>(values);
    }
}
