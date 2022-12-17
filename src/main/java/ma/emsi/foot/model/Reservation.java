package ma.emsi.foot.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long nbrjoueur;
	@ManyToOne
	private Client reservedBy;
	@ManyToOne
	private Terrain terrain;
	@ManyToOne
	private Creneau creneau;
	private Date date;
	private boolean etat;
	
	

	public Reservation(Long nbrjoueur, Client reservedBy, Terrain terrain, Creneau creneau, Date date, boolean etat) {
		super();
		this.nbrjoueur = nbrjoueur;
		this.reservedBy = reservedBy;
		this.terrain = terrain;
		this.creneau = creneau;
		this.date = date;
		this.etat = etat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNbrjoueur() {
		return nbrjoueur;
	}

	public void setNbrjoueur(Long nbrjoueur) {
		this.nbrjoueur = nbrjoueur;
	}

	public Client getReservedBy() {
		return reservedBy;
	}

	public void setReservedBy(Client reservedBy) {
		this.reservedBy = reservedBy;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public Creneau getCreneau() {
		return creneau;
	}

	public void setCreneau(Creneau creneau) {
		this.creneau = creneau;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", nbrjoueur=" + nbrjoueur + ", reservedBy=" + reservedBy + ", terrain="
				+ terrain + ", creneau=" + creneau + ", date=" + date + ", etat=" + etat + "]";
	}
	
	

}
