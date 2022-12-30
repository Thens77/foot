package ma.emsi.foot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ma.emsi.foot.model.Terrain;

public interface TerrainRepository extends JpaRepository<Terrain, Long> {
	
	@Query("Select t from Terrain t where t.club.id =?1 ")
	List<Terrain> findByClub(Long idc);

}
