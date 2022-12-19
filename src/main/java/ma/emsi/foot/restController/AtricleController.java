package ma.emsi.foot.restController;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.emsi.foot.error.BadRequestAlertException;
import ma.emsi.foot.model.Article;
import ma.emsi.foot.service.ArticleService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/articles")
public class AtricleController {
	
	
	@Autowired
	ArticleService service;

	@PostMapping
	public ResponseEntity<Article> ajouter(@RequestBody Article article) {
		
        if (article.getId() != null) {
            throw new BadRequestAlertException("A new article cannot already have an ID", "article" , "idexists");
        }
        Article result = service.ajouter(article);
        
        return ResponseEntity.ok(result);	
	}
	@GetMapping
	public List<Article> liste() {
		return service.liste();
	}
	@GetMapping("/{id}")
	public Article getById(@PathVariable Long id) {
		return service.getArticle(id);
	}
	@GetMapping("/delete/{id}")
	public void supprimer(@PathVariable Long id) {
		service.supprimer(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Article> modifier(@PathVariable(value = "id", required = false) final Long id, @RequestBody Article  article ) {
		 if (article.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", "article", "idnull");
	        }
	        if (!Objects.equals(id, article.getId())) {
	            throw new BadRequestAlertException("Invalid ID", "article", "idinvalid");
	        }

	        if (service.getArticle(id)==null) {
	            throw new BadRequestAlertException("Entity not found", "article", "idnotfound");
	        }

	        Article result = service.modifier(article,id);
	        return ResponseEntity.ok(result);
   }
	
}
