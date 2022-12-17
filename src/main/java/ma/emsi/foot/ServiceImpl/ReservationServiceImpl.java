package ma.emsi.foot.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.emsi.foot.model.Reservation;
import ma.emsi.foot.model.Terrain;
import ma.emsi.foot.repository.ReservationRepository;
import ma.emsi.foot.service.ReservationService;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	private ReservationRepository repository;

	@Override
	public Reservation ajouter(Reservation reservation) {
		return repository.save(reservation);
	}

	@Override
	public Reservation modifier(Reservation reservation, Long id) {
		// TODO Auto-generated method stub
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
	public List<Reservation> listeByTerrainAndDate(Terrain s, Date d) {
	
		return repository.findByTerrainAndDate(s, d);
	}

}
