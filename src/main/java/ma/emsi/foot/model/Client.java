package ma.emsi.foot.model;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class Client extends Utilisateur {
	
	private boolean active = false;

	public Client() {
	}

	public Client(String userName, String password, String nom, String prenom, int age, String email,
			boolean isActive, boolean isNotLocked, List<Role> roles) {
		super(userName, password, nom, prenom, age, email, isNotLocked, roles);
		this.active = isActive;
		
	}

	/*
	 * public Client(Long id, String userName, String password, String nom, String
	 * prenom, int age, String email, String cin, byte[] picByte, boolean isActive,
	 * boolean isNotLocked, String[] autorities, List<Role> roles) { super(id,
	 * userName, password, nom, prenom, age, email, cin, picByte, isActive,
	 * isNotLocked, autorities, roles); // TODO Auto-generated constructor stub }
	 */
	

	public Client(Long id, String userName, String password, String nom, String prenom, int age, String email,
			 byte[] picByte, boolean isActive) {
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
	


	@Override
	public String toString() {
		return "Client [ active=" + active + "]";
	}

	

}