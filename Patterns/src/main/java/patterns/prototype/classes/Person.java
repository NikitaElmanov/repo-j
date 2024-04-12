package patterns.prototype.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import patterns.prototype.interfaces.Copiable;

import static lombok.AccessLevel.PRIVATE;

/**
 * class Person implements Copyable and it is just test of prototype pattern
 *
 * @author Nikita Elmanov
 */

@Data
@AllArgsConstructor()
@NoArgsConstructor(access = PRIVATE)
public class Person implements Copiable {

    private int id;
    private String name;

    @Override
    public Object clone() {
        final var person = new Person();

        person.id = this.id;
        person.name = this.name;

        return person;
    }
}
