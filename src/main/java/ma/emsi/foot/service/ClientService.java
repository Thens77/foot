package ma.emsi.foot.service;

import java.util.List;


import ma.emsi.foot.model.Client;

public interface ClientService {
	
	Client ajouter(Client client);

	Client modifier(Client client,Long id);
	
	void supprimer(Long id);

	Client getClient(Long id);
	
	List<Client> liste();

}
