package ru.web.app.model.service;

import org.junit.Assert;
import org.junit.Test;
import ru.web.app.model.pojo.User;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private static UserService us;

    @Test
    public void getAllUsers() {
        us = mock(UserService.class);

        when(us.getAllUsers()).thenReturn(Arrays.asList(new User(), new User()));

        for (User user : us.getAllUsers()) {
            Assert.assertNotNull(user);
        }
    }
}