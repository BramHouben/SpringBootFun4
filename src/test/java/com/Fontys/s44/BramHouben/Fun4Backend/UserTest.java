package com.Fontys.s44.BramHouben.Fun4Backend;

import com.Fontys.s44.BramHouben.Fun4Backend.dao.UserFakeDao;
import com.Fontys.s44.BramHouben.Fun4Backend.model.User;
import com.Fontys.s44.BramHouben.Fun4Backend.security.UserRoles;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class UserTest {


	@Autowired
	private UserFakeDao userFakeDao;

	@Test
	void contextLoads() {
	}

	@Test
	void addUserNull(){
		User user = new User();

		userFakeDao.insertUser(user);

		List<User> userList = userFakeDao.getAllUsers();

		Assert.assertEquals(0, userList.size());

	}


	@Test
	void addUser(){
		User user = new User(UUID.randomUUID(),"Name",21,"Male","Password", UserRoles.BASICUSER);

		userFakeDao.insertUser(user);

		List<User> userList = userFakeDao.getAllUsers();

		Assert.assertNotNull(userFakeDao.getAllUsers());

		Assert.assertEquals(1, userList.size());

	}


}
