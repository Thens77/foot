package ma.emsi.foot.restController;

import java.sql.Date;
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
import ma.emsi.foot.model.Terrain;
import ma.emsi.foot.service.ClientReservService;
import ma.emsi.foot.service.ReservationService;
import ma.emsi.foot.service.TerrainService;
class addAssoc{
public Long id ;
public Long nbrjoueur ;
public Long idc ;
}

class obj{
public Long id ;
public Date date ;
}
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/reservations")
public class ReservationController {
	
	@Autowired
	ReservationService service;
	
	@Autowired
	TerrainService serviceT ;
	
	@Autowired
	ClientReservService seClientReserv;

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
	
	@PostMapping("/test")
	public List<Reservation> liste2(@RequestBody obj obj) {
		System.out.println("DAAAAAAAAAAAAAAAAAAAAAAAAATE : " + obj.date + " " + obj.id);
		Terrain t = serviceT.getTerrain(obj.id);
		return service.listeByTerrainAndDate(t , obj.date);
	}
	
	@GetMapping("/club/{id}")
	public List<Reservation> findByClub(@PathVariable Long id){
		return service.findByClub(id);
	}
	
	@GetMapping("/terrain/{id}")
	public List<Reservation> findbyterrain(@PathVariable Long id) {
		Terrain t = serviceT.getTerrain(id);
		return service.findByTerrain(t);
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
		System.out.println("from reserv controller" + id );
		if (reservation.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", "reservation ", "idnull");
	        }
	        if (!Objects.equals(id, reservation.getId())) {
	            throw new BadRequestAlertException("Invalid ID", "reservation", "idinvalid");
	        }

	        if (service.getReservation(id)==null) {
	            throw new BadRequestAlertException("Entity not found", " reservation", "idnotfound");
	        }
	        System.out.println("from reserv controller : " + reservation);
	        Reservation result = service.modifier(reservation,id);
	        return ResponseEntity.ok(result);
    }
	
	 

	@GetMapping("/join")
	public List<Reservation> jointeam(){
	 

	return service.joinUs();
	 

	}
	
	@GetMapping("/join/{id}")
	public List<Reservation> jointeamByclub(@PathVariable Long id){
	 

	return service.joinUsbyClub(id);
	 

	
	}
	
	
	@SuppressWarnings("removal")
	@PostMapping("/gg")
	 
	public void addtorecepy(@RequestBody addAssoc assc){
	System.out.println(assc.nbrjoueur+"{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{"+assc.id);
	Reservation veve= service.getReservation(assc.id);
	System.out.println(" from gg :" + assc.idc);
	seClientReserv.join1(veve,new Long(assc.nbrjoueur), assc.idc);
	}


}
