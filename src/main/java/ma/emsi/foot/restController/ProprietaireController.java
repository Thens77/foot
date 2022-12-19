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
import ma.emsi.foot.model.Proprietaire;
import ma.emsi.foot.service.PropritaireService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/proprietaires")
public class ProprietaireController {
	
	@Autowired
	PropritaireService service;

	@PostMapping
	public ResponseEntity<Proprietaire> ajouter(@RequestBody Proprietaire proprietaire) {
		
        if (proprietaire.getId() != null) {
            throw new BadRequestAlertException("A new proprietaire cannot already have an ID", "proprietaire" , "idexists");
        }
        Proprietaire result = service.ajouter(proprietaire);
        return ResponseEntity.ok(result);
		
	}
	
	@GetMapping
	public List<Proprietaire> liste() {
		return service.liste();
	}

	@GetMapping("/{id}")
	public Proprietaire getById(@PathVariable Long id) {
		return service.getProprietaire(id);
	}

	@GetMapping("/delete/{id}")
	public void supprimer(@PathVariable Long id) {
		service.supprimer(id);

	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Proprietaire> modifier(@PathVariable(value = "id", required = false) final Long id, @RequestBody Proprietaire proprietaire ) {
		 if (proprietaire.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", "Proprietaire ", "idnull");
	        }
	        if (!Objects.equals(id, proprietaire.getId())) {
	            throw new BadRequestAlertException("Invalid ID", "Proprietaire", "idinvalid");
	        }

	        if (service.getProprietaire(id)==null) {
	            throw new BadRequestAlertException("Entity not found", "Proprietaire ", "idnotfound");
	        }

	        Proprietaire result = service.modifier(proprietaire,id);
	        return ResponseEntity.ok(result);
    }


}
