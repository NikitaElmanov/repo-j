package test;

import patterns.prototype.classes.Person;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Person person = new Person(1, "Tom", Arrays.asList("12", "23", "34", "67"));

        Person person2 = (Person)person.clone();
        person2.setName("Ann");

        person2.setListOfFlats(Arrays.asList("11", "22", "33", "66"));

        System.out.println(person.getListOfFlats());
        System.out.println(person2.getListOfFlats());

    }

}
