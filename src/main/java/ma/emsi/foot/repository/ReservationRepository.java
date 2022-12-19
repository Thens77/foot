package ma.emsi.foot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.emsi.foot.model.Reservation;
import ma.emsi.foot.model.Terrain;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	
	@Query("select r from Reservation r where r.terrain.id = ?1 and r.date = ?2 ")
	List<Reservation> findByTerrainAndDate(Terrain s , Date c);

}
