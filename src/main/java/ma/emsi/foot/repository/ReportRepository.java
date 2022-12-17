package ma.emsi.foot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.emsi.foot.model.Report;

public interface ReportRepository extends JpaRepository<Report,Long> {

}
