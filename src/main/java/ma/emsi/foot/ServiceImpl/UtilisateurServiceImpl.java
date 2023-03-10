package ma.emsi.foot.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.emsi.foot.model.Role;
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
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("test1");
		Utilisateur utilisateur = utilisateurRepository.findByUserName(username);
		System.out.println("test2");
		if (utilisateur == null) {
			System.out.println("user not found");
			throw new UsernameNotFoundException("Nom d'utilisateur ou mot de passe erron??");
		}
		System.out.println(utilisateur);
		for (Role r : utilisateur.getRoles())
			System.out.println("Role:" + r.getNom());
		return new org.springframework.security.core.userdetails.User(utilisateur.getUserName(), utilisateur.getPassword(),
				utilisateur.getRoles().stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getNom())).collect(Collectors.toList()));

	}

}
