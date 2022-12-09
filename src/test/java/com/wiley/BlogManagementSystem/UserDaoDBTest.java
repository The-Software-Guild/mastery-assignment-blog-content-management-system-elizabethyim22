package com.wiley.BlogManagementSystem;


import com.wiley.BlogManagementSystem.dao.UserDao;
import com.wiley.BlogManagementSystem.model.User;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class UserDaoDBTest extends  TestCase{

    @Autowired
    UserDao userDao;

    public UserDaoDBTest(){}

    @Before
    public void setUp(){
        List<User> users = userDao.getAllUsers();
        for(User user : users){
            userDao.deleteUserById(user.getUser_id());
        }
    }

    @Test
    public void testUserAndGetUser(){
        //create a new User
        User user = new User();
        user.setPassword("ThisIsThePassWord");
        user.setUsername("UserName");
        user.setRole("Student");
        userDao.addUser(user);
        User fromDao = userDao.getUserById(user.getUser_id());

        assertEquals(user.getUser_id(), fromDao.getUser_id());
    }

    @Test
    public void testGetAllUsers(){
        //create 2 users
        User user = new User();
        user.setPassword("ThisIsThePassWord");
        user.setUsername("UserName");
        user.setRole("Student");
        userDao.addUser(user);

        User user1 = new User();
        user1.setPassword("PassWord");
        user1.setUsername("User");
        user1.setRole("Manager");
        userDao.addUser(user1);

        List<User> users = userDao.getAllUsers();

        assertEquals(2, users.size());
        assertTrue(users.contains(user));
        assertTrue(users.contains(user1));
    }

    @Test
    public void testUpdateUser(){
        //create a new user
        User user = new User();
        user.setPassword("ThisIsThePassWord");
        user.setUsername("UserName");
        user.setRole("Student");
        userDao.addUser(user);

        //update one part
        user.setUsername("nameUser");
        userDao.updateUser(user);

        //get the new updated version
        User updated = userDao.getUserById(user.getUser_id());

        //assert that the user got updated
        assertEquals(user, updated);
    }

    @Test
    public void testDeleteUserById(){
        //create a new user
        User user = new User();
        user.setPassword("ThisIsThePassWord");
        user.setUsername("UserName");
        user.setRole("Student");
        userDao.addUser(user);

        // get the user
        User fromDao = userDao.getUserById(user.getUser_id());

        //assert that the user we created is equal to the new one
        assertEquals(user.getUser_id(), fromDao.getUser_id());

        //delete the user
        userDao.deleteUserById(user.getUser_id());

        //get the new ID and assert that it is null
        fromDao = userDao.getUserById(user.getUser_id());
        assertNull(fromDao);

    }
}
