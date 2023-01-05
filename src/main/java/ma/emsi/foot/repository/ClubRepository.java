package ma.emsi.foot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.emsi.foot.model.Club;

public interface ClubRepository extends JpaRepository<Club, Long>{

	@Query("select c from Club c where c.proprietaire.id =?1 ")
	List<Club>  getByProprietaire(Long id);
	
	@Query("select c from Club c where c.etat = 1")
	List<Club> getVerified();
}
