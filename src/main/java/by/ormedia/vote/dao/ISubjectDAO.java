package by.ormedia.vote.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import by.ormedia.vote.entity.Subject;

public interface ISubjectDAO {

	public Serializable addSubject(Subject subject) throws SQLException;
	public boolean updateSubject(Subject subject) throws SQLException;
	public boolean deleteSubject(Subject subject) throws SQLException;
	public Subject getSubjectById(long id) throws SQLException;
	public List<Subject> getSubjects() throws SQLException;
	public List<Subject> getSubjectsByStatus(boolean status) throws SQLException;
	
}
