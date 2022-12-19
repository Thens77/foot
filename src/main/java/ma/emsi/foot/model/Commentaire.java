package ma.emsi.foot.model;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Commentaire {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Client client;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	private Club club;

	private Date date;

	private String commentaire;

	 public Commentaire() {
		}
	
	public Commentaire(Client client, Club club, Date date, String commentaire) {
		super();
		this.client = client;
		this.club = club;
		this.date = date;
		this.commentaire = commentaire;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}
	

	@Override
	public String toString() {
		return "Commentaire [id=" + id + ", client=" + client + ", club=" + club + ", date=" + date + ", commentaire="
				+ commentaire + "]";
	}

	

}
