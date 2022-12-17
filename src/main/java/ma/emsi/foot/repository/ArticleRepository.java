package ma.emsi.foot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.emsi.foot.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
