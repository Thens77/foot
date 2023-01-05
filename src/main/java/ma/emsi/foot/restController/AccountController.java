package ma.emsi.foot.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.emsi.foot.auth.AuthenticationRequest;
import ma.emsi.foot.auth.AuthenticationResponse;
import ma.emsi.foot.model.Client;
import ma.emsi.foot.model.Proprietaire;
import ma.emsi.foot.model.Role;
import ma.emsi.foot.model.Utilisateur;
import ma.emsi.foot.register.MessageResponse;
import ma.emsi.foot.register.SignupRequest;
import ma.emsi.foot.repository.RoleRepository;
import ma.emsi.foot.repository.UtilisateurRepository;
import ma.emsi.foot.securite.JWTUtils;
import ma.emsi.foot.service.ClientService;
import ma.emsi.foot.service.PropritaireService;
import ma.emsi.foot.service.UtilisateurService;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {
	
	@Autowired
	private AuthenticationManager authenticationManager ;
	
	@Autowired
	UtilisateurService utilisateurService ;
	
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	UtilisateurRepository repository ;
	
	@Autowired
	ClientService clientService ;
	
	@Autowired
	PropritaireService propritaireService ;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JWTUtils jwt ;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate( @RequestBody AuthenticationRequest request){
		System.out.println("print" + request);
	 authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		 UserDetails userDetails = utilisateurService.loadUserByUsername(request.getUsername());
		Utilisateur user = repository.findByUserName(request.getUsername());
		final String jw = jwt.generateToken(userDetails);
		AuthenticationResponse authenticationResponse = new AuthenticationResponse(jw , userDetails.getUsername() , user.getRoles().get(0).getNom(),user.getId());
		System.out.println(request.getUsername());
		System.out.println(authenticationResponse.getAccessToken());
		return ResponseEntity.ok(authenticationResponse);
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) {
		if (repository.findByUserName(signUpRequest.getUsername())!=null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Invalid: cet nom d'utilisateur exist deja!"));
		}

		if (repository.findByEmail(signUpRequest.getEmail())!=null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Invalid: cet email exist deja!"));
		}
	
		
		Role rclient = roleRepo.findById((long) 3).get();
		List<Role> roles = new ArrayList<Role>();
		roles.add(rclient);
		// Create new user's account
		
		Proprietaire prop = new Proprietaire(signUpRequest.getUsername(), 
				          encoder.encode(signUpRequest.getPassword()),
				          signUpRequest.getNom() ,
				          signUpRequest.getPrenom() ,
				          signUpRequest.getAge(),
							 signUpRequest.getEmail(),
							 signUpRequest.getCin(),
							 true,
							 false,
							 roles
							);
				
		propritaireService.ajouter(prop);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	

	@PostMapping("/signupC")
	public ResponseEntity<?> registerClient( @RequestBody SignupRequest signUpRequest) {
		if (repository.findByUserName(signUpRequest.getUsername())!=null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Invalid: cet nom d'utilisateur exist deja!"));
		}

		if (repository.findByEmail(signUpRequest.getEmail())!=null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Invalid: cet email exist deja!"));
		}
	
		
		Role rclient = roleRepo.findById((long) 3).get();
		List<Role> roles = new ArrayList<Role>();
		roles.add(rclient);
		// Create new user's account
		
		Client client = new Client(signUpRequest.getUsername(), 
				          encoder.encode(signUpRequest.getPassword()),
				          signUpRequest.getNom() ,
				          signUpRequest.getPrenom() ,
				          signUpRequest.getAge(),
							 signUpRequest.getEmail(),
						
							 true,
							 false,
							 roles
							);
				
		clientService.ajouter(client);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}

