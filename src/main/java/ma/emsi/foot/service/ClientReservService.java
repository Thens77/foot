package ma.emsi.foot.service;

import java.util.List;

import ma.emsi.foot.model.ClientReserv;
import ma.emsi.foot.model.Panier;
import ma.emsi.foot.model.Reservation;

public interface ClientReservService {
	
	ClientReserv ajouter(ClientReserv clientReserv);

	ClientReserv modifier(ClientReserv clientReserv,Long id);
	
	void supprimer(Long id);

	ClientReserv getClientReserv(Long id);
	
	List<ClientReserv> liste();
	
  List<ClientReserv> findByReservation(Long id);
  
  void join(Reservation reser);
  
  void join1(Reservation reser,Long nbrJoueur , Long id) ;
  
  List<ClientReserv> findByPanier(Panier panier); 
}
