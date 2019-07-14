package by.ormedia.vote.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.ormedia.vote.entity.User;
import by.ormedia.vote.util.DatabaseUtil;

@Transactional
@Repository
public class UserDAOImpl implements IUserDAO{

	@Override
	public Serializable addUser(User user) throws SQLException {
		Session session = null;
		Serializable id = null;
		try{
		session = DatabaseUtil.getSessionFactory().openSession();
		session.beginTransaction();
		id = session.save(user);
		session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null&&session.isOpen())session.close();
		}
		return id;
	}

	@Override
	public boolean updateUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserById(long id) throws SQLException {
		Session session = null;
		User user = null;
		try{
			session = DatabaseUtil.getSessionFactory().openSession();
			user = session.get(User.class, id);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null&&session.isOpen())session.close();
		}
		return user;
	}

	@Override
	public List<User> getUsers() throws SQLException {
		Session session = null;
		List<User>list = null;
		try{
			session = DatabaseUtil.getSessionFactory().openSession();
			list = session.createQuery("from User").getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null&&session.isOpen())session.close();
		}
		return list!=null?list:new ArrayList<>();
	}

}
