package ma.emsi.foot.service;

import java.util.Date;
import java.util.List;

import ma.emsi.foot.model.Reservation;
import ma.emsi.foot.model.Terrain;

public interface ReservationService {
	
	Reservation ajouter(Reservation reservation);

	Reservation modifier(Reservation reservation,Long id);
	
	void supprimer(Long id);

	Reservation getReservation(Long id);
	
	List<Reservation> liste();
	
	List<Reservation> listeByTerrainAndDate(Terrain s , Date d);	
	
	List<Reservation> findByTerrain(Terrain t);
	
	List<Reservation> findByClub(Long id);	
	
	List<Reservation> joinUs();
	
	List<Reservation> joinUsbyClub(Long id);
	
	List<Reservation> joinUsNum(Long num);

}
