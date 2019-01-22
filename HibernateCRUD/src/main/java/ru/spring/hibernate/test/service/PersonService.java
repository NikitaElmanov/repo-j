package ru.spring.hibernate.test.service;

import ru.spring.hibernate.test.model.Person;

import java.util.List;

public interface PersonService {

    public void addPerson(Person p);
    public void updatePerson(Person p);
    public List<Person> listPersons();
    public Person getPersonById(int id);
    public void removePerson(int id);

}
