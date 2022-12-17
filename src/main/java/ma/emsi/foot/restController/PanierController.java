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
import ma.emsi.foot.model.Panier;
import ma.emsi.foot.service.PanierService;

public class PanierController {
	
	@Autowired
	PanierService service;

	@PostMapping
	public ResponseEntity<Panier> ajouter(@RequestBody Panier panier) {
		
        if (panier.getId() != null) {
            throw new BadRequestAlertException("A new boitier cannot already have an ID", "" , "idexists");
        }
        Panier result = service.ajouter(panier);
        return ResponseEntity.ok(result);
		
	}
	
	@GetMapping
	public List<Panier> liste() {
		return service.liste();
	}

	@GetMapping("/{id}")
	public Panier getById(@PathVariable Long id) {
		return service.getPanier(id);
	}

	@GetMapping("/delete/{id}")
	public void supprimer(@PathVariable Long id) {
		service.supprimer(id);

	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Panier> modifier(@PathVariable(value = "id", required = false) final Long id, @RequestBody Panier panier ) {
		 if (panier.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", " ", "idnull");
	        }
	        if (!Objects.equals(id, panier.getId())) {
	            throw new BadRequestAlertException("Invalid ID", "", "idinvalid");
	        }

	        if (service.getPanier(id)==null) {
	            throw new BadRequestAlertException("Entity not found", " ", "idnotfound");
	        }

	        Panier result = service.modifier(panier,id);
	        return ResponseEntity.ok(result);
    }

}
