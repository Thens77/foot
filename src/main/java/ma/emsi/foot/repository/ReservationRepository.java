package ma.emsi.foot.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.emsi.foot.model.Reservation;
import ma.emsi.foot.model.Terrain;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	//@Query("select r from Reservation r where r.terrain.id = ?1 and r.date = ?2 ")
	List<Reservation> findByTerrainAndDate(Terrain s , Date c);

	List<Reservation> findByTerrain(Terrain terrain);
	
	@Query("select r from Reservation r where r.terrain.club.id = ?1 ")
	List<Reservation> findByClub(Long idc);
		
	@Query("select r from Reservation r where r.etat = 0 ")
	List<Reservation> joinUs();
	
	@Query("select r from Reservation r where r.terrain.club.id=?1 and r.etat = 0 ")
	List<Reservation> joinUsbyClub(Long c);
	
	@Query("select r from Reservation r where r.nbrjoueur = ?1 ")
	List<Reservation> joinUsNum(Long num);
	
	@Query("select r from Reservation r where r.id = ?1 ")
	Optional<Reservation> findById(Long id);
}
