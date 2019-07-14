package by.ormedia.vote.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.ormedia.vote.dao.IUserDAO;
import by.ormedia.vote.entity.User;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private IUserDAO dao;

	@Override
	public Serializable addUser(User user) {
		try {
			return this.dao.addUser(user);
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsers() {
		List<User>list = null;
		try {
			list = this.dao.getUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list!=null?list:new ArrayList<>();
	}

}
