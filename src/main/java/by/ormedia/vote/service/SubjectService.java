package by.ormedia.vote.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.ormedia.vote.dao.ISubjectDAO;
import by.ormedia.vote.dao.IUserDAO;
import by.ormedia.vote.dao.IVoteTicketDAO;
import by.ormedia.vote.entity.Subject;

@Service
public class SubjectService implements ISubjectService{

	@Autowired
	private ISubjectDAO dao;
	
	
	@Override
	public Serializable addSubject(Subject subject) {
		try {
			return this.dao.addSubject(subject);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateSubject(Subject subject) {
		try {
			return this.dao.updateSubject(subject);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteSubject(Subject subject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Subject getSubjectById(long id) {
		Subject subject = null;
		try {
			subject = this.dao.getSubjectById(id);
		} catch (SQLException e){ 
			e.printStackTrace();
		}
		return subject;
	}

	@Override
	public List<Subject> getSubjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Subject> getSubjectsByStatus(boolean status) {
		// TODO Auto-generated method stub
		return null;
	}

}
