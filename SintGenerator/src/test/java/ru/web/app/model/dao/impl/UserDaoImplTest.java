package ru.web.app.model.dao.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.web.app.model.dao.UserDao;
import ru.web.app.model.dao.exception.DAOException;
import ru.web.app.model.pojo.User;

public class UserDaoImplTest {

    private UserDao dao;

    @Before
    public void init() {
        dao = UserDaoImpl.getInstance();
    }

    @Test
    public void checkGettingAllUsers(){
        try {
            Assert.assertNotNull(UserDaoImpl.getInstance().getAllUsers());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = DAOException.class)
    public void createUser() throws DAOException {
        dao.createUser(new User());
    }
}