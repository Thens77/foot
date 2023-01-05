package ma.emsi.foot.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.emsi.foot.model.Client;
import ma.emsi.foot.model.Panier;
import ma.emsi.foot.repository.PanierRepository;
import ma.emsi.foot.service.PanierService;

@Service
@Transactional
public class PanierServiceImpl implements PanierService{
	
	@Autowired
	private PanierRepository repository;

	@Override
	public Panier ajouter(Panier club) {
		return repository.save(club);
	}

	@Override
	public Panier modifier(Panier panier, Long id) {
		// TODO Auto-generated method stub
		Panier panier2 = repository.findById(id).get();
		if (panier2 != null) {
			panier2.setClient(panier2.getClient());;
			return repository.save(panier2);
		}
		return null;
	}

	@Override
	public void supprimer(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Panier getPanier(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Panier> liste() {
		return repository.findAll();
	}

	@Override
	public List<Panier> findByClient(Client client) {
		
		return repository.findByClient(client);
	}

}
