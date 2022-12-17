package ma.emsi.foot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Photos {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String url;
	@ManyToOne
	private Club club;
	
	@ManyToOne
	private Terrain terrain;

	
	public Photos(String url, Club club, Terrain terrain) {
		super();
		this.url = url;
		this.club = club;
		this.terrain = terrain;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	@Override
	public String toString() {
		return "Photos [id=" + id + ", url=" + url + ", club=" + club + ", terrain=" + terrain + "]";
	}

	
	

}
