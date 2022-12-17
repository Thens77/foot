package ma.emsi.foot.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.emsi.foot.model.Club;
import ma.emsi.foot.repository.ClubRepository;
import ma.emsi.foot.service.ClubService;

@Service
@Transactional
public class ClubServiceImpl implements ClubService{
	
	@Autowired
	private ClubRepository repository;

	@Override
	public Club ajouter(Club club) {
		return repository.save(club);
	}

	@Override
	public Club modifier(Club club, Long id) {
		// TODO Auto-generated method stub
		Club club2 = repository.findById(id).get();
		if (club2 != null) {
			club2.setClubName(club.getClubName());
			club2.setDescription(club.getDescription());
			club2.setIdFiscal(club.getIdFiscal());
			club2.setLatitude(club.getLatitude());
			club2.setLongitude(club.getLongitude());
			club2.setProprietaire(club.getProprietaire());
			club2.setSosName(club.getSosName());
			club2.setTerrains(club.getTerrains());
			club2.setPhotos(club.getPhotos());
			return repository.save(club2);
		}
		return null ;
		
	}

	@Override
	public void supprimer(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Club getClub(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Club> liste() {
		return repository.findAll();
	}

}
