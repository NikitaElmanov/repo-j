package patterns.generative.factoryMethod.entities;

import lombok.AllArgsConstructor;
import patterns.generative.factoryMethod.entities.interfaces.Developer;

@AllArgsConstructor
public class CppDeveloper implements Developer {

    private int id;
    private String name;
    private int experience;

    public void writeCode() {
        System.out.println("C++ developer wrote c++ code");
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
