package ma.emsi.foot.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.emsi.foot.model.Commentaire;
import ma.emsi.foot.repository.CommentaireRepository;
import ma.emsi.foot.service.CommentaireService;

@Service
@Transactional
public class CommentaireServiceImpl implements CommentaireService{
	
	@Autowired
	private CommentaireRepository repository;

	@Override
	public Commentaire ajouter(Commentaire commentaire) {
		return repository.save(commentaire);
	}

	@Override
	public Commentaire modifier(Commentaire commentaire, Long id) {
		// TODO Auto-generated method stub
		Commentaire commentaire2 = repository.findById(id).get();
		if (commentaire2 != null) {
			commentaire2.setCommentaire(commentaire.getCommentaire());
			commentaire2.setDate(commentaire2.getDate());
			commentaire2.setClient(commentaire.getClient());
			commentaire2.setClub(commentaire.getClub());
			return repository.save(commentaire2);
		}
		return null ;
	}

	@Override
	public void supprimer(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Commentaire getCommentaire(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Commentaire> liste() {
		return repository.findAll();
	}

}
