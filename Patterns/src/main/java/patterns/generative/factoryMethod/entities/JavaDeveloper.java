package patterns.generative.factoryMethod.entities;

import lombok.AllArgsConstructor;
import patterns.generative.factoryMethod.entities.interfaces.Developer;

@AllArgsConstructor
public class JavaDeveloper implements Developer {

    private int id;
    private String name;
    private int experience;

    @Override
    public void writeCode() {
        System.out.println("Java developer wrote java code");
    }

    @Override
    public String toString() {
        return "JavaDeveloper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                '}';
    }
}
