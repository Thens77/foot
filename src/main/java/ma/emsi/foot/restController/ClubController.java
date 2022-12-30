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
import ma.emsi.foot.model.Club;
import ma.emsi.foot.service.ClubService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clubs")
public class ClubController {
	
	@Autowired
	ClubService service;

	@PostMapping
	public ResponseEntity<Club> ajouter(@RequestBody Club club) {
		
        if (club.getId() != null) {
            throw new BadRequestAlertException("A new club cannot already have an ID", "club" , "idexists");
        }
        club.setPicByte(service.compressBytes(club.getPicByte()));
        Club result = service.ajouter(club);
        return ResponseEntity.ok(result);
		
	}
	
	@GetMapping
	public List<Club> liste() {
		return service.liste();
	}
	
	@GetMapping("prop/{id}")
	public List<Club> listep(@PathVariable Long id) {
		return service.getByProprietaire(id);
	}

	@GetMapping("/{id}")
	public Club getById(@PathVariable Long id) {
		return service.getClub(id);
	}

	@GetMapping("/delete/{id}")
	public void supprimer(@PathVariable Long id) {
		service.supprimer(id);

	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Club> modifier(@PathVariable(value = "id", required = false) final Long id, @RequestBody Club club ) {
		System.out.println("controlller : " + club);
		 if (club.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", "club", "idnull");
	        }
	        if (!Objects.equals(id, club.getId())) {
	            throw new BadRequestAlertException("Invalid ID", "club", "idinvalid");
	        }

	        if (service.getClub(id)==null) {
	            throw new BadRequestAlertException("Entity not found", "club", "idnotfound");
	        }

	        Club result = service.modifier(club);
	        return ResponseEntity.ok(result);
    }

}
