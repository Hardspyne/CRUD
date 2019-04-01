package com.dao;

import com.model.User;
import com.util.DbUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class UserDaoTest {
    private UserDao userDao;
    private String randomFirstName;
    private String randomLastName;
    private int newId;

    @Before
    public void initTest() {
        userDao = new UserDao(DbUtil.getDbConnection());
        randomFirstName = UUID.randomUUID().toString();
        randomLastName = UUID.randomUUID().toString();
    }

    @After
    public void deleteIfTestCrash() {
        userDao.deleteUser(newId);
    }


    @Test
    public void crudTest() {
        newId = userDao.addUser(new User(randomFirstName, randomLastName, new Date(), null));

        assertNotEquals(0, newId);
        assertNotNull(userDao.getUserById(newId));

        userDao.updateUser(new User(newId, randomFirstName, randomLastName, new Date(), "555@mail.ru"));
        assertEquals(userDao.getUserById(newId).getEmail(), "555@mail.ru");

        User user = userDao.getUserById(newId);
        assertNotNull(user);

        userDao.deleteUser(newId);
        assertNull(userDao.getUserById(newId));
    }


}