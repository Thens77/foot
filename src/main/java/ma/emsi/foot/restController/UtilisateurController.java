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
import ma.emsi.foot.model.Utilisateur;
import ma.emsi.foot.service.UtilisateurService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/utilisateurs")
public class UtilisateurController {
	
	@Autowired
	UtilisateurService service;

	@PostMapping
	public ResponseEntity<Utilisateur> ajouter(@RequestBody Utilisateur utilisateur) {
		
        if (utilisateur.getId() != null) {
            throw new BadRequestAlertException("A new utilisateur cannot already have an ID", "Utilisateur" , "idexists");
        }
        Utilisateur result = service.ajouter(utilisateur);
        return ResponseEntity.ok(result);
		
	}
	
	@GetMapping
	public List<Utilisateur> liste() {
		return service.liste();
	}

	@GetMapping("/{id}")
	public Utilisateur getById(@PathVariable Long id) {
		return service.getUtilisateur(id);
	}

	@GetMapping("/delete/{id}")
	public void supprimer(@PathVariable Long id) {
		service.supprimer(id);

	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Utilisateur> modifier(@PathVariable(value = "id", required = false) final Long id, @RequestBody Utilisateur utilisateur ) {
		 if (utilisateur.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", " Utilisateur", "idnull");
	        }
	        if (!Objects.equals(id, utilisateur.getId())) {
	            throw new BadRequestAlertException("Invalid ID", "Utilisateur", "idinvalid");
	        }

	        if (service.getUtilisateur(id)==null) {
	            throw new BadRequestAlertException("Entity not found", "Utilisateur ", "idnotfound");
	        }

	        Utilisateur result = service.modifier(utilisateur,id);
	        return ResponseEntity.ok(result);
    }


}
