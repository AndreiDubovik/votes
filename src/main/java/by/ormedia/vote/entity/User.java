package by.ormedia.vote.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
	private long id;
	@Column (name = "email")
	private String email;
	@OneToMany(mappedBy="user", targetEntity=by.ormedia.vote.entity.VoteTicket.class)
	private Set<VoteTicket>tickets = new HashSet<>();
	@OneToMany(mappedBy="initiator", targetEntity=by.ormedia.vote.entity.Subject.class)
	private Set<Subject>subjects = new HashSet<>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Set<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
	public Set<VoteTicket> getTickets() {
		return tickets;
	}
	public void setTickets(Set<VoteTicket> tickets) {
		this.tickets = tickets;
	}
	
	

}
