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
import ma.emsi.foot.model.Creneau;
import ma.emsi.foot.service.CreneauService;

public class CreneauController {
	
	@Autowired
	CreneauService service;

	@PostMapping
	public ResponseEntity<Creneau> ajouter(@RequestBody Creneau creneau) {
		
        if (creneau.getId() != null) {
            throw new BadRequestAlertException("A new boitier cannot already have an ID", "" , "idexists");
        }
        Creneau result = service.ajouter(creneau);
        return ResponseEntity.ok(result);
		
	}
	
	@GetMapping
	public List<Creneau> liste() {
		return service.liste();
	}

	@GetMapping("/{id}")
	public Creneau getById(@PathVariable Long id) {
		return service.getCreneau(id);
	}

	@GetMapping("/delete/{id}")
	public void supprimer(@PathVariable Long id) {
		service.supprimer(id);

	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Creneau> modifier(@PathVariable(value = "id", required = false) final Long id, @RequestBody Creneau creneau ) {
		 if (creneau.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", " ", "idnull");
	        }
	        if (!Objects.equals(id, creneau.getId())) {
	            throw new BadRequestAlertException("Invalid ID", "", "idinvalid");
	        }

	        if (service.getCreneau(id)==null) {
	            throw new BadRequestAlertException("Entity not found", " ", "idnotfound");
	        }

	        Creneau result = service.modifier(creneau,id);
	        return ResponseEntity.ok(result);
    }


}
