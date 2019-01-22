package ru.spring.hibernate.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spring.hibernate.test.dao.PersonDAO;
import ru.spring.hibernate.test.model.Person;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAO personDAO;

    @Override
    @Transactional
    public void addPerson(Person p) {
        personDAO.addPerson(p);
    }

    @Override
    @Transactional
    public void updatePerson(Person p) {
        personDAO.updatePerson(p);
    }

    @Override
    @Transactional
    public List<Person> listPersons() {
        return personDAO.listPersons();
    }

    @Override
    @Transactional
    public Person getPersonById(int id) {
        return personDAO.getPersonById(id);
    }

    @Override
    @Transactional
    public void removePerson(int id) {
        personDAO.removePerson(id);
    }
}
