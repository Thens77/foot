package ma.emsi.foot.service;

import java.util.List;

import ma.emsi.foot.model.Terrain;

public interface TerrainService {
	
	Terrain ajouter(Terrain terrain);

	Terrain modifier(Terrain terrain,Long id);
	
	void supprimer(Long id);

	Terrain getTerrain(Long id);
	
	List<Terrain> liste();

}
