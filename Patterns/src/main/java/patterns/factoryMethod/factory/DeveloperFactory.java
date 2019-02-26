package patterns.factoryMethod.factory;

import patterns.factoryMethod.entities.CppDeveloper;
import patterns.factoryMethod.entities.JavaDeveloper;
import patterns.factoryMethod.entities.interfaces.Developer;

public class DeveloperFactory {

    public static Developer getDeveloper(String type, int id, String name, int experience){
        if (type.equalsIgnoreCase("java")){
            return new JavaDeveloper(id, name, experience);
        }
        else if (type.equalsIgnoreCase("cpp")) {
            return new CppDeveloper(id, name, experience);
        }

        System.out.println("'" + type + "' is unknown type!");
//        throw new RuntimeException("'" + type + "' is unknown type!");
        return null;
    }

}
