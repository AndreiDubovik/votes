package by.ormedia.vote.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
		Transaction tr = null;
		try{
		session = DatabaseUtil.getSessionFactory().openSession();
		tr = session.beginTransaction();
		id = session.save(user);
		tr.commit();
		}catch(HibernateException e){
			if(tr!=null)tr.rollback();
			throw new SQLException();
		}finally{
			if(session!=null&&session.isOpen())session.close();
		}
		return id;
	}

	@Override
	public boolean updateUser(User user) throws SQLException {
		Session session = null;
		Transaction tr = null;
		try{
		session = DatabaseUtil.getSessionFactory().openSession();
		tr = session.beginTransaction();
		session.update(user);
		tr.commit();
		}catch(HibernateException e){
			if(tr!=null)tr.rollback();
			throw new SQLException();
		}finally{
			if(session!=null&&session.isOpen())session.close();
		}
		return true;
	}

	@Override
	public boolean deleteUser(User user) throws SQLException {
		Session session = null;
		Transaction tr = null;
		try{
		session = DatabaseUtil.getSessionFactory().openSession();
		tr = session.beginTransaction();
		session.delete(user);
		tr.commit();
		}catch(HibernateException e){
			if(tr!=null)tr.rollback();
			throw new SQLException();
		}finally{
			if(session!=null&&session.isOpen())session.close();
		}
		return true;
	}

	@Override
	public User getUserById(long id) throws SQLException {
		Session session = null;
		User user = null;
		try{
			session = DatabaseUtil.getSessionFactory().openSession();
			user = session.get(User.class, id);
		}catch(HibernateException e){
			throw new SQLException();
		}finally{
			if(session!=null&&session.isOpen())session.close();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() throws SQLException {
		Session session = null;
		List<User>list = null;
		try{
			session = DatabaseUtil.getSessionFactory().openSession();
			list = session.createQuery("from User").getResultList();
		}catch(Exception e){
			throw new SQLException();
		}finally{
			if(session!=null&&session.isOpen())session.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByEmail(String email) throws SQLException {
		Session session = null;
		List<User> users = null;
		try{
			session = DatabaseUtil.getSessionFactory().openSession();
			users = session.createQuery("from User where email=:em").setParameter("em", email).getResultList();
		}catch(Exception e){
			throw new SQLException();
		}finally{
			if(session!=null&&session.isOpen())session.close();
		}
		return users!=null&&users.size()!=0?users.get(0):null;
	}

}
