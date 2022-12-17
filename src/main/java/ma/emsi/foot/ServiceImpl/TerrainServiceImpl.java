package ma.emsi.foot.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.emsi.foot.model.Terrain;
import ma.emsi.foot.repository.TerrainRepository;
import ma.emsi.foot.service.TerrainService;

@Service
@Transactional
public class TerrainServiceImpl implements TerrainService {

	@Autowired
	private TerrainRepository repository;

	@Override
	public Terrain ajouter(Terrain terrain) {
		return repository.save(terrain);
	}

	@Override
	public Terrain modifier(Terrain terrain, Long id) {
		// TODO Auto-generated method stub
		Terrain terrain2 = repository.findById(id).get();
		if (terrain2 != null) {
			terrain2.setClub(terrain.getClub());
			terrain2.setDescription(terrain.getDescription());
			terrain2.setNbrJoueurs(terrain.getNbrJoueurs());
			terrain2.setPrix(terrain.getPrix());
			terrain2.setSize(terrain.getSize());
			terrain2.setPhotos(terrain.getPhotos());
			return repository.save(terrain2);
		}
		return null ;
	}

	@Override
	public void supprimer(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Terrain getTerrain(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Terrain> liste() {
		return repository.findAll();
	}

}
