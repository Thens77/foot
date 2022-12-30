package ma.emsi.foot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.emsi.foot.model.Club;
import ma.emsi.foot.model.Creneau;

public interface CreneauRepository extends JpaRepository<Creneau, Long> {
	
	List<Creneau> findByClub(Club club);

}
