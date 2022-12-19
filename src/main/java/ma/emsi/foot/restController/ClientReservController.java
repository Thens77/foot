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
import ma.emsi.foot.model.ClientReserv;
import ma.emsi.foot.service.ClientReservService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clientreservations")
public class ClientReservController {
	
	@Autowired
	ClientReservService service;

	@PostMapping
	public ResponseEntity<ClientReserv> ajouter(@RequestBody ClientReserv clientReserv) {
		
        if (clientReserv.getId() != null) {
            throw new BadRequestAlertException("A new clientreservations cannot already have an ID", "clientreservations" , "idexists");
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
	            throw new BadRequestAlertException("Invalid id", "clientreservations ", "idnull");
	        }
	        if (!Objects.equals(id, clientReserv.getId())) {
	            throw new BadRequestAlertException("Invalid ID", "clientreservations", "idinvalid");
	        }

	        if (service.getClientReserv(id)==null) {
	            throw new BadRequestAlertException("Entity not found", "clientreservations", "idnotfound");
	        }

	        ClientReserv result = service.modifier(clientReserv,id);
	        return ResponseEntity.ok(result);
    }


}
