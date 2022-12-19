package ma.emsi.foot.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import ma.emsi.foot.model.Utilisateur;

public interface UtilisateurService extends UserDetailsService   {
	
	Utilisateur ajouter(Utilisateur utilisateur);

	Utilisateur modifier(Utilisateur utilisateur,Long id);
	
	void supprimer(Long id);

	Utilisateur getUtilisateur(Long id);
	
	List<Utilisateur> liste();

}
