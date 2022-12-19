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
import ma.emsi.foot.model.Terrain;
import ma.emsi.foot.service.TerrainService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/terrains")
public class TerrainController {

	
	@Autowired
	TerrainService service;

	@PostMapping
	public ResponseEntity<Terrain> ajouter(@RequestBody Terrain terrain) {
		
        if (terrain.getId() != null) {
            throw new BadRequestAlertException("A new Terrain cannot already have an ID", "" , "idexists");
        }
        Terrain result = service.ajouter(terrain);
        return ResponseEntity.ok(result);
		
	}
	
	@GetMapping
	public List<Terrain> liste() {
		return service.liste();
	}

	@GetMapping("/{id}")
	public Terrain getById(@PathVariable Long id) {
		return service.getTerrain(id);
	}

	@GetMapping("/delete/{id}")
	public void supprimer(@PathVariable Long id) {
		service.supprimer(id);

	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Terrain> modifier(@PathVariable(value = "id", required = false) final Long id, @RequestBody Terrain terrain ) {
		 if (terrain.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", " Terrain", "idnull");
	        }
	        if (!Objects.equals(id, terrain.getId())) {
	            throw new BadRequestAlertException("Invalid ID", "Terrain", "idinvalid");
	        }

	        if (service.getTerrain(id)==null) {
	            throw new BadRequestAlertException("Entity not found", " Terrain", "idnotfound");
	        }

	        Terrain result = service.modifier(terrain,id);
	        return ResponseEntity.ok(result);
    }

}
