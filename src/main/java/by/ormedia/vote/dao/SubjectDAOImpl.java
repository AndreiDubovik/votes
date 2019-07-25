package by.ormedia.vote.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import by.ormedia.vote.entity.Item;
import by.ormedia.vote.entity.Subject;
import by.ormedia.vote.entity.User;
import by.ormedia.vote.entity.VoteTicket;
import by.ormedia.vote.util.DatabaseUtil;

@Transactional
@Repository
public class SubjectDAOImpl implements ISubjectDAO{
	
	@Autowired
	private IUserDAO userDao;
	
	@Override
	public Serializable addSubject(Subject subject) throws SQLException {
		Session session = null;
		Serializable id = null;
		Transaction tr = null;
		User user0 = userDao.getUserByEmail(subject.getInitiator().getEmail());
		if(user0!=null)subject.setInitiator(user0);
		Set<VoteTicket>tickets0 = subject.getVoteTickets();
		for(VoteTicket vt:tickets0){
			User us = userDao.getUserByEmail(vt.getUser().getEmail());
			if(us!=null)vt.setUser(us);
		}
		try{
			session = DatabaseUtil.getSessionFactory().getCurrentSession();
			tr = session.beginTransaction();
			User creator = subject.getInitiator();
			if(creator.getId()==0)session.save(creator);
			id = session.save(subject);
			Set<Item>items = subject.getItems();
			for(Item i:items)session.save(i);
			Set<VoteTicket>tickets = subject.getVoteTickets();
			for(VoteTicket vt:tickets){
				User user = vt.getUser();
				if(user.getId()==0)session.save(user);
				session.save(vt);
			}
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
