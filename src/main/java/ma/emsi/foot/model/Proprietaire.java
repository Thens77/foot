package ma.emsi.foot.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("P")
public class Proprietaire extends Utilisateur {
	private String cin ;
	private boolean active ;

	public Proprietaire() {
	}


	public Proprietaire(String userName, String password, String nom, String prenom, int age, String email, String cin,
			boolean isActive, boolean isNotLocked, List<Role> roles) {
		super(userName, password, nom, prenom, age, email, isNotLocked, roles);
		this.active = isActive;
		this.cin = cin ;
	}

	
	public Proprietaire(Long id, String userName, String password, String nom, String prenom, int age, String email,
			String cin, byte[] picByte, boolean isActive) {
		super();
		this.active = isActive;
		// TODO Auto-generated constructor stub
	}
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	@Override
	public String toString() {
		return "Proprietaire [cin=" + cin + ", active=" + active + "]";
	}

	

}