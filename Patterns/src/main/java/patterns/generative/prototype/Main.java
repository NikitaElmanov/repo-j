package patterns.generative.prototype;

import patterns.generative.prototype.classes.Person;

public class Main {

    public static void main(String[] args) {
        Person p1 = new Person(1, "Tom");
        Person p2 = (Person) p1.clone();
        Person p3 = p1;
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p2.equals(p1));
        System.out.println(p2 == p1);
        System.out.println(p3 == p1);
    }

}
