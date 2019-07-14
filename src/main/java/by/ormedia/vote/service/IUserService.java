package by.ormedia.vote.service;

import java.io.Serializable;
import java.util.List;

import by.ormedia.vote.entity.User;

public interface IUserService {
	
	public Serializable addUser(User user);
	public boolean updateUser(User user);
	public boolean deleteUser(User user);
	public User getUserById(long id);
	public List<User> getUsers();
}
