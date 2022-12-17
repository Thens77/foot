package ma.emsi.foot.restController;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ma.emsi.foot.error.BadRequestAlertException;
import ma.emsi.foot.model.Commentaire;
import ma.emsi.foot.service.CommentaireService;

public class CommentaireController {
	
	@Autowired
	CommentaireService service;

	@PostMapping
	public ResponseEntity<Commentaire> ajouter(@RequestBody Commentaire commentaire) {
		
        if (commentaire.getId() != null) {
            throw new BadRequestAlertException("A new boitier cannot already have an ID", "" , "idexists");
        }
        Commentaire result = service.ajouter(commentaire);
        return ResponseEntity.ok(result);
		
	}
	
	@GetMapping
	public List<Commentaire> liste() {
		return service.liste();
	}

	@GetMapping("/{id}")
	public Commentaire getById(@PathVariable Long id) {
		return service.getCommentaire(id);
	}

	@GetMapping("/delete/{id}")
	public void supprimer(@PathVariable Long id) {
		service.supprimer(id);

	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Commentaire> modifier(@PathVariable(value = "id", required = false) final Long id, @RequestBody Commentaire commentaire ) {
		 if (commentaire.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", " ", "idnull");
	        }
	        if (!Objects.equals(id, commentaire.getId())) {
	            throw new BadRequestAlertException("Invalid ID", "", "idinvalid");
	        }

	        if (service.getCommentaire(id)==null) {
	            throw new BadRequestAlertException("Entity not found", " ", "idnotfound");
	        }

	        Commentaire result = service.modifier(commentaire,id);
	        return ResponseEntity.ok(result);
    }

}
