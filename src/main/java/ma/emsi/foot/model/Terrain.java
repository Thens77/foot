package ma.emsi.foot.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity

public class Terrain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String size;
	private int nbrJoueurs;
	private double prix;
	private String description;
	@OneToMany(mappedBy = "terrain", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Photos> photos = new HashSet<Photos>();
	@ManyToOne
	@JsonBackReference
	private Club club;
	
	@Column(name = "picByte", length = 100000)
	private byte[] picByte;
	
	 public Terrain() {
		}

	public Terrain(String size, int nbrJoueurs, double prix, String description, Set<Photos> photos, Club club) {
		super();
		this.size = size;
		this.nbrJoueurs = nbrJoueurs;
		this.prix = prix;
		this.description = description;
		this.photos = photos;
		this.club = club;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Photos> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photos> photos) {
		this.photos = photos;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public int getNbrJoueurs() {
		return nbrJoueurs;
	}

	public void setNbrJoueurs(int nbrJoueurs) {
		this.nbrJoueurs = nbrJoueurs;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	
	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	@Override
	public String toString() {
		return "Terrain [id=" + id + ", size=" + size + ", nbrJoueurs=" + nbrJoueurs + ", prix=" + prix
				+ ", description=" + description + ", photos=" + photos+ "]";
	}
	
	

}
