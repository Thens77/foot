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
import ma.emsi.foot.model.Report;
import ma.emsi.foot.service.ReportService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/reports")
public class ReportController {
	
	@Autowired
	ReportService service;

	@PostMapping
	public ResponseEntity<Report> ajouter(@RequestBody Report report) {
		
        if (report.getId() != null) {
            throw new BadRequestAlertException("A new Report cannot already have an ID", "report" , "idexists");
        }
        Report result = service.ajouter(report);
        return ResponseEntity.ok(result);
		
	}
	
	@GetMapping
	public List<Report> liste() {
		return service.liste();
	}

	@GetMapping("/{id}")
	public Report getById(@PathVariable Long id) {
		return service.getReport(id);
	}

	@GetMapping("/delete/{id}")
	public void supprimer(@PathVariable Long id) {
		service.supprimer(id);

	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Report> modifier(@PathVariable(value = "id", required = false) final Long id, @RequestBody Report report ) {
		 if (report.getId() == null) {
	            throw new BadRequestAlertException("Invalid id", "report ", "idnull");
	        }
	        if (!Objects.equals(id, report.getId())) {
	            throw new BadRequestAlertException("Invalid ID", "report", "idinvalid");
	        }

	        if (service.getReport(id)==null) {
	            throw new BadRequestAlertException("Entity not found", " report", "idnotfound");
	        }

	        Report result = service.modifier(report,id);
	        return ResponseEntity.ok(result);
    }


}
