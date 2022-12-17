package ma.emsi.foot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	 private Long id; 
	 private String nom; 
	 
	 public Role() { 
	 } 
	  
	 public Long getId() { 
	 return id; 
	 } 
	 public void setId(Long id) { 
	 this.id = id; 
	 } 
	 public Role(String name) { 
	 super(); 
	 this.nom = name; 
	 } 
	 public String getNom() { 
	 return nom; 
	 } 
	 public void setNom(String name) { 
	 this.nom = name; 
	 }



	@Override
	public String toString() {
		return "Role [id=" + id + ", nom=" + nom + "]";
	} 
	 
	 
	 
}
