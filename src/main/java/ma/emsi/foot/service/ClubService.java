package ma.emsi.foot.service;

import java.util.List;

import ma.emsi.foot.model.Club;

public interface ClubService {
	
	Club ajouter(Club club);

	Club modifier(Club club,Long id);
	
	void supprimer(Long id);

	Club getClub(Long id);
	
	List<Club> liste();

}
