package ma.emsi.foot.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.emsi.foot.model.Utilisateur;
import ma.emsi.foot.repository.UtilisateurRepository;
import ma.emsi.foot.service.UtilisateurService;


@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {
	

	@Autowired
	UtilisateurRepository utilisateurRepository ;
	
	@Override
	public Utilisateur ajouter(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return utilisateurRepository.save(utilisateur);
	}

	@Override
	public Utilisateur modifier(Utilisateur utilisateur, Long id) {
		// TODO Auto-generated method stub
		Utilisateur utilisateur2 = utilisateurRepository.findById(id).get();
		if(utilisateur2!=null) {
			utilisateur2.setAge(utilisateur.getAge());
			utilisateur2.setEmail(utilisateur.getEmail());
			utilisateur2.setPrenom(utilisateur.getPrenom());
			utilisateur2.setNom(utilisateur.getNom());
			utilisateur2.setPassword(utilisateur.getPassword());
			utilisateur2.setRoles(utilisateur.getRoles());
			utilisateur2.setUserName(utilisateur.getUserName());
			return utilisateurRepository.save(utilisateur2);
		}
		return null ;
		
	}

	@Override
	public void supprimer(Long id) {
		// TODO Auto-generated method stub
		utilisateurRepository.deleteById(id);
		
	}

	@Override
	public Utilisateur getUtilisateur(Long id) {
		// TODO Auto-generated method stub
		return utilisateurRepository.findById(id).get();
	}

	@Override
	public List<Utilisateur> liste() {
		// TODO Auto-generated method stub
		return utilisateurRepository.findAll();
	}
	

}
