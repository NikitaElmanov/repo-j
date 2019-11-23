package ru.web.app.util;

import org.junit.Assert;
import org.junit.Test;

public class DBFactoryTest {

    @Test
    public void checkGetConnection() throws Exception {
        Assert.assertNotNull(DBFactory.getConnection());
    }
}