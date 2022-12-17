package ma.emsi.foot.service;

import java.util.List;

import ma.emsi.foot.model.Article;

public interface ArticleService {
	
	Article ajouter(Article article);

	Article modifier(Article article,Long id);
	
	void supprimer(Long id);

	Article getArticle(Long id);
	
	List<Article> liste();

}
