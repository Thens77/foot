package ma.emsi.foot.model;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Article {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	 private Long id ;
	 private Date date ;
	 private String art ;
	 private String titre ;
	 
	 
	 @ManyToOne
	 private Admin admin ;

	public Article(Date date, String art, Admin admin ,String titre ) {
		super();
		this.date = date;
		this.art = art;
		this.admin = admin;
		this.titre = titre ;
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getArt() {
		return art;
	}


	public void setArt(String art) {
		this.art = art;
	}


	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}


	@Override
	public String toString() {
		return "Article [id=" + id + ", date=" + date + ", art=" + art + ", titre=" + titre + ", admin=" + admin + "]";
	}

	
	 
	 
 
}
