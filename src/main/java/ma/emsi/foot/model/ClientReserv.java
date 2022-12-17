package ma.emsi.foot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ClientReserv {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	 private Long id; 
	 @ManyToOne
	 private Reservation reservation ;
	 @ManyToOne
	 private Panier panier ;
	 private Long nbr ;
	 
	public ClientReserv(Reservation reservation, Panier panier, Long nbr) {
		super();
		this.reservation = reservation;
		this.panier = panier;
		this.nbr = nbr;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public Panier getPanier() {
		return panier;
	}
	public void setPanier(Panier panier) {
		this.panier = panier;
	}
	public Long getNbr() {
		return nbr;
	}
	public void setNbr(Long nbr) {
		this.nbr = nbr;
	}

	@Override
	public String toString() {
		return "ClientReserv [id=" + id + ", reservation=" + reservation + ", panier=" + panier + ", nbr=" + nbr + "]";
	}
	 
	
	 

}
