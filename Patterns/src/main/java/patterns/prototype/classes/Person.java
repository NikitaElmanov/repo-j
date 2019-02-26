package patterns.prototype.classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import patterns.prototype.interfaces.Copyable;

import java.util.ArrayList;
import java.util.List;

/**
 * class Person implements Copyable and it is just test of prototype pattern
 *
 * @author Nikita Elmanov
 */

@Getter
@Setter
@AllArgsConstructor
public class Person implements Copyable {

    private int id;
    private String name;
    private List<String> listOfFlats = new ArrayList<String>();

    private Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getListOfFlats() {
        return listOfFlats;
    }

    public void setListOfFlats(List<String> listOfFlats) {
        this.listOfFlats = listOfFlats;
    }

    public Object clone() {
        Person person = new Person();

        person.setId(this.getId());
        person.setName(this.getName());
        person.setListOfFlats(this.getListOfFlats());

        return person;
    }

//    @Override
//    public String toString() {
//        return "Person{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", listOfFlats=" + listOfFlats +
//                '}';
//    }
}
