package ma.emsi.foot.model;

import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Creneau {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private Time heureDebut ;
	private Time heureFin ;
	@ManyToOne
	private Club club;
	
	 public Creneau() {
		}
	
	public Creneau(Time heureDebut, Time heureFin , Club club) {
		super();
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
		this.club = club ;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Time getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Time heureDebut) {
		this.heureDebut = heureDebut;
	}
	public Time getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(Time heureFin) {
		this.heureFin = heureFin;
	}
	
	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	@Override
	public String toString() {
		return "Creneau [id=" + id + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + ", club=" + club + "]";
	}

	
	
	
	

}
