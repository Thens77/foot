package ma.emsi.foot.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.emsi.foot.model.Report;
import ma.emsi.foot.repository.ReportRepository;
import ma.emsi.foot.service.ReportService;

@Service
@Transactional
public class ReportServiceImpl implements ReportService{
	
	@Autowired
	private ReportRepository repository;

	@Override
	public Report ajouter(Report report) {
		return repository.save(report);
	}

	@Override
	public Report modifier(Report report, Long id) {
		// TODO Auto-generated method stub
		Report report2 = repository.findById(id).get();
		if (report2 != null) {
			report2.setClient(report2.getClient());
			report2.setReportMsg(report.getReportMsg());
			
			return repository.save(report2);
		}
		return null ;
	}

	@Override
	public void supprimer(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Report getReport(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Report> liste() {
		return repository.findAll();
	}

}
