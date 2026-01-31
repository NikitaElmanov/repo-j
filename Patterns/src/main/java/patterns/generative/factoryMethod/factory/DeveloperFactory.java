package patterns.generative.factoryMethod.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import patterns.generative.factoryMethod.entities.CppDeveloper;
import patterns.generative.factoryMethod.entities.JavaDeveloper;
import patterns.generative.factoryMethod.entities.interfaces.Developer;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DeveloperFactory {

    public static Developer getDeveloper(String type, int id, String name, int experience) {
        if (type.equalsIgnoreCase("java")) {
            return new JavaDeveloper(id, name, experience);
        } else if (type.equalsIgnoreCase("cpp")) {
            return new CppDeveloper(id, name, experience);
        }

        System.out.println("'" + type + "' is unknown type!");
        return null;
    }

}
