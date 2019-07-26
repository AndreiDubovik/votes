package by.ormedia.vote.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.ormedia.vote.dao.IVoteTicketDAO;
import by.ormedia.vote.entity.VoteTicket;

@Service
public class VoteTicketService implements IVoteTicketService{

	@Autowired
	private IVoteTicketDAO ticketDAO;
	
	@Override
	public boolean vote(long voteId, String key) {
		try {
			VoteTicket vt = ticketDAO.getVoteTicketById(voteId);
			if(vt==null)return false;
			if(!vt.getKeycode().equals(key))return false;
			vt.setVoted(true);
			this.ticketDAO.updateVoteTicket(vt);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

}
