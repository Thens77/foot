package ma.emsi.foot.restController;
 
import java.util.Date;
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
import ma.emsi.foot.model.Creneau;
import ma.emsi.foot.model.Terrain;
import ma.emsi.foot.service.ClubService;
import ma.emsi.foot.service.CreneauService;
import ma.emsi.foot.service.TerrainService;
 
class Matrix{
public Long terrain;
public Date date;
 
 

}

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/creneaux")
public class CreneauController {
 

@Autowired
CreneauService service;
@Autowired
TerrainService terrainservice;
 

@Autowired
ClubService serviceC ;
 
@PostMapping
public ResponseEntity<Creneau> ajouter(@RequestBody Creneau creneau) {
 

        if (creneau.getId() != null) {
            throw new BadRequestAlertException("A new creneau cannot already have an ID", "creneau" , "idexists");
        }
        Creneau result = service.ajouter(creneau);
        return ResponseEntity.ok(result);
 

}
 

@GetMapping
public List<Creneau> liste() {
return service.liste();
}
 

@GetMapping("/club/{id}")
public List<Creneau> liste2(@PathVariable Long id) {
Club c = serviceC.getClub(id);
return service.findByClub(c);
}
 
@GetMapping("/{id}")
public Creneau getById(@PathVariable Long id) {
return service.getCreneau(id);
}
 
@GetMapping("/delete/{id}")
public void supprimer(@PathVariable Long id) {
service.supprimer(id);
 
}
@PostMapping("/dispo")
public List<Creneau> crenauxdispo(@RequestBody Matrix matrix) {
Terrain terrain = terrainservice.getTerrain(matrix.terrain);
System.out.println("from creneaucontroller " + terrain);
return service.findDispo(terrain, matrix.date);
 

 
}
 

@PutMapping("/{id}")
    public ResponseEntity<Creneau> modifier(@PathVariable(value = "id", required = false) final Long id, @RequestBody Creneau creneau ) {
if (creneau.getId() == null) {
            throw new BadRequestAlertException("Invalid id", "creneau", "idnull");
        }
        if (!Objects.equals(id, creneau.getId())) {
            throw new BadRequestAlertException("Invalid ID", "creneau", "idinvalid");
        }
 
        if (service.getCreneau(id)==null) {
            throw new BadRequestAlertException("Entity not found", "creneau", "idnotfound");
        }
 
        Creneau result = service.modifier(creneau,id);
        return ResponseEntity.ok(result);
    }
 
 
}
 