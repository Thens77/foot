package ma.emsi.foot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.emsi.foot.model.Client;
import ma.emsi.foot.model.Panier;

public interface PanierRepository extends JpaRepository<Panier, Long> {
	
	
	@Query("select p from Panier p where p.client.id = ?1")
	Panier findByClient(Long cc);
	
	List<Panier> findByClient(Client client);
}
