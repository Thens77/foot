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
import ma.emsi.foot.model.Client;
import ma.emsi.foot.service.ClientService;

public class ClientController {
	
	@Autowired
	ClientService service;
	
	private static final String ENTITY_NAME = "client";

	@PostMapping
	public ResponseEntity<Client> ajouter(@RequestBody Client client) {
		
        if (client.getId() != null) {
            throw new BadRequestAlertException("A new boitier cannot already have an ID", ENTITY_NAME , "idexists");
        }
        Client result = service.ajouter(client);
        
        return ResponseEntity.ok(result);
		
	}
	
	@GetMapping
	public List<Client> liste() {
		return service.liste();
	}

	@GetMapping("/{id}")
	public Client getById(@PathVariable Long id) {
		return service.getClient(id);
	}

	@GetMapping("/delete/{id}")
	public void supprimer(@PathVariable Long id) {
		service.supprimer(id);

	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Client> modifier(@PathVariable(value = "id", required = false) final Long id, @RequestBody Client client ) {
		 if (client.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
	        }
	        if (!Objects.equals(id, client.getId())) {
	            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
	        }

	        if (service.getClient(id)==null) {
	            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
	        }

	        Client result = service.modifier(client,id);
	        return ResponseEntity.ok(result);
    }

}
