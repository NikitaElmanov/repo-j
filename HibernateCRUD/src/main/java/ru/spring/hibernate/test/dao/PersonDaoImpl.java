package ru.spring.hibernate.test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.spring.hibernate.test.model.Person;

import java.util.List;
import org.slf4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class PersonDaoImpl implements PersonDAO{

    private static final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

    @PersistenceContext(unitName = "HibPU")
    private EntityManager em;

    @Override
    public void addPerson(Person person) {
        em.persist(person);
        logger.info("Person saved successfully, Person Details="+person);

    }

    @Override
    public void removePerson(int id) {

        Person person = em.find(Person.class, id);

        if (person != null){
            em.remove(person);
        }
        logger.info("Person deleted successfully, person details="+person);
    }

    @Override
    public Person getPersonById(int id) {

        Person person = em.find(Person.class, id);

        logger.info("Person loaded successfully, Person details="+person);

        return person;
    }

    @Override
    public List<Person> listPersons() {

        List<Person> personList = em.createQuery("SELECT p FROM Person p").getResultList();

        for (Person p : personList){
            logger.info("Person List::"+p);
        }

        return personList;
    }

    @Override
    public void updatePerson(Person person) {

        em.merge(person);

        logger.info("Person updated successfully, Person Details= "+person);
    }
}
