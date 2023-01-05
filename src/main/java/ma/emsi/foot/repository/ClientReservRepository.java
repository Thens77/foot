package ma.emsi.foot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.emsi.foot.model.ClientReserv;
import ma.emsi.foot.model.Panier;

public interface ClientReservRepository extends JpaRepository<ClientReserv, Long> {
	@Query("select e from ClientReserv e where e.reservation.id= ?1")
	List<ClientReserv> findByReservation(Long id);
	
	List<ClientReserv> findByPanier(Panier panier);
	

}
