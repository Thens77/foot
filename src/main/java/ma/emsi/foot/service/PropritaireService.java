package ma.emsi.foot.service;

import java.util.List;

import ma.emsi.foot.model.Proprietaire;

public interface PropritaireService {
	
	Proprietaire ajouter(Proprietaire proprietaire);

	Proprietaire modifier(Proprietaire proprietaire,Long id);
	
	void supprimer(Long id);

	Proprietaire getProprietaire(Long id);
	
	List<Proprietaire> liste();

}
