package by.ormedia.vote.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "subjects")
public class Subject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	@Column(name = "subject")
	private String subject;
	@ManyToOne(cascade= {CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @JoinColumn(name="initiator")
	private User initiator;
	@JoinColumn(name = "isopen")
	private boolean isOpen;
	@OneToMany(mappedBy="subject", targetEntity=by.ormedia.vote.entity.Item.class)
	private Set<Item>items = new HashSet<>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public User getInitiator() {
		return initiator;
	}
	public void setInitiator(User initiator) {
		this.initiator = initiator;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public Set<Item> getItems() {
		return items;
	}
	public void setItems(Set<Item> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Subject [id=" + id + ", subject=" + subject + ", initiator=" + initiator + ", isOpen=" + isOpen
				+ ", items=" + items + "]";
	}

	
}
