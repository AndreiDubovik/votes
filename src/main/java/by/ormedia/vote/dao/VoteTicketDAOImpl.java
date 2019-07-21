package by.ormedia.vote.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import by.ormedia.vote.entity.Subject;
import by.ormedia.vote.entity.VoteTicket;
import by.ormedia.vote.util.DatabaseUtil;

@Repository
public class VoteTicketDAOImpl implements IVoteTicketDAO{

	@Override
	public boolean updateVoteTicket(VoteTicket vote) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VoteTicket getVoteTicketById(long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VoteTicket> getVoteTicketBySubject(Subject subject) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable addVoteTicket(VoteTicket vote) throws SQLException {
		Session session = null;
		Serializable id = null;
		Transaction tr = null;
		try{
		session = DatabaseUtil.getSessionFactory().openSession();
		tr = session.beginTransaction();
		id = session.save(vote);
		tr.commit();
		}catch(HibernateException e){
			if(tr!=null)tr.rollback();
			throw new SQLException();
		}finally{
			if(session!=null&&session.isOpen())session.close();
		}
		return id;
	}

}
