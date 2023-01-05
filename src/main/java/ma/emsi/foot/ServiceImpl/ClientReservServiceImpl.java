package ma.emsi.foot.ServiceImpl;
 
import java.util.List;
import java.util.Objects;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import jakarta.transaction.Transactional;
import ma.emsi.foot.model.Client;
import ma.emsi.foot.model.ClientReserv;
import ma.emsi.foot.model.Panier;
import ma.emsi.foot.model.Reservation;
import ma.emsi.foot.model.Utilisateur;
import ma.emsi.foot.repository.ClientRepository;
import ma.emsi.foot.repository.ClientReservRepository;
import ma.emsi.foot.repository.PanierRepository;
import ma.emsi.foot.repository.UtilisateurRepository;
import ma.emsi.foot.service.ClientReservService;
 
@Service
@Transactional
public class ClientReservServiceImpl implements ClientReservService{
 

@Autowired
private ClientReservRepository repository;
 

@Autowired
private ClientRepository repositoryClient;
 
@Autowired
private UtilisateurRepository utilisateurRepository;

@Autowired
private PanierRepository repositoryPanier;
 

 

@Override
public ClientReserv ajouter(ClientReserv clientReserv) {
Panier pepe = new Panier();
repositoryPanier.findById(clientReserv.getPanier().getId());
return repository.save(clientReserv);
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
public List<ClientReserv> findByReservation(Long id){
return repository.findByReservation(id);
}
 

@Override
public void join(Reservation reser) {
Panier panier = new Panier();
Client cc = new Client ();
repositoryPanier.findById(reser.getId());
}
 

@Override
public void join1(Reservation reser,Long nbrJoueur , Long id) {
  
Client cc = repositoryClient.findById(id).get();
Panier panier=repositoryPanier.findByClient(cc.getId());
 

if(Objects.equals(panier,null) ) {
panier = new Panier();
panier.setClient(cc);
repositoryPanier.save(panier);
}
ClientReserv toto = new ClientReserv();
toto.setPanier(panier);
toto.setReservation(reser);
toto.setNbr(nbrJoueur);
repository.save(toto); 
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

@Override
public List<ClientReserv> findByPanier(Panier panier) {
	return repository.findByPanier(panier);
}
 
}