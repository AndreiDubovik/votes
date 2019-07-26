package by.ormedia.vote.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import by.ormedia.vote.entity.Item;
import by.ormedia.vote.entity.Subject;
import by.ormedia.vote.util.DatabaseUtil;

@Repository
public class ItemDAOImpl implements IItemDAO{

	@Override
	public Serializable addItem(Item item) throws SQLException {
		Session session = null;
		Serializable id = null;
		Transaction tr = null;
		try{
		session = DatabaseUtil.getSessionFactory().openSession();
		tr = session.beginTransaction();
		id = session.save(item);
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
	public boolean updateItem(Item item) throws SQLException {
		Session session = null;
		Transaction tr = null;
		try{
		session = DatabaseUtil.getSessionFactory().openSession();
		tr = session.beginTransaction();
		session.update(item);
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
	public boolean deleteItem(Item item) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Item getItemById(long id) throws SQLException {
		Session session = null;
		Item item = null;
		try{
			session = DatabaseUtil.getSessionFactory().openSession();
			item = session.get(Item.class, id);
		}catch(HibernateException e){
			throw new SQLException();
		}finally{
			if(session!=null&&session.isOpen())session.close();
		}
		return item;
	}

	@Override
	public List<Item> getItemsBySubject(Subject subject) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
