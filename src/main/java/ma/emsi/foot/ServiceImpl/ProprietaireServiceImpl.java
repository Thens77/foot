package ma.emsi.foot.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.emsi.foot.model.Proprietaire;
import ma.emsi.foot.repository.ProprietaireRepository;
import ma.emsi.foot.service.PropritaireService;

@Service
@Transactional
public class ProprietaireServiceImpl implements PropritaireService{
	
	@Autowired
	private ProprietaireRepository repository;

	@Override
	public Proprietaire ajouter(Proprietaire club) {
		club.setActive(true);
		return repository.save(club);
	}

	@Override
	public Proprietaire modifier(Proprietaire proprietaire, Long id) {
		// TODO Auto-generated method stub
		Proprietaire proprietaire2 = repository.findById(id).get();
		if (proprietaire2 != null) {
			proprietaire2.setAge(proprietaire.getAge());
			proprietaire2.setCin(proprietaire.getCin());
        	proprietaire2.setEmail(proprietaire.getEmail());
        	proprietaire2.setNom(proprietaire.getNom());
        	proprietaire2.setActive(proprietaire.isActive());
        	proprietaire2.setPassword(proprietaire.getPassword());
        	proprietaire2.setPrenom(proprietaire.getPrenom());
        	proprietaire2.setRoles(proprietaire.getRoles());
        	proprietaire2.setUserName(proprietaire.getUserName());
        	proprietaire2.setPicByte(proprietaire.getPicByte());
        	
        	return repository.save(proprietaire2);
		}
		return null ;
	}

	@Override
	public void supprimer(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Proprietaire getProprietaire(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Proprietaire> liste() {
		return repository.findAll();
	}


}
