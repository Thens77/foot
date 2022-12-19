package ma.emsi.foot.model;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Admin extends Utilisateur {


	public Admin() {
	}

	public Admin(String userName, String password, String nom, String prenom, int age, String email, boolean isNotLocked, List<Role> roles) {
		super(userName, password, nom, prenom, age, email, isNotLocked, roles);
		
	}

	/*
	 * public Client(Long id, String userName, String password, String nom, String
	 * prenom, int age, String email, String cin, byte[] picByte, boolean isActive,
	 * boolean isNotLocked, String[] autorities, List<Role> roles) { super(id,
	 * userName, password, nom, prenom, age, email, cin, picByte, isActive,
	 * isNotLocked, autorities, roles); // TODO Auto-generated constructor stub }
	 */
	

	public Admin(Long id, String userName, String password, String nom, String prenom, int age, String email,
			 byte[] picByte, boolean isActive) {
		super();
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Admin [getAutorities()=" + Arrays.toString(getAutorities()) + ", isNotLocked()=" + isNotLocked() + ", getId()=" + getId() + ", getUserName()=" + getUserName()
				+ ", getPassword()=" + getPassword() + ", getNom()=" + getNom() + ", getPicByte()="
				+ Arrays.toString(getPicByte()) + ", getPrenom()=" + getPrenom() + ", getAge()=" + getAge()
				+ ", getEmail()=" + getEmail() + ", getRoles()=" + getRoles() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	

}
