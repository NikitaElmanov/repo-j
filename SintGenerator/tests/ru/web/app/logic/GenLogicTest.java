package ru.web.app.logic;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

public class GenLogicTest {

    private GenLogic logic;

    @Before
    public void init(){
        logic = new GenLogic(Arrays.asList("create", "insert"),
                             Arrays.asList("fld1", "fld2", "fld3"),
                             Arrays.asList("INT", "VARCHAR", "DECIMAL"),
                             Arrays.asList("", "255", "6,3"),
                             Arrays.asList("true", "false", "flase"),
                             "testdb", "100");
    }

    @Test
    public void generateScript() {

        System.out.println(logic.generateScript());
    }
}