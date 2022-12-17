package ma.emsi.foot.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.emsi.foot.model.ClientReserv;
import ma.emsi.foot.repository.ClientReservRepository;
import ma.emsi.foot.service.ClientReservService;

@Service
@Transactional
public class ClientReservServiceImpl implements ClientReservService{
	
	@Autowired
	private ClientReservRepository repository;

	@Override
	public ClientReserv ajouter(ClientReserv terrain) {
		return repository.save(terrain);
	}

	@Override
	public ClientReserv modifier(ClientReserv clientReserv, Long id) {
		// TODO Auto-generated method stub
		ClientReserv clientReserv2 = repository.findById(id).get();
		if (clientReserv2 != null) {
			clientReserv2.setNbr(clientReserv.getNbr());
			clientReserv2.setPanier(clientReserv.getPanier());
			clientReserv2.setReservation(clientReserv.getReservation());
			return repository.save(clientReserv2);
		}
		return null;
	}

	@Override
	public void supprimer(Long id) {
		repository.deleteById(id);
	}

	@Override
	public ClientReserv getClientReserv(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<ClientReserv> liste() {
		return repository.findAll();
	}

}
