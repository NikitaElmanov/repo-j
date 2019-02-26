package patterns.factoryMethod;

import patterns.factoryMethod.entities.interfaces.Developer;
import patterns.factoryMethod.factory.DeveloperFactory;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Developer developerJ = DeveloperFactory.getDeveloper("java", 3, "Tom", 12);
        Developer developerC = DeveloperFactory.getDeveloper("cpp", 2, "Ann", 2);
        Developer developerWell = DeveloperFactory.getDeveloper("csd", 45, "ggg", 102);

        List<Developer> list = new ArrayList<>();
        list.add(developerJ);
        list.add(developerC);
        list.add(developerWell);

        System.out.println(list);
    }
}
