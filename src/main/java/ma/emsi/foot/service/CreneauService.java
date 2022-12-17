package ma.emsi.foot.service;

import java.util.Date;
import java.util.List;

import ma.emsi.foot.model.Creneau;
import ma.emsi.foot.model.Terrain;

public interface CreneauService {
	
	Creneau ajouter(Creneau creneau);

	Creneau modifier(Creneau creneau,Long id);
	
	void supprimer(Long id);

	Creneau getCreneau(Long id);
	
	List<Creneau> liste();
	
	List<Creneau> findDispo(Terrain t , Date d);


}
