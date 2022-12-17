package ma.emsi.foot.service;

import java.util.List;

import ma.emsi.foot.model.Commentaire;

public interface CommentaireService {
	
	Commentaire ajouter(Commentaire commentaire);

	Commentaire modifier(Commentaire commentaire,Long id);
	
	void supprimer(Long id);

	Commentaire getCommentaire(Long id);
	
	List<Commentaire> liste();

}
