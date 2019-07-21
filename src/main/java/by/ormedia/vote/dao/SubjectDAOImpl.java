package by.ormedia.vote.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.ormedia.vote.entity.Subject;
import by.ormedia.vote.entity.User;
import by.ormedia.vote.util.DatabaseUtil;

@Transactional
@Repository
public class SubjectDAOImpl implements ISubjectDAO{

	@Autowired
	private IUserDAO userDAO;
	
	@Override
	public Serializable addSubject(Subject subject) throws SQLException {
		Session session = null;
		Serializable id = null;
		Transaction t = null;
		try{
			session = DatabaseUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			User user0 = subject.getInitiator();
			User user = userDAO.getUserByEmail(user0.getEmail());
			if(user!=null){
				user0.setId(user.getId());
			}else{
				userDAO.addUser(user0);
			}
			id = session.save(subject);
			t.commit();
		}catch(HibernateException e){
			if(t!=null)t.rollback();
			throw new SQLException();
		}
		return id;
	}

	@Override
	public boolean updateSubject(Subject subject) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSubject(Subject subject) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Subject getSubjectById(long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subject> getSubjects() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subject> getSubjectsByStatus(boolean status) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
