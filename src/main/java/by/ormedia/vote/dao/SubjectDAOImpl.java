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
	private IUserDAO userDAO;
	
	@Autowired
	private IItemDAO itemDAO;
	
	@Autowired
	private IVoteTicketDAO voteDAO;
	
	@Override
	public Serializable addSubject(Subject subject) throws SQLException {
		Session session = null;
		Serializable id = null;
		Transaction t = null;
		try{
			Set<VoteTicket>tickets = subject.getVoteTickets();
			session = DatabaseUtil.getSessionFactory().openSession();
			t = session.beginTransaction();
			User user0 = subject.getInitiator();
			try{
				User user = userDAO.getUserByEmail(user0.getEmail());
				if(user!=null){
					user0.setId(user.getId());
				}else{
					userDAO.addUser(user0);
				}
			}catch(SQLException e3){
				e3.printStackTrace();
				throw new HibernateException(e3);
			}
			id = session.save(subject);
			Set<Item>items = subject.getItems();
			try{
				for(Item i:items)itemDAO.addItem(i);
			}catch(SQLException e2){
				e2.printStackTrace();
				throw new HibernateException(e2);
			}
			
			try{
				for(VoteTicket vt:tickets){
					User voteUser = vt.getUser();
					try{
						User user = userDAO.getUserByEmail(voteUser.getEmail());
						if(user!=null){
							voteUser.setId(user.getId());
						}else{
							userDAO.addUser(voteUser);
						}
					}catch(SQLException e5){
						e5.printStackTrace();
						throw new HibernateException(e5);
					}
					voteDAO.addVoteTicket(vt);
				}
			}catch(SQLException e4){
				e4.printStackTrace();
				throw new HibernateException(e4);
			}
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
