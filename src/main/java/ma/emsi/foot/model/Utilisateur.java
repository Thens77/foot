package ma.emsi.foot.model;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Type")
public class Utilisateur  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private String userName;
	@Column(nullable = false)
	private String password;
	
	private String nom ; 
	private String prenom ;
	private int age ; 
	private String email ;
	
	
	@Column(name = "picByte", length = 100000)
	private byte[] picByte;
	
	private boolean isNotLocked ;
	
	private String[] autorities ;

	public Utilisateur(String userName, String password, String nom, String prenom, int age, String email,
			 boolean isNotLocked, List<Role> roles) {
		super();
		this.userName = userName;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.email = email;

		this.isNotLocked = isNotLocked;
		this.roles = roles;
	}
/*	
	public Utilisateur(Long id, String userName, String password, String nom, String prenom, int age, String email,
			String cin,  byte[] picByte) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.email = email;
		this.cin = cin;
		this.picByte = picByte;
	}*/
	public String[] getAutorities() {
		return autorities;
	}
	public void setAutorities(String[] autorities) {
		this.autorities = autorities;
	}
	
	public boolean isNotLocked() {
		return isNotLocked;
	}
	public void setNotLocked(boolean isNotLocked) {
		this.isNotLocked = isNotLocked;
	}
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles")
	private List<Role> roles;
	
	
	public Utilisateur() {

	}
	
	


	public Utilisateur(Long id, String userName, String password, String nom, String prenom, int age, String email,
			 byte[] picByte) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.email = email;
		
		this.picByte = picByte;
	
	}
	public Utilisateur(Long id, String userName, String password, String nom, String prenom, int age, String email,
			 boolean isNotLocked, String[] autorities, List<Role> roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.email = email;
		

		this.isNotLocked = isNotLocked;
		this.autorities = autorities;
		this.roles = roles;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public String getNom() {
		return nom;
	}
	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}


	

	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", userName=" + userName + ", password=" + password + ", nom=" + nom
				+ ", prenom=" + prenom + ", age=" + age + ", email=" + email 
				+ ", isNotLocked=" + isNotLocked + ", autorities=" + Arrays.toString(autorities) + ", roles=" + roles
				+ "]";
	}
	
	
	
	
	
}