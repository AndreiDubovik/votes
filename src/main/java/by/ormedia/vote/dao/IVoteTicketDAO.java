package by.ormedia.vote.dao;

import java.sql.SQLException;
import java.util.List;
import by.ormedia.vote.entity.Subject;
import by.ormedia.vote.entity.VoteTicket;

public interface IVoteTicketDAO {
	public boolean updateVoteTicket(VoteTicket vote) throws SQLException;
	public VoteTicket getVoteTicketById(long id) throws SQLException;
	public List<VoteTicket> getVoteTicketBySubject(Subject subject) throws SQLException;
}
