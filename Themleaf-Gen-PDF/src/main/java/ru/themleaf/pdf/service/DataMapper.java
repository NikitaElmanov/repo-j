package ru.themleaf.pdf.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import ru.themleaf.pdf.model.Person;

@Service
public class DataMapper {

    public Context setData(List<Person> persons) {

        var context = new Context();

        Map<String, Object> personMap =
                Map.of("persons", persons);

        context.setVariables(personMap);

        return context;
    }

}
