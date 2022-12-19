package ma.emsi.foot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	Client client;
	private String reportMsg;
	
	 public Report() {
		}

	public Report(Client client, String reportMsg) {
		super();
		this.client = client;
		this.reportMsg = reportMsg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getReportMsg() {
		return reportMsg;
	}

	public void setReportMsg(String reportMsg) {
		this.reportMsg = reportMsg;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", client=" + client + ", reportMsg=" + reportMsg + "]";
	}
	
	

}
