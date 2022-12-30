package ma.emsi.foot.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
public class Club {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	 private Long id; 
	 private String idFiscal ; 
	 private String SosName ;
	 private String description ;
	 private String clubName ; 
	 private double longitude ;
	 private double latitude ;
	 @Column(name = "picByte", length = 100000)
	private byte[] picByte;
	 private boolean etat;
	 @ManyToOne
	 private Proprietaire proprietaire ;
	 @OneToMany(mappedBy = "club" , fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
		private Set<Photos> photos =new HashSet<Photos>();
	 @OneToMany(mappedBy = "club" , fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
	 @JsonManagedReference
		private Set<Terrain> terrains =new HashSet<Terrain>();
	 
	 public Club() {
		}
	 
	public Club(String idFiscal, String sosName, String description, String clubName, double longitude, double latitude,
			Proprietaire proprietaire, Set<Photos> photos, Set<Terrain> terrains,byte[] picByte) {
		super();
		this.idFiscal = idFiscal;
		SosName = sosName;
		this.description = description;
		this.clubName = clubName;
		this.longitude = longitude;
		this.latitude = latitude;
		this.proprietaire = proprietaire;
		this.photos = photos;
		this.terrains = terrains;
		this.picByte = picByte ;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdFiscal() {
		return idFiscal;
	}
	public void setIdFiscal(String idFiscal) {
		this.idFiscal = idFiscal;
	}
	public String getSosName() {
		return SosName;
	}
	public void setSosName(String sosName) {
		SosName = sosName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public Proprietaire getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(Proprietaire proprietaire) {
		this.proprietaire = proprietaire;
	}
	public Set<Photos> getPhotos() {
		return photos;
	}
	public void setPhotos(Set<Photos> photos) {
		this.photos = photos;
	}
	public Set<Terrain> getTerrains() {
		return terrains;
	}
	public void setTerrains(Set<Terrain> terrains) {
		this.terrains = terrains;
	}
	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
	
	
	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	@Override
	public String toString() {
		return "Club [id=" + id + ", idFiscal=" + idFiscal + ", SosName=" + SosName + ", description=" + description
				+ ", clubName=" + clubName + ", longitude=" + longitude + ", latitude=" + latitude + ", picByte="
				+ Arrays.toString(picByte) + ", etat=" + etat + ", proprietaire=" + proprietaire + ", photos=" + photos
				+ ", terrains=" + terrains + "]";
	}

	
	
	
	 
	 

}
