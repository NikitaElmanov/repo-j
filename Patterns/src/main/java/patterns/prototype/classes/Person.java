package patterns.prototype.classes;

import jdk.nashorn.internal.objects.annotations.Constructor;
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
@NoArgsConstructor
public class Person implements Copyable {

    private int id;
    private String name;
    private List<String> listOfFlats = new ArrayList<String>();

    public Object clone() {
        Person person = new Person();

        person.setId(this.getId());
        person.setName(this.getName());

        List<String> temp = new ArrayList<>();

        for(String str : listOfFlats){
            temp.add(str);
        }

        person.setListOfFlats(temp);

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
