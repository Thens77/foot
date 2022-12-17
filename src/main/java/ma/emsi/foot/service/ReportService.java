package ma.emsi.foot.service;

import java.util.List;

import ma.emsi.foot.model.Report;

public interface ReportService {
	
	Report ajouter(Report report);

	Report modifier(Report report,Long id);
	
	void supprimer(Long id);

	Report getReport(Long id);
	
	List<Report> liste();

}
