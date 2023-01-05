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
import ma.emsi.foot.model.Client;
import ma.emsi.foot.model.Panier;
import ma.emsi.foot.service.ClientService;
import ma.emsi.foot.service.PanierService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/paniers")
public class PanierController {
	
	@Autowired
	PanierService service;
	
	@Autowired
	ClientService clientserv ;

	@PostMapping
	public ResponseEntity<Panier> ajouter(@RequestBody Panier panier) {
		
        if (panier.getId() != null) {
            throw new BadRequestAlertException("A new panier cannot already have an ID", "panier" , "idexists");
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
	
	@GetMapping("/user/{id}")
	public List<Panier> getByuser(@PathVariable Long id) {
		Client c = clientserv.getClient(id);
		return service.findByClient(c);
	}

	@GetMapping("/delete/{id}")
	public void supprimer(@PathVariable Long id) {
		service.supprimer(id);

	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Panier> modifier(@PathVariable(value = "id", required = false) final Long id, @RequestBody Panier panier ) {
		 if (panier.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", "panier ", "idnull");
	        }
	        if (!Objects.equals(id, panier.getId())) {
	            throw new BadRequestAlertException("Invalid ID", "panier", "idinvalid");
	        }

	        if (service.getPanier(id)==null) {
	            throw new BadRequestAlertException("Entity not found", "panier", "idnotfound");
	        }

	        Panier result = service.modifier(panier,id);
	        return ResponseEntity.ok(result);
    }

}
