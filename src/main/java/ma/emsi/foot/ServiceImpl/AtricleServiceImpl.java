package ma.emsi.foot.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.emsi.foot.model.Article;
import ma.emsi.foot.repository.ArticleRepository;
import ma.emsi.foot.service.ArticleService;

@Service
@Transactional
public class AtricleServiceImpl implements ArticleService {
	@Autowired
	private ArticleRepository repository;

	@Override
	public Article ajouter(Article terrain) {
		return repository.save(terrain);
	}

	@Override
	public Article modifier(Article article, Long id) {
		// TODO Auto-generated method stub
		Article article2 = repository.findById(id).get();
		if (article2 != null) {
			article2.setDate(article.getDate());
			article2.setArt(article.getArt());
			article2.setAdmin(article.getAdmin());
			article2.setTitre(article.getTitre());
			return repository.save(article2);
		}
		return null;
		
	}

	@Override
	public void supprimer(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Article getArticle(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Article> liste() {
		return repository.findAll();
	}

}
