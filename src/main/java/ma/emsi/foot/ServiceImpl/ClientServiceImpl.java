package ma.emsi.foot.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ma.emsi.foot.model.Client;
import ma.emsi.foot.repository.ClientRepository;
import ma.emsi.foot.service.ClientService;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository repository;

	@Override
	public Client ajouter(Client client) {
		return repository.save(client);
	}

	@Override
	public Client modifier(Client client, Long id) {
		// TODO Auto-generated method stub
		Client client1 = repository.findById(id).get();
        if (client1 != null){
        	client1.setAge(client.getAge());
        	
        	client1.setEmail(client.getEmail());
        	client1.setNom(client.getNom());
        	client1.setActive(client.isActive());
        	client1.setPassword(client.getPassword());
        	client1.setPrenom(client.getPrenom());
        	client1.setRoles(client.getRoles());
        	client1.setUserName(client.getUserName());
        	client1.setPicByte(client.getPicByte());
        	return repository.save(client1);
        }
        return null ;
	}

	@Override
	public void supprimer(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Client getClient(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<Client> liste() {
		return repository.findAll();
	}

}
