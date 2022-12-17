package ma.emsi.foot.service;

import java.util.List;


import ma.emsi.foot.model.Panier;

public interface PanierService {
	
	Panier ajouter(Panier panier);

	Panier modifier(Panier panier,Long id);
	
	void supprimer(Long id);

	Panier getPanier(Long id);
	
	List<Panier> liste();
	

}
