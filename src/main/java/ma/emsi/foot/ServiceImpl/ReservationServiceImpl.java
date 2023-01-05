package ma.emsi.foot.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.emsi.foot.model.Client;
import ma.emsi.foot.model.ClientReserv;
import ma.emsi.foot.model.Panier;
import ma.emsi.foot.model.Reservation;
import ma.emsi.foot.model.Terrain;
import ma.emsi.foot.repository.ClientRepository;
import ma.emsi.foot.repository.ClientReservRepository;
import ma.emsi.foot.repository.PanierRepository;
import ma.emsi.foot.repository.ReservationRepository;
import ma.emsi.foot.service.ClientReservService;
import ma.emsi.foot.service.ReservationService;
import ma.emsi.foot.service.TerrainService;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	private ReservationRepository repository;

	@Autowired
	private TerrainService t ;
	
	@Autowired
	private PanierRepository panierrepo ;
	@Autowired
	private ClientRepository cp ;
	
	@Autowired
	private ClientReservService clientReserv ;
	@Override
	public Reservation ajouter(Reservation reservation) {
		if(reservation.getNbrjoueur() == reservation.getTerrain().getNbrJoueurs()) {
			reservation.setEtat(true);
		}
		ClientReserv assoc = new ClientReserv();
		
		Panier cc = panierrepo.findByClient(reservation.getReservedBy().getId());
		Client c =  cp.getById(reservation.getReservedBy().getId());
		if(cc == null) {
			Panier pp = new Panier();
			pp.setClient (c);
			panierrepo.save(pp);
		}
		Panier afterparty = panierrepo.findByClient(reservation.getReservedBy().getId()); 
		assoc.setReservation(reservation);
		assoc.setPanier(afterparty);
		assoc.setNbr(reservation.getNbrjoueur());
		 
  		clientReserv.ajouter(assoc);
		return repository.save(reservation);
	}
	@Override
	public Reservation modifier(Reservation reservation, Long id) {
		// TODO Auto-generated method stub
		System.out.println("Id Long : " + id);
		System.out.println("Id : " + reservation.getId());
		System.out.println("from update : " + reservation.getNbrjoueur());
		Reservation reservation2 = repository.findById(id).get();
		if (reservation2 != null) {
			reservation2.setCreneau(reservation.getCreneau());
			reservation2.setDate(reservation.getDate());
			reservation2.setEtat(reservation.isEtat());
			reservation2.setNbrjoueur(reservation.getNbrjoueur());
			reservation2.setReservedBy(reservation.getReservedBy());
			reservation2.setTerrain(reservation.getTerrain());
			return repository.save(reservation2);
		}
		return null ;
	}

	@Override
	public void supprimer(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Reservation getReservation(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Reservation> liste() {
		return repository.findAll();
	}
	
	@Override
	public
	List<Reservation> listeByTerrainAndDate(Terrain s , Date d) {
		System.out.println(s);
		System.out.println(d);
		//String pattern = "yyyy-MM-dd";
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		//String date = simpleDateFormat.format(d);
		System.out.println(repository.findByTerrainAndDate(s, d));
		return repository.findByTerrainAndDate(s, d);
	}
	
	@Override
	public List<Reservation> joinUsNum(Long num){
	return repository.joinUsNum(num);
	}
	
	@Override
	public List<Reservation> findByTerrain(Terrain t) {
		return repository.findByTerrain(t);
	}

	@Override
	public List<Reservation> findByClub(Long id) {
		return repository.findByClub(id);
	}
	
	@Override 
	public List<Reservation> joinUs(){
	return repository.joinUs();
	}

	@Override
	public List<Reservation> joinUsbyClub(Long id) {
		return repository.joinUsbyClub(id);
	}

}
