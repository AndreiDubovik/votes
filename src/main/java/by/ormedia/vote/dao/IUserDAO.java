package by.ormedia.vote.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import by.ormedia.vote.entity.User;

public interface IUserDAO {
	
	public Serializable addUser(User user) throws SQLException;
	public boolean updateUser(User user) throws SQLException;
	public boolean deleteUser(User user) throws SQLException;
	public User getUserById(long id) throws SQLException;
	public List<User> getUsers() throws SQLException;

}
