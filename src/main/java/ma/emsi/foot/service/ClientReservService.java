package ma.emsi.foot.service;

import java.util.List;

import ma.emsi.foot.model.ClientReserv;

public interface ClientReservService {
	
	ClientReserv ajouter(ClientReserv clientReserv);

	ClientReserv modifier(ClientReserv clientReserv,Long id);
	
	void supprimer(Long id);

	ClientReserv getClientReserv(Long id);
	
	List<ClientReserv> liste();

}
