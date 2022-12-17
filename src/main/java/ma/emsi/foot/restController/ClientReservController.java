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
import ma.emsi.foot.model.ClientReserv;
import ma.emsi.foot.service.ClientReservService;

public class ClientReservController {
	
	@Autowired
	ClientReservService service;

	@PostMapping
	public ResponseEntity<ClientReserv> ajouter(@RequestBody ClientReserv clientReserv) {
		
        if (clientReserv.getId() != null) {
            throw new BadRequestAlertException("A new boitier cannot already have an ID", "" , "idexists");
        }
        ClientReserv result = service.ajouter(clientReserv);
        
        return ResponseEntity.ok(result);
		
	}
	
	@GetMapping
	public List<ClientReserv> liste() {
		return service.liste();
	}

	@GetMapping("/{id}")
	public ClientReserv getById(@PathVariable Long id) {
		return service.getClientReserv(id);
	}

	@GetMapping("/delete/{id}")
	public void supprimer(@PathVariable Long id) {
		service.supprimer(id);

	}
	
	@PutMapping("/{id}")
    public ResponseEntity<ClientReserv> modifier(@PathVariable(value = "id", required = false) final Long id, @RequestBody ClientReserv clientReserv ) {
		 if (clientReserv.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", " ", "idnull");
	        }
	        if (!Objects.equals(id, clientReserv.getId())) {
	            throw new BadRequestAlertException("Invalid ID", "", "idinvalid");
	        }

	        if (service.getClientReserv(id)==null) {
	            throw new BadRequestAlertException("Entity not found", " ", "idnotfound");
	        }

	        ClientReserv result = service.modifier(clientReserv,id);
	        return ResponseEntity.ok(result);
    }


}
