package ru.spring.hibernate.test.dao;

import ru.spring.hibernate.test.model.Person;

import java.util.List;

public interface PersonDAO {

    void addPerson(Person person);
    void removePerson(int id);
    Person getPersonById(int id);
    List<Person> listPersons();
    void updatePerson(Person person);
}
