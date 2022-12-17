package ma.emsi.foot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.emsi.foot.model.Terrain;

public interface TerrainRepository extends JpaRepository<Terrain, Long> {

}
