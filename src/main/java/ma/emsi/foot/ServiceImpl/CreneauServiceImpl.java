package ma.emsi.foot.ServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.emsi.foot.model.Club;
import ma.emsi.foot.model.Creneau;
import ma.emsi.foot.model.Reservation;
import ma.emsi.foot.model.Terrain;
import ma.emsi.foot.repository.ClubRepository;
import ma.emsi.foot.repository.CreneauRepository;
import ma.emsi.foot.service.CreneauService;
import ma.emsi.foot.service.ReservationService;

@Service
@Transactional
public class CreneauServiceImpl implements CreneauService{
	
	@Autowired
	private ReservationService reservationService ;
	
	@Autowired
	private CreneauRepository creneauRepository;
	@Autowired
	private ClubRepository cr ;
	
	
	@Override
	public Creneau ajouter(Creneau creneau) {
		return creneauRepository.save(creneau);
	}

	@Override
	public Creneau modifier(Creneau creneau,Long id) {
		Creneau creneau2 = creneauRepository.findById(id).get();
		if(creneau2!=null) {
			creneau2.setHeureDebut(creneau.getHeureDebut());
			creneau2.setHeureFin(creneau.getHeureFin());
			return creneauRepository.save(creneau2);
		}
		return null;
		
	}

	@Override
	public void supprimer(Long id) {
		creneauRepository.deleteById(id);
		
	}

	@Override
	public Creneau getCreneau(Long id) {
		return creneauRepository.findById(id).get();
	}

	@Override
	public List<Creneau> liste() {
		return creneauRepository.findAll();
	}
	
	@Override
	public List<Creneau> findDispo(Terrain t , Date d) {
		 System.out.println("Hey crenn");
		 List<Creneau>  dispos = new ArrayList<Creneau>();
		 System.out.println("diiiiiipo " + t.getId());
		 Club cc = cr.getById(t.getClub().getId()); 
		 for(Creneau c : creneauRepository.findByClub(t.getClub())) {
			 
			 int check=0;
			 for(Reservation o : reservationService.listeByTerrainAndDate(t,d)) {
				  check = 0 ;
				 if(     (c.getHeureDebut().compareTo(o.getCreneau().getHeureDebut())<=0 && c.getHeureFin().compareTo(o.getCreneau().getHeureDebut())>0 )|| 
						 (c.getHeureDebut().compareTo(o.getCreneau().getHeureDebut())<=0 && c.getHeureFin().compareTo(o.getCreneau().getHeureFin())>=0 ) ||
						 (c.getHeureDebut().compareTo(o.getCreneau().getHeureFin())<0 && c.getHeureFin().compareTo(o.getCreneau().getHeureFin())>=0 )||
						 (c.getHeureDebut().compareTo(o.getCreneau().getHeureDebut())>=0 && c.getHeureFin().compareTo(o.getCreneau().getHeureFin())<=0 )) {
					 check = 1;
					 break ;
				 }
				 
			 } 
			 
			 if(check==0) {
				 dispos.add(c);
			 }
		 }
		 System.out.println("diiiiiipo " + t.getClub());
	        return dispos;
	}

	@Override
	public List<Creneau> findByClub(Club c) {
		return creneauRepository.findByClub(c);
	}
	


}
