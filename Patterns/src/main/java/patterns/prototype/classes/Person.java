package patterns.prototype.classes;

import lombok.*;
import patterns.prototype.interfaces.Copyable;

/**
 * class Person implements Copyable and it is just test of prototype pattern
 *
 * @author Nikita Elmanov
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Copyable {

    private int id;
    private String name;

    public Object clone() {
        Person person = new Person();

        person.id = this.id;
        person.name = this.name;

        return person;
    }

    public static void main(String[] args) {
        Person p1 = new Person(1, "Tom");
        Person p2 = (Person) p1.clone();
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p2.equals(p1));
    }
}
