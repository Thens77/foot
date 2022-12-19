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
import ma.emsi.foot.model.Reservation;
import ma.emsi.foot.service.ReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/reservations")
public class ReservationController {
	
	@Autowired
	ReservationService service;

	@PostMapping
	public ResponseEntity<Reservation> ajouter(@RequestBody Reservation reservation) {
		
        if (reservation.getId() != null) {
            throw new BadRequestAlertException("A new reservation cannot already have an ID", "Reservation" , "idexists");
        }
        Reservation result = service.ajouter(reservation);
        return ResponseEntity.ok(result);
		
	}
	
	@GetMapping
	public List<Reservation> liste() {
		return service.liste();
	}

	@GetMapping("/{id}")
	public Reservation getById(@PathVariable Long id) {
		return service.getReservation(id);
	}

	@GetMapping("/delete/{id}")
	public void supprimer(@PathVariable Long id) {
		service.supprimer(id);

	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Reservation> modifier(@PathVariable(value = "id", required = false) final Long id, @RequestBody Reservation reservation ) {
		 if (reservation.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", "reservation ", "idnull");
	        }
	        if (!Objects.equals(id, reservation.getId())) {
	            throw new BadRequestAlertException("Invalid ID", "reservation", "idinvalid");
	        }

	        if (service.getReservation(id)==null) {
	            throw new BadRequestAlertException("Entity not found", " reservation", "idnotfound");
	        }

	        Reservation result = service.modifier(reservation,id);
	        return ResponseEntity.ok(result);
    }


}
