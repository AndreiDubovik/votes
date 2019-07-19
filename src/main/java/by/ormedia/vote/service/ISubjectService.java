package by.ormedia.vote.service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import by.ormedia.vote.entity.Subject;

public interface ISubjectService {
	
	public Serializable addSubject(Subject subject);
	public boolean updateSubject(Subject subject);
	public boolean deleteSubject(Subject subject);
	public Subject getSubjectById(long id);
	public List<Subject> getSubjects();
	public List<Subject> getSubjectsByStatus(boolean status);

}
