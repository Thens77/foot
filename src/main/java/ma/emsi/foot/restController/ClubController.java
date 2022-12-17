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
import ma.emsi.foot.model.Club;
import ma.emsi.foot.service.ClubService;

public class ClubController {
	
	@Autowired
	ClubService service;

	@PostMapping
	public ResponseEntity<Club> ajouter(@RequestBody Club club) {
		
        if (club.getId() != null) {
            throw new BadRequestAlertException("A new boitier cannot already have an ID", "" , "idexists");
        }
        Club result = service.ajouter(club);
        return ResponseEntity.ok(result);
		
	}
	
	@GetMapping
	public List<Club> liste() {
		return service.liste();
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
		 if (club.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", " ", "idnull");
	        }
	        if (!Objects.equals(id, club.getId())) {
	            throw new BadRequestAlertException("Invalid ID", "", "idinvalid");
	        }

	        if (service.getClub(id)==null) {
	            throw new BadRequestAlertException("Entity not found", " ", "idnotfound");
	        }

	        Club result = service.modifier(club,id);
	        return ResponseEntity.ok(result);
    }

}
